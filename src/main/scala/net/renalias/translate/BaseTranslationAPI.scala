package net.renalias.translate

import net.renalias.logging.Logging
import dispatch.Http

sealed case class Language(val langCode:String)
case object English extends Language("EN")
case object Spanish extends Language("ES")
case object French extends Language("FR")
case object German extends Language("DE")

case class TranslationFailure(val errorCode:String, val errorDescription:String, val ex:Option[Exception] = None)
case class TranslationResult(val result:String) {
  override def toString = result
}

protected[translate] object Helpers {
  val buildParam = (p:(String,String)) => p._1 + "=" + p._2
  val buildQuery = (l:List[(String,String)]) => l.flatMap({params:(String,String) => List(buildParam(params))}).mkString("&")
}

trait BaseTranslationAPI {
  protected[translate] var newExecutor = () => new Http
  def translate(text:String, from:Language, to:Language): Either[TranslationFailure, TranslationResult]
}

trait BingConfig {
  var appId:String
}



/**
 * Base object that should be instantiated, including one of the specific traits that
 * provide the translation itself and any configuration objects as required by the
 * specific trait
 *
 * Use it like this:
 *
 * val translator = new Translate with Bing with MyBingConfig
 */
class Translate {
  this: BaseTranslationAPI =>
}