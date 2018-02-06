package akka.schema.registry.models


case class ErrorResponse(
                          error_code: Int,
                          message: String
                        )


/**
  * https://docs.confluent.io/current/schema-registry/docs/api.html#get--schemas-ids-int- id
  *
  * @param schema
  */
case class SchemaResponse(
                           schema: String
                         )


/**
  * https://docs.confluent.io/current/schema-registry/docs/api.html#get--subjects
  *
  * @param value
  */
case class SubjectsResponse(value: Seq[String]) extends AnyVal


/**
  * https://docs.confluent.io/current/schema-registry/docs/api.html#get--subjects-(string- subject)-versions
  * https://docs.confluent.io/current/schema-registry/docs/api.html#delete--subjects-(string- subject)
  *
  * @param value
  */
case class SubjectVersionsResponse(value: Seq[Long]) extends AnyVal


/**
  * https://docs.confluent.io/current/schema-registry/docs/api.html#get--subjects-(string- subject)-versions-(versionId- version)
  *
  * @param name
  * @param version
  * @param schema
  */
case class NewSchemaResponse(
                              name: String,
                              version: Long,
                              schema: String
                            )


/**
  * https://docs.confluent.io/current/schema-registry/docs/api.html#post--subjects-(string- subject)-versions
  *
  * @param version
  */
case class NewSubjectVersionResponse(
                                      version: Long
                                    )


/**
  * https://docs.confluent.io/current/schema-registry/docs/api.html#post--subjects-(string- subject)
  *
  * @param id
  * @param name
  * @param version
  * @param schema
  */
case class CheckSchemaResponse(
                                id: Long,
                                name: String,
                                version: Long,
                                schema: String
                              )


/**
  * https://docs.confluent.io/current/schema-registry/docs/api.html#delete--subjects-(string- subject)-versions-(versionId- version)
  *
  * @param value
  */
case class DeleteSubjectVersionResponse(value: Long) extends AnyVal


/**
  * https://docs.confluent.io/current/schema-registry/docs/api.html#post--compatibility-subjects-(string- subject)-versions-(versionId- version)
  *
  * @param is_compatible
  */
case class CompabilitySchemaResponse(
                                      is_compatible: Boolean
                                    )