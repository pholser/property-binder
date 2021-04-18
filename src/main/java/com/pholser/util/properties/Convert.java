package com.pholser.util.properties;

@FunctionalInterface
public interface Convert<T> {
  T convert(String formatted);
}
