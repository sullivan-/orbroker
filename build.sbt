
name := "orbroker"

organization := "org.orbroker"

version := "4.0.0-SNAPSHOT"

scalaVersion := "2.10.3"

javacOptions ++= Seq("-source", "1.5", "-target", "1.5")

scalacOptions ++= Seq("-deprecation","-explaintypes","-unchecked")

libraryDependencies ++= Seq(
  "org.freemarker" % "freemarker" % "2.3.18" % "optional",
  "joda-time" % "joda-time" % "2.3" % "optional",
  "org.apache.velocity" % "velocity" % "1.7" % "optional",
  "org.apache.derby" % "derby" % "10.4.2.0" % "test",
// junit interface for sbt
  "com.novocode" % "junit-interface" % "0.9" % "test->default"
)

//parallelExecution in Test := false

//need to be narrowed
unmanagedResourceDirectories in Test <+= baseDirectory(_ / "src/test/scala") 

//it conflicts during package phase with autogenerated one
excludeFilter in unmanagedResources := "MANIFEST.MF" 

credentials += Credentials(new java.io.File(""".credentials"""))

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) 
    Some("snapshots" at nexus + "content/repositories/snapshots") 
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomIncludeRepository := { _ => false }

publishArtifact in Test := false

publishMavenStyle := true


pomExtra := (
  <url>http://orbroker.org</url>
  <licenses>
    <license>
      <name>GNU Lesser GPL</name>
      <url>http://www.gnu.org/licenses/lgpl.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://code.google.com/p/orbroker/</url>
    <connection>scm:hg:https://code.google.com/p/orbroker/</connection>
  </scm>
  <developers>
    <developer>
      <id>nilskp</id>
      <name>Nils Kilden-Pedersen</name>
    </developer>
    <developer>
      <id>nustinov</id>
      <name>Nicholas Ustinov</name>
    </developer>
  </developers>)
