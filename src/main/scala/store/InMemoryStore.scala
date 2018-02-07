package akka.schema.registry.store

import akka.schema.registry.models._

import scala.concurrent.Future

object InMemoryStore extends SchemaRegistryStore {

  /**
    * Get schema by global id
    *
    * @param version
    * @return schema
    */
  def getSchema(version: Long): Future[Option[SchemaResponse]] = {
    Future.successful(Some(SchemaResponse(schema = "foo")))
  }

  /**
    * Get list of registered subjects
    * @return list of subjects
    */
  def getSubjects(): Future[SubjectsResponse] = {
    Future.successful(SubjectsResponse(value = Seq("s1", "s2")))
  }

  /**
    * Get list of versions for a subject
    * @param subject
    * @return list of version ids
    */
  def getSubjectVersions(subject: String): Future[Option[SubjectVersionsResponse]] = {
    Future.successful(Some(SubjectVersionsResponse(value = Seq(1, 2))))
  }

  /**
    * Register new schema
    *
    * @param subject
    * @param request
    * @return schema created with global id
    */
  def addSchema(subject: String, request: NewSchemaRequest): Future[Option[NewSchemaResponse]] = {
    Future.successful(Some(NewSchemaResponse(name = "foo", version = 1l, schema = "bar")))
  }

  /**
    * Delete subject
    *
    * @param subject
    * @return list of versions for subject
    */
  def deleteSubject(subject: String): Future[Option[SubjectVersionsResponse]] = {
    Future.successful(Some(SubjectVersionsResponse(value = Seq(1, 2))))
  }

  /**
    * Check if a schema has already been registered under the specified subject.
    *
    * @param subject
    * @param schema
    * @return schema details with global id if found
    */
  def checkSchema(subject: String, schema: String): Future[Option[CheckSchemaResponse]] = {
    Future.successful(Some(CheckSchemaResponse(id = 1l, name = "foo", version = 1l, schema = "bar")))
  }

  /**
    * Get schema with version
    *
    * @param subject
    * @param version
    * @return schema details with global id
    */
  def getVersionedSchema(subject: String, version: Long): Future[Option[CheckSchemaResponse]] = {
    Future.successful(Some(CheckSchemaResponse(id = 1l, name = "foo", version = 1l, schema = "bar")))
  }

  /**
    * Delete schema by subject and version
    *
    * @param subject
    * @param version
    * @return new updated list of versions
    */
  def deleteVersionedSchema(subject: String, version: Long): Future[Option[SubjectVersionsResponse]] = {
    Future.successful(Some(SubjectVersionsResponse(value = Seq(1, 2))))
  }

  /**
    * Check compability of a schema with currently registered ones
    *
    * @param subject
    * @param version
    * @param schema
    * @return boolean if compatible
    */
  def checkCompability(subject: String, version: Long, schema: String): Future[Option[Boolean]] = {
    Future.successful(Some(true))
  }

}
