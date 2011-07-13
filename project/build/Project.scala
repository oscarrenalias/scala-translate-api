import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) with IdeaProject {

val dispatch_http = "net.databinder" %% "dispatch-http" % "0.8.3" withSources()
val dispatch_http_nio = "net.databinder" %% "dispatch-nio" % "0.8.3" withSources()
val specs = "org.scala-tools.testing" %% "specs" % "1.6.8" % "test"

val renalias_repo = "renalias.net Repository" at "http://phunkphorce.github.com/maven"
val common_lib = "net.renalias" %% "scala-common" % "20110712" withSources()

}