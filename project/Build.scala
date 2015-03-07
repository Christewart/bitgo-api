import sbt._
import Keys._

object BitgoBuild extends Build {
 
  val appName = "bitgo-api"
  val appV = "0.1"
  val scalaV = "2.11.2"
  val akkaV = "2.3.6"
  val sprayV = "1.3.2" 
 
  val appDependencies = Seq( 
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV withSources() withJavadoc(),
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test" withSources() withJavadoc(),
    "io.spray"            %%  "spray-client"     % sprayV withSources() withJavadoc(), 
    "io.spray" %% "spray-can" % sprayV withSources() withJavadoc(),
    "io.spray" %% "spray-routing" % sprayV withSources() withJavadoc(),
    "io.spray" %%  "spray-json" % "1.3.1" withSources() withJavadoc(),   
    "io.spray" %% "spray-testkit" % sprayV % "test" withSources() withJavadoc(), 
    "org.scalatest" %% "scalatest" % "2.2.4" % "test" withSources() withJavadoc(),  
    "joda-time" % "joda-time" % "2.7"  
  )
  
  
  lazy val root = Project(appName, base=file(".")).settings(
    scalaVersion := scalaV, 
    libraryDependencies ++= appDependencies
  )

}
