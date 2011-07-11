package net.renalias.logging

import org.slf4j.{ Logger ⇒ SLFLogger, LoggerFactory ⇒ SLFLoggerFactory }

/**
 * Created by IntelliJ IDEA.
 * User: Oscar Renalias
 * Date: 11/07/11
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */

object Logger {
  def apply(logger: String): SLFLogger = SLFLoggerFactory getLogger logger
  def apply(clazz: Class[_]): SLFLogger = apply(clazz.getName)
  def root: SLFLogger = apply(SLFLogger.ROOT_LOGGER_NAME)
}

trait Logging {
  @transient lazy val log = Logger(this.getClass.getName)
}