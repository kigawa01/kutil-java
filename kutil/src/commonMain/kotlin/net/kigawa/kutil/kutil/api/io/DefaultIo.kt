package net.kigawa.kutil.kutil.api.io

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object DefaultIo {
  val error: WriterIo<String>
  val out: WriterIo<String>
}