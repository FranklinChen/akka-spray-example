package janrain.sample

import akka.actor._
import akka.io.IO
import spray.can.Http
import spray.http._
import HttpMethods._
import MediaTypes._
import akka.routing.RoundRobinRouter

class RequestHandler extends Actor {
  def receive = {
    case _: Http.Connected ⇒
      sender ! Http.Register(self)

    case HttpRequest(GET, Uri.Path("/"), _, _, _) ⇒
      sender ! HttpResponse(entity = HttpEntity(`text/plain`, "hello!"))
  }
}

object MainActor {
  case object Start
}

class MainActor extends Actor {
  import MainActor.Start

  implicit val system = context.system

  def receive: Receive = {
    case Start ⇒
      val handler: ActorRef =
        context.actorOf(Props[RequestHandler].withRouter(RoundRobinRouter(nrOfInstances = 10)))
      IO(Http) ! Http.Bind(handler, interface = "localhost", port = 9090)
  }
}

object Application extends App {
  val system = ActorSystem("akka-sample")
  val mainActor = system.actorOf(Props[MainActor], name = "main")
  mainActor ! MainActor.Start
}
