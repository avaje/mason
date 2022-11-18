package io.avaje.jsonb.stream;

import java.io.InputStream;
import java.io.OutputStream;

final class Recycle {
  private Recycle() {}

  private static boolean jvmRecycle;
  private static ThreadLocal<JParser> read;
  private static ThreadLocal<JGenerator> managed;

  static {
    if (Float.parseFloat(System.getProperty("java.specification.version")) >= 19
        && !Boolean.getBoolean("jsonb.UseTLBuffers")) {
      jvmRecycle = true;
    } else {
      managed = ThreadLocal.withInitial(Recycle::getGenerator);
      read = ThreadLocal.withInitial(Recycle::getParser);
    }
  }

  /** Return a recycled generator with the given target OutputStream. */
  static JsonGenerator generator(OutputStream target) {
    return (jvmRecycle ? getGenerator() : managed.get()).prepare(target);
  }

  /** Return a recycled generator with expected "to String" result. */
  static JsonGenerator generator() {
    return (jvmRecycle ? getGenerator() : managed.get()).prepare(null);
  }

  static JsonParser parser(byte[] bytes) {
    return (jvmRecycle ? getParser() : read.get()).process(bytes, bytes.length);
  }

  static JsonParser parser(InputStream in) {
    return (jvmRecycle ? getParser() : read.get()).process(in);
  }

  static JGenerator getGenerator() {
    return new JGenerator(4096);
  }

  static JParser getParser() {
    return new JParser(
        new char[4096],
        new byte[4096],
        0,
        JParser.ErrorInfo.MINIMAL,
        JParser.DoublePrecision.DEFAULT,
        JParser.UnknownNumberParsing.BIGDECIMAL,
        100,
        50_000);
  }
}
