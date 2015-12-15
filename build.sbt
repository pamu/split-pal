import Libraries.android._
import Libraries.macroid._
import Libraries.playServices._
import Libraries.apacheCommons._
import Libraries.json._
import Libraries.graphics._
import Libraries.scroller._
import Libraries.test._
import android.Keys._
import android.PromptPasswordsSigningConfig

android.Plugin.androidBuild

platformTarget in Android := Versions.androidPlatformV

name := """split-pal"""

organization := "com.rxbytes"

organizationName := "rxbytes"

organizationHomepage := Some(new URL("http://pamu.github.io"))

version := Versions.appV

scalaVersion := Versions.scalaV

scalacOptions ++= Seq("-feature", "-deprecation")

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

scalacOptions ++= Seq("-feature", "-deprecation", "-target:jvm-1.7")

resolvers ++= Settings.resolvers

libraryDependencies ++= Seq(
  aar(macroidRoot),
  aar(androidAppCompat),
  aar(androidCardView),
  aar(androidRecyclerview),
  aar(macroidExtras),
  aar(playServicesBase),
  aar(fastScroller),
  apacheCommonsLang,
  json4s,
  picasso,
  specs2,
  mockito,
  androidTest,
  compilerPlugin(Libraries.wartRemover))

apkSigningConfig in Android := Option(
  PromptPasswordsSigningConfig(
    keystore = new File(Path.userHome.absolutePath + "/.android/rxbytes.keystore"),
    alias = "rxbytes"))

run <<= run in Android

packageRelease <<= packageRelease in Android

proguardScala in Android := true

useProguard in Android := true

proguardOptions in Android ++= Settings.proguardCommons

proguardCache in Android := Seq.empty
