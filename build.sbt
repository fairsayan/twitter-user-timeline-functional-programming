name := "twitter-user-timeline-functional-programming"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
libraryDependencies ++= Seq(
  "org.twitter4j" % "twitter4j-core" % "4.0.4",
  "org.twitter4j" % "twitter4j-stream" % "4.0.4"
)

