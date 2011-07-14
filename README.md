Scala-translate-API
===================

Scala-translate-API is a very simple API for some of the online translation APIs such as Bing Translate API or Google Translate. So far, given that the future of Google Translate is unclear, only the Bing Translate API is supported. 

The API is currently fully synchronous, but using the asynchronous capabilities of the Dispatch library is one of the TODO items for future releases. For the time being, please encapsulate the API calls within a simple actor to obtain asynchronous capabilities.

Bing API Usage
--------------
The Bing API implementation is currently based on the HTTP version of the Microsoft Translator V2 as described here: http://msdn.microsoft.com/en-us/library/ff512419.aspx. Only the Microsoft.Translator.Translate method (http://msdn.microsoft.com/en-us/library/ff512419.aspx) is supported.

Please note that Bing requires its own application id in order to use the translation API.

API usage:

```scala
val translator = new Translate with Bing with new BingConfig {
	val appId = "your-bing-app-id-goes-here"
}
val text = translator.translate("text to translate", English, Spanish)
```

Project setup
-------------
The library is cross-compiled for Scala 2.9.0 and 2.9.0-1, and can be declared as a dependency in your project with SBT or Maven.

Using SBT:

```scala
val renalias_repo = "Renalias.net Maven Repository" at "http://phunkphorce.github.com/maven"
val translate_lib = "net.renalias" %% "scala-translate-api" % "1.0-SNAPSHOT"
```

Using Maven, first declare the repository:

```xml
<repository>
  <id>renaliasnetRepository</id>
  <name>renalias.net Repository</name>
  <url>http://phunkphorce.github.com/maven/</url>
</repository>
```

And then declare the dependency:

```xml
<dependency>
  <groupId>net.renalias</groupId>
  <artifactId>scala-translate-api_2.9.0-1</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
