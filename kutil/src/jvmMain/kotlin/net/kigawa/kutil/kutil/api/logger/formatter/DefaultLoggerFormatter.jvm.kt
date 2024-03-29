package net.kigawa.kutil.kutil.api.logger.formatter

import net.kigawa.kutil.kutil.api.logger.LoggerRecord
import net.kigawa.kutil.kutil.api.str.objectformatter.DefaultObjFormatter
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

actual class DefaultLoggerFormatter : LoggerFormatter {
  private val datetimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd|HH-ss")
  private val objectFormatter = DefaultObjFormatter()
  actual override fun format(logRecord: LoggerRecord): Any {
    val datetime = Instant.ofEpochMilli(logRecord.logRecord.millis).atZone(ZoneId.systemDefault())
    val datetimeStr = datetime.format(datetimeFormatter)
    return "$datetimeStr[${logRecord.level.name}]|${objectFormatter.format(logRecord.message)}\n"
  }
}