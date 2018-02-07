package akka.schema.registry.http.routes

import akka.http.scaladsl.server.Directives._
import akka.schema.registry.models._
import akka.schema.registry.store.SchemaRegistryStore
import de.heikoseeberger.akkahttpcirce.CirceSupport
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext

class SchemaRegistryRoute(store: SchemaRegistryStore)(implicit executionContext: ExecutionContext) extends CirceSupport {
  val route = pathPrefix("schemas" / "ids" / LongNumber) {
    case (id) =>
      get {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#get--schemas-ids-int- id
        complete {
          store.getSchema(id)
        }
      }
  } ~ pathPrefix("subjects") {
    get {
      // https://docs.confluent.io/current/schema-registry/docs/api.html#get--subjects
      complete {
        store.getSubjects()
      }
    }
  } ~ pathPrefix("subjects" / Segment / "versions") {
    case (subject) =>
      get {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#get--subjects-(string- subject)-versions
        complete {
          store.getSubjects()
        }
      } ~ post {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#post--subjects-(string- subject)-versions
        entity(as[NewSchemaRequest]) { request =>
          complete {
            store.addSchema(subject, request)
          }
        }
      }
  } ~ pathPrefix("subjects" / Segment) {
    case (subject) =>
      delete {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#delete--subjects-(string- subject)
        complete {
          store.deleteSubject(subject)
        }
      } ~ post {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#post--subjects-(string- subject)
        entity(as[NewSubjectVersionRequest]) { request =>
          complete {
            store.checkSchema(subject, request.schema)
          }
        }
      }
  } ~ pathPrefix("subjects" / Segment / "versions" / LongNumber) {
    case (subject, version) =>
      get {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#get--subjects-(string- subject)-versions-(versionId- version)
        complete {
          store.getVersionedSchema(subject, version)
        }
      } ~ delete {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#delete--subjects-(string- subject)-versions-(versionId- version)
        complete {
          store.deleteVersionedSchema(subject, version)
        }
      }
  } ~ pathPrefix("compability" / "subjects" / Segment / "versions" / LongNumber) {
    case (subject, version) =>
      post {
        // https://docs.confluent.io/current/schema-registry/docs/api.html#post--compatibility-subjects-(string- subject)-versions-(versionId- version)
        entity(as[NewSubjectVersionRequest]) { request =>
          complete {
            store.checkCompability(subject, version, request.schema)
          }
        }
      }
  }
}
