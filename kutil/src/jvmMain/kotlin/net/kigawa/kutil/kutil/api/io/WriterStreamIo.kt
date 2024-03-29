package net.kigawa.mcsm.util.io

import kotlinx.coroutines.channels.Channel
import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.kutil.kutil.api.io.ChannelWriterIo
import net.kigawa.kutil.kutil.api.io.SuspendCloseable
import java.io.BufferedWriter

class WriterStreamIo(
  coroutines: Coroutines,
  private val writer: BufferedWriter,
  private val onClose: (suspend () -> Unit)? = null,
) : ChannelWriterIo<String>(Channel()), SuspendCloseable {
  init {
    coroutines.launchIo {
      try {
        for (line in channel) {
          writer.write(line)
          writer.newLine()
          writer.flush()
        }
      } finally {
        suspendClose()
      }
    }
  }

  override suspend fun suspendClose() {
    try {
      channel.close()
      writer.run { close() }
    } finally {
      onClose?.invoke()
    }
  }
}