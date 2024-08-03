package io.avaje.jsonb.generator.models.valid;

import java.math.BigDecimal;

import io.avaje.jsonb.Json;

@Json
public class WithAdapterTest {

  @Json.Serializer(MoneySerializer.class)
  BigDecimal amount;

  BigDecimal somethingElse;
}
