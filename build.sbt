val sprayVersion = "1.3.1"
val akkaVersion = "2.3.3"

name := "sample-akka-project"

organization := "net.lockney"

version := "0.0.1"

scalaVersion := "2.10.4"

resolvers += "spray" at "http://repo.spray.io/"

libraryDependencies ++= Seq(
  "io.spray" % "spray-can" % sprayVersion,
  "io.spray" % "spray-httpx" % sprayVersion,
  "io.spray" %%  "spray-json" % "1.2.6",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)

Revolver.settings
