/*
 The MIT License

 Copyright (c) 2009-2021 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.pholser.util.properties;

import com.pholser.util.properties.conversions.Conversion;
import com.pholser.util.properties.internal.MapPropertySource;
import com.pholser.util.properties.internal.ResourceBundlePropertySource;
import com.pholser.util.properties.internal.Schema;
import com.pholser.util.properties.internal.validation.SchemaValidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

public class PropertyBinder<T> {
  private final Schema<T> schema;

  public PropertyBinder(Class<T> schema) {
    @SuppressWarnings("rawtypes")
    ServiceLoader<Conversion> loader = ServiceLoader.load(Conversion.class);

    this.schema =
      new SchemaValidator(loader.iterator()).validate(schema);
  }

  public static <U> PropertyBinder<U> forType(Class<U> schema) {
    return new PropertyBinder<>(schema);
  }

  public PropertyBinder<T> validated() {
    schema.validateWhenPropertiesBecomeBound();
    return this;
  }

  public T bind(InputStream propertyInput) throws IOException {
    return evaluate(loadProperties(propertyInput));
  }

  public T bind(File propertiesFile) throws IOException {
    try (InputStream input = new FileInputStream(propertiesFile)) {
      return bind(input);
    }
  }

  public T bind(Properties properties) {
    return evaluate(new SubstitutableProperties(properties));
  }

  public T bind(Map<String, ?> properties) {
    return evaluate(new MapPropertySource(properties));
  }

  public T bind(ResourceBundle bundle) {
    return evaluate(new ResourceBundlePropertySource(bundle));
  }

  public T bind(PropertySource source) {
    return evaluate(source);
  }

  private T evaluate(PropertySource source) {
    T mapped = schema.evaluate(source);
    return schema.validate(mapped);
  }

  public static SystemProperties getSystemProperties() {
    return forType(SystemProperties.class).bind(System.getProperties());
  }

  private static PropertySource loadProperties(InputStream input)
    throws IOException {

    SubstitutableProperties properties = new SubstitutableProperties();
    properties.load(input);
    return properties;
  }
}
