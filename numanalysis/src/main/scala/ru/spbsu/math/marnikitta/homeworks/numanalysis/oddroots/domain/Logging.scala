package ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain

import org.slf4j.{Logger, LoggerFactory}

/**
  * Created by marnikitta on 11.09.16.
  */
trait Logging {
  val LOG: Logger = LoggerFactory.getLogger(this.getClass)
}
