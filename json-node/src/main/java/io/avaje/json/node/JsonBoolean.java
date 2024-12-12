package io.avaje.json.node;

public final /*value*/ class JsonBoolean implements JsonNode {

  private final boolean value;

  public static JsonBoolean of(boolean value) {
    return new JsonBoolean(value);
  }

  private JsonBoolean(boolean value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return text();
  }

  @Override
  public JsonBoolean unmodifiable() {
    return this;
  }

  @Override
  public JsonBoolean copy() {
    return this;
  }

  @Override
  public Type type() {
    return Type.BOOLEAN;
  }

  @Override
  public String text() {
    return Boolean.toString(value);
  }

  public boolean value() {
    return value;
  }

}