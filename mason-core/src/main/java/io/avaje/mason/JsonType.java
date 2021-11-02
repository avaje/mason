package io.avaje.mason;

import java.io.*;
import java.util.List;

/**
 * A nicer to use JsonAdapter.
 */
public interface JsonType<T> {

  /**
   * Return the list type for this JsonType.
   */
  JsonType<List<T>> list();

  String toJson(T bean) throws IOException;

  void toJson(JsonWriter writer, T bean) throws IOException;

  void toJson(Writer writer, T bean) throws IOException;

  void toJson(OutputStream outputStream, T bean) throws IOException;

  T fromJson(JsonReader reader) throws IOException;

  T fromJson(String content) throws IOException;

  T fromJson(Reader reader) throws IOException;

  T fromJson(InputStream inputStream) throws IOException;

}
