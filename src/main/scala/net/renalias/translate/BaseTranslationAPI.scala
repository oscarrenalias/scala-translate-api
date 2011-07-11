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

trait BingTranslate extends BaseTranslationAPI with Logging {
  this: BingConfig =>
  val baseUrl = "http://api.microsofttranslator.com/V2/Http.svc/"
  protected val httpErrorCodes = List(400, 401, 402, 403)

  def translate(text:String, from:Language, to:Language) = {
    import dispatch._
    import scala.xml._

    val targetUrl = baseUrl + "Translate?" + Helpers.buildQuery(List("appId" -> this.appId, "text" -> text, "from" -> from.langCode, "to" -> to.langCode))
    log.debug("Bing target URL = " + targetUrl)

    try {
      // make the request, serialize to XML and process the resulting NodeSeq to extract what we need
      // using the when() clause we make sure that the request handler is only run when the http response code
      // is not 40x
      val newText = newExecutor().when({code => !httpErrorCodes.exists(_ == code)})(url(targetUrl) <> { result =>
        (result \\ "string").text
      })

      Right(TranslationResult(newText))
    } catch {
      case ex:StatusCode => Left(TranslationFailure("http-error", "The server returned an HTTP error", Some(ex)))
      case ex:Exception => Left(TranslationFailure("error", "Error making the HTTP request", Some(ex)))
    }
  }
}

/**
 * Base object that should be instantiated, including one of the specific traits that
 * provide the translation itself and any configuration objects as required by the
 * specific trait
 *
 * Use it like this:
 *
 * val translator = new Translate with BingTranslate with MyBingConfig
 */
class Translate {
  this: BaseTranslationAPI =>
}