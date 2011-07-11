import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) with IdeaProject {

val dispatch_http = "net.databinder" %% "dispatch-http" % "0.8.3" withSources()
val dispatch_http_nio = "net.databinder" %% "dispatch-nio" % "0.8.3" withSources()
val specs = "org.scala-tools.testing" %% "specs" % "1.6.8" % "test"
val log_lib = "ch.qos.logback" % "logback-classic" % "0.9.26"

}