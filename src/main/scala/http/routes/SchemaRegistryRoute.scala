package akka.schema.registry.http.routes

import akka.http.scaladsl.server.Directives._
import akka.schema.registry.models._
import de.heikoseeberger.akkahttpcirce.CirceSupport
import io.circe.generic.auto._
import io.circe.syntax._
import scala.concurrent.ExecutionContext

class SchemaRegistryRoute()(implicit executionContext: ExecutionContext) extends CirceSupport {
  val route = pathPrefix("schemas" / "ids" / LongNumber) {
    case (id) =>
      get {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#get--schemas-ids-int- id
        complete(SchemaResponse(schema = "foo").asJson)
      }
  } ~ pathPrefix("subjects") {
    get {
      // https://docs.confluent.io/current/schema-registry/docs/api.html#get--subjects
      complete(Seq("s1", "s2").asJson)
    }
  } ~ pathPrefix("subjects" / Segment / "versions") {
    case (subject) =>
      get {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#get--subjects-(string- subject)-versions
        complete(Seq(1, 2, 3, 4).asJson)
      } ~ post {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#post--subjects-(string- subject)-versions
        entity(as[NewSchemaRequest]) { request =>
          complete(NewSchemaResponse(name = "foo", version = 1l, schema = "bar").asJson)
        }
      }
  } ~ pathPrefix("subjects" / Segment) {
    case (subject) =>
      delete {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#delete--subjects-(string- subject)
        complete(Seq(1, 2, 3, 4).asJson)
      } ~ post {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#post--subjects-(string- subject)
        entity(as[NewSubjectVersionRequest]) { request =>
          complete(CheckSchemaResponse(id = 1l, name = "foo", version = 2l, schema = "bar").asJson)
        }
      }
  } ~ pathPrefix("subjects" / Segment / "versions" / LongNumber) {
    case (subject, version) =>
      get {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#get--subjects-(string- subject)-versions-(versionId- version)
        complete(NewSchemaResponse(name = "foo", version = 1l, schema = "bar").asJson)
      } ~ delete {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#delete--subjects-(string- subject)-versions-(versionId- version)
        complete(DeleteSubjectVersionResponse(1l).asJson)
      }
  } ~ pathPrefix("compability" / "subjects" / Segment / "versions" / LongNumber) {
    case (subject, version) =>
      post {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#post--compatibility-subjects-(string- subject)-versions-(versionId- version)
        complete(CompabilitySchemaResponse(is_compatible = true))
      }
  }
}
