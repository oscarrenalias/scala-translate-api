package net.renalias.translate

/**
 * Created by IntelliJ IDEA.
 * User: Oscar Renalias
 * Date: 11/07/11
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 */

sealed case class Language(val langCode:String)
case object English extends Language("EN")
case object Spanish extends Language("ES")
case object French extends Language("FR")
case object German extends Language("DE")

case class TranslationFailure(val errorCode:String, val errorDescription:String, val ex:Option[Exception] = None)
case class TranslationResult(val result:String)

trait BaseTranslationAPI {
  def translate(text:String, from:Language, to:Language): Either[TranslationFailure, TranslationResult]
}

trait BingConfig {
  var appKey:String
}

trait BingTranslate extends BaseTranslationAPI {
  this: BingConfig =>
  def translate(text:String, from:Language, to:Language) = {
    println("Translating text from: " + from + " to: " + to + " with key: " + this.appKey)

    Left(TranslationFailure("error", "Not implemented yet"))
  }
}

class Translate {
  this: BaseTranslationAPI =>
}