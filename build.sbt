name := "sample-akka-project"

version := "0.0.1"

organization := "janrain.sample"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "spray" at "http://repo.spray.io/",
  "Spray Nightlies" at "http://nightlies.spray.io/"
)

libraryDependencies ++= Seq(
  "io.spray" % "spray-can" % "1.2-20130712",
  "io.spray" % "spray-httpx" % "1.2-20130712",
  "io.spray" %%  "spray-json" % "1.2.5",
  "com.typesafe.akka" %% "akka-actor" % "2.2.0",
  "com.typesafe.akka" %% "akka-slf4j" % "2.2.0",
  "ch.qos.logback" % "logback-classic" % "1.0.0"
)
