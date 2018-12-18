name := "livytest"
version := "1.0"
scalaVersion := "2.11.8"
libraryDependencies ++= Seq(
"org.apache.spark" % "spark-core_2.11" % "2.1.0",
"org.apache.spark" % "spark-sql_2.11" % "2.1.0",
"org.apache.spark" % "spark-streaming_2.11" % "2.1.0",
"org.apache.spark" % "spark-mllib_2.11" % "2.1.0",
"org.jmockit" % "jmockit" % "1.34" % "test",
  "org.apache.livy" % "livy-client-http" % "0.5.0-incubating"
)

mainClass in Compile := Some("sample.FactoryOutside")

test in assembly := {}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}