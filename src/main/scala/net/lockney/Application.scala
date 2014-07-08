package net.lockney

import akka.actor._
import akka.io.IO
import akka.routing.RoundRobinPool
import spray.can.Http
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.http._

class RequestHandler extends Actor {
  def receive = {
    case _: Http.Connected ⇒
      sender ! Http.Register(self)

    case HttpRequest(GET, Uri.Path("/"), _, _, _) ⇒
      sender ! HttpResponse(entity = HttpEntity(`text/plain`, "hello!"))

    case HttpRequest(GET, Uri.Path("/bye"), _, _, _) ⇒
      sender ! HttpResponse(entity = HttpEntity(`text/plain`, "goodbye!"))
  }
}

object MainActor {
  case object Start
}

class MainActor extends Actor {
  import net.lockney.MainActor.Start

  implicit val system = context.system

  def receive: Receive = {
    case Start ⇒
      val handler: ActorRef =
        context.actorOf(Props[RequestHandler].withRouter(RoundRobinPool(nrOfInstances = 10)))
      IO(Http) ! Http.Bind(handler, interface = "localhost", port = 9090)
  }
}

object Application extends App {
  val system = ActorSystem("akka-sample")
  val mainActor = system.actorOf(Props[MainActor], name = "main")
  mainActor ! MainActor.Start
}
