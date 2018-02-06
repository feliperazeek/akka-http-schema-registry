name := "akka-http-schema-registry"

version := "0.0.1"

scalaVersion := "2.11.8"

resolvers += "akka" at "http://repo.akka.io/snapshots"

libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % "2.2.4",
	"com.typesafe.akka" %% "akka-http" % "10.0.2",
	"com.typesafe.akka" %% "akka-http-core" % "10.0.2",
    "de.heikoseeberger" %% "akka-http-circe" % "1.11.0", 
    "io.circe" %% "circe-core" % "0.6.1",
    "io.circe" %% "circe-generic" % "0.6.1",
    "io.circe" %% "circe-parser" % "0.6.1",
    "com.typesafe.slick" %% "slick" % "3.2.0",
    "com.h2database" % "h2" % "1.4.185"
)

