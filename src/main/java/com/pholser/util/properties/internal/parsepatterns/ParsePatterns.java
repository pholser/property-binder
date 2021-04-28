package com.pholser.util.properties.internal.parsepatterns;

import com.pholser.util.properties.ParsedAs;
import com.pholser.util.properties.PropertySource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.pholser.util.properties.internal.Substitutions.substitute;

public class ParsePatterns {
  private final List<String> raw = new ArrayList<>();
  private final List<String> substitutable = new ArrayList<>();
  private final List<String> resolved = new ArrayList<>();

  public void load(ParsedAs spec) {
    Collections.addAll(raw, spec.value());
    resolved.addAll(raw);
    Collections.addAll(substitutable, spec.valueOf());
  }

  public boolean hasSubstitutions() {
    return !substitutable.isEmpty();
  }

  public List<String> resolved() {
    return new ArrayList<>(resolved);
  }

  public void resolve(PropertySource properties) {
    substitutable.stream()
      .map(each -> substitute(properties, each))
      .forEach(resolved::add);
  }
}
