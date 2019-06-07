name := "herding_cats"

version := "0.1"
//
// squash into
scalaVersion := "2.12.8"
val catsVersion: String = "1.0.1"
val catsCore: ModuleID = "org.typelevel" %% "cats-core" % catsVersion
val catsFree: ModuleID = "org.typelevel" %% "cats-free" % catsVersion
val catsMtl: ModuleID = "org.typelevel" %% "cats-mtl-core" % "0.2.1"
val simulacrum: ModuleID = "com.github.mpilquist" %% "simulacrum" % "0.11.0"
val macroParadise: ModuleID = compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
val kindProjector: ModuleID = compilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")
val resetAllAttrs: ModuleID = "org.scalamacros" %% "resetallattrs" % "1.0.0"

val specs2Version = "3.8.6" // use the version used by discipline
val specs2Core = "org.specs2" %% "specs2-core" % specs2Version
val specs2Scalacheck = "org.specs2" %% "specs2-scalacheck" % specs2Version
val scalacheck = "org.scalacheck" %% "scalacheck" % "1.12.4"

lazy val root = (project in file(".")).
  settings(
    organization := "com.example",
    name := "something",
    scalaVersion := "2.12.4",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.0.5",
      "org.scalatest" %% "scalatest" % "3.0.5" % "test",
      catsCore,
      catsFree,
      catsMtl,
      simulacrum,
      specs2Core % Test, specs2Scalacheck % Test, scalacheck % Test,
      macroParadise, kindProjector, resetAllAttrs
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-Ypartial-unification",
      "-feature",
      "-language:_"
    )
  )