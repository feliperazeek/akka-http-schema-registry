package akka.schema.registry
import akka.schema.registry.http.HttpService
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._

object Main {

  def main(args: Array[String]) {
    implicit val actorSystem       = ActorSystem("akka-http-schema-registry")
    implicit val actorMaterializer = ActorMaterializer()
    implicit val executor          = actorSystem.dispatcher
    Http().bindAndHandle(new HttpService().route, "0.0.0.0", 8080)
    println("server started at 8080")
  }

}
