package akka.schema.registry.http

import akka.schema.registry.http.routes._
import akka.http.scaladsl.server.Directives._
import scala.concurrent.ExecutionContext

class HttpService(implicit executionContext: ExecutionContext) {
  val routes = new SchemaRegistryRoute()
  val route = {
    routes.route ~ pathSingleSlash {
      get {
        complete {
          "Hello World"
        }
      }
    }
  }
}
