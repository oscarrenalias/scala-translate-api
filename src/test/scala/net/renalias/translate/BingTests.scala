package net.renalias.translate

import org.specs.Specification

class BingTests extends Specification("Bing Translate API tests") {
  "Bing API" should {
    shareVariables()

    // initialize the translation object
    val api = new Translate with BingTranslate with BingConfig {
      var appId = "A639B46D72A7DD8C40D882DC4546EBC44EA52513"
    }

    "successful translations should return a result" in {
      val result = api.translate("potato", English, Spanish)
      result must_== Right(TranslationResult("patata"))
    }
  }
}