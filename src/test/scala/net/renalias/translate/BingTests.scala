package net.renalias.translate

import org.specs.Specification

/**
 * Created by IntelliJ IDEA.
 * User: Oscar Renalias
 * Date: 11/07/11
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
class BingTests extends Specification("Bing Translate API tests") {
  "Bing API" should {
    shareVariables()

    // initialize the translation object
    val api = new Translate with BingTranslate with BingConfig {
      var appKey = "whatever"
    }

    "return the same text if to and from are the same language" in {
      val result = api.translate("text", English, English)
      result must_== Left(TranslationFailure("error", "Not implemented yet"))
    }
  }
}