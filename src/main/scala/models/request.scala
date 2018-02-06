package akka.schema.registry.models

/**
  * https://docs.confluent.io/current/schema-registry/docs/api.html#get--subjects-(string- subject)-versions-(versionId- version)
  *
  * @param name
  * @param version
  * @param schema
  */
case class NewSchemaRequest(
  name: String,
  version: Int,
  schema: String
)


/**
  * https://docs.confluent.io/current/schema-registry/docs/api.html#post--subjects-(string- subject)-versions
  * https://docs.confluent.io/current/schema-registry/docs/api.html#post--subjects-(string- subject)
  * https://docs.confluent.io/current/schema-registry/docs/api.html#post--compatibility-subjects-(string- subject)-versions-(versionId- version)
  *
  * @param schema
  */
case class NewSubjectVersionRequest(
                             schema: String
                           )
