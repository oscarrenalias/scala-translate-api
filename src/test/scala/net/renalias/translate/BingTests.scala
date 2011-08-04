package net.renalias.translate

import org.specs.Specification
import org.specs.matcher.Matcher

class BingTests extends Specification("Bing Translate API tests") {

		// small custom matcher to make our tests a little nicer
		lazy val beSuccessful = new Matcher[TranslationResult]() {
			def apply(v: => TranslationResult) = ({ v match {
				case Right(TranslationSuccess(_)) => true
				case _ => false
			}}, "OK", "The returned response was not successful. Actual error: " + v.toString)
    }

  "Bing API" should {
    shareVariables()

    // initialize the translation object
    val api = new Translate with Bing with BingConfig {
      var appId = "A639B46D72A7DD8C40D882DC4546EBC44EA52513"
    }

    "successful translations should return a result" in {
      val response = api.translate("potato", English, Spanish)
	    response must beSuccessful
    }

	  "correctly escape characters in texts" in {
		  val response = api.translate("two words & two more", English, Spanish)
		  response must beSuccessful
	  }

	  "be able to translate long texts" in {
		  val longText = """this is is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library,
this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library,
this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library,
this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library
this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library,
this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library
this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library,
this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library
this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library,
this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library, this is a very long text that should not be a problem for the library
"""
		  val response = api.translate(longText, English, Spanish)
		  response must beSuccessful
	  }
  }
}