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

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

/**
 * Creates proxies that provide typed access to values in
 * {@linkplain PropertySource sources of property configuration}.
 *
 * The {@link Class} given to a binder at construction time should be
 * an interface with no super-interfaces. We refer to such an interfaces
 * as a <dfn>schema</dfn>. A schema's methods represent the keys of a source
 * of property configuration and the intended types of their associated
 * values. Binding a source of property configuration to the schema gives
 * the caller a proxy that implements the schema's interface.
 * Invoking a proxy method attempts to retrieve the property value associated
 * with the method's key, and to convert the value to the method's return
 * type.
 *
 * Every schema method maps to a property key. A method marked with
 * {@link BoundProperty} uses that annotation's value as the property key
 * when the method is invoked; if it is not so marked, the key used is:
 * <a href="https://docs.oracle.com/javase/specs/jls/se8/html/jls-6.html#jls-6.7">
 * fully qualified name</a> of the schema interface {@code + '.' +}
 * the method's name.
 *
 * Binders admit sources of property configuration that map string keys
 * to values of unknown type. If a given value in such a source happens
 * to be a string, that value will undergo conversion to the method's
 * return type if possible. Any problems with such a conversion will result
 * in runtime type errors.
 *
 * Binders discover instances of the {@linkplain java.util.ServiceLoader
 * service} {@link Conversion} to attempt conversions of property values.
 *
 * The {@link ValuesSeparatedBy} annotation can be applied only to schema
 * methods with an aggregate return type.
 *
 * If a schema method specifies a default value via {@link DefaultsTo},
 * that value must be convertible to the return type of the schema method.
 *
 * If the schema method accepts one or more arguments, the property's value
 * is treated as a {@linkplain String#format(String, Object...) format string},
 * and the arguments assigned to the format specifiers accordingly,
 * before the entire value is converted. If the property's value is
 * not a string, any arguments to the schema method are ignored.
 *
 * If the schema methods is marked with {@link ParsedAs}, then a property
 * value's conversion is handed the patterns from the annotation in a list,
 * for the conversion to use at its discretion for parsing property values.
 * For example, a conversion of date values might accept
 * {@linkplain java.text.SimpleDateFormat simple date format} patterns.
 *
 * After binding, invoking a schema method returns the value of the property
 * it represents if that property is present; else the value given by
 * the method's {@link DefaultsTo} marker if present; else null for
 * scalar types, a zero-length array for array types, an empty list for
 * list types, and the empty value for optional types. If the schema method
 * returns a primitive type and neither a property nor its default
 * is present, the method will raise {@code NullPointerException}.
 *
 * Binders validate property values if {@link #validated()} is called,
 * according to the
 * <a href="https://docs.oracle.com/javaee/7/tutorial/bean-validation.htm">
 * Bean Validation API</a>. Zero-parameter schema methods have their
 * associated property values validated as soon as the schema is bound
 * to a property source. Schema methods with one or more arguments have
 * their arguments and resulting property values validated when the method
 * is invoked.
 *
 * Inspired by
 * <a href="https://lemnik.wordpress.com/2007/03/28/code-at-runtime-in-java-56/">this blog entry</a>.
 *
 * @param <T> the type of accessor proxies the binder is to create
 */
public class PropertyBinder<T> {
  private final Schema<T> schema;

  /**
   * Creates a new property binder from the given schema.
   *
   * @param schema the type used to create and configure accessor proxies
   * @throws NullPointerException if {@code schema} is {@code null}
   * @throws IllegalArgumentException if {@code schema}'s configuration
   * is invalid in any way
   */
  public PropertyBinder(Class<T> schema) {
    @SuppressWarnings("rawtypes")
    ServiceLoader<Conversion> loader = ServiceLoader.load(Conversion.class);

    this.schema =
      new SchemaValidator(loader.iterator()).validate(schema);
  }

  /**
   * Factory method for property binders.
   *
   * @param schema the type used to create and configure accessor proxies
   * @param <U> the type of accessor proxies the binder is to create
   * @return a new property binder that binds proxies to sources of
   * property configuration
   * @throws NullPointerException if {@code schema} is {@code null}
   * @throws IllegalArgumentException if {@code schema}'s configuration
   * is invalid in any way
   */
  public static <U> PropertyBinder<U> forType(Class<U> schema) {
    return new PropertyBinder<>(schema);
  }

  /**
   * Tells the property binder that it will validate property values
   * via the Bean Validation API at bind time and/or retrieval time.
   *
   * @return self
   */
  public PropertyBinder<T> validated() {
    schema.validateWhenPropertiesBecomeBound();
    return this;
  }

  /**
   * Makes a new proxy bound to the properties purported to be in the given
   * input reader.
   *
   * @param propertyInput a reader containing properties to be bound
   * @return a proxy bound to the properties
   * @throws IOException if there is a problem reading from the reader
   * @throws NullPointerException if {@code propertyInput} is {@code null}
   */
  public T bind(Reader propertyInput) throws IOException {
    return evaluate(loadProperties(propertyInput));
  }

  /**
   * Makes a new proxy bound to the given properties.
   *
   * If, after binding, the caller alters the contents of the properties
   * object via her reference to it, the properties that the proxy refers
   * to are affected.
   *
   * @param properties the properties to be bound
   * @return a proxy bound to the properties
   */
  public T bind(Properties properties) {
    return evaluate(new SubstitutableProperties(properties));
  }

  /**
   * Makes a new proxy bound to the given map.
   *
   * If, after binding, the caller alters the contents of the map via her
   * reference to it, the properties that the proxy refers to are affected.
   *
   * @param properties the properties to be bound
   * @return a proxy bound to the properties
   * @throws NullPointerException if {@code properties} is {@code null}
   */
  public T bind(Map<String, ?> properties) {
    return evaluate(new MapPropertySource(properties));
  }

  /**
   * Makes a new proxy bound to the given resource bundle
   *
   * If, after binding, the caller alters the contents of the bundle
   * via her reference to it, the properties that the proxy refers to
   * are affected.
   *
   * @param bundle the bundle to be bound
   * @return a proxy bound to the bundle
   * @throws NullPointerException if {@code bundle} is {@code null}
   */
  public T bind(ResourceBundle bundle) {
    return evaluate(new ResourceBundlePropertySource(bundle));
  }

  /**
   * Makes a new proxy bound to the properties represented by the given
   * property source.
   *
   * If, after binding, the caller affects the responses the property source
   * gives, the properties that the proxy refers to are affected.
   *
   * @param source the property source to be bound
   * @return a proxy bound to the property source
   * @throws NullPointerException if {@code source} is {@code null}
   */
  public T bind(PropertySource source) {
    return evaluate(source);
  }

  /**
   * Makes a new proxy bound to a snapshot of the current
   * {@linkplain System#getProperties() system properties}.
   *
   * @return a proxy bound to system properties
   */
  public static SystemProperties getSystemProperties() {
    return forType(SystemProperties.class).bind(System.getProperties());
  }

  private T evaluate(PropertySource source) {
    T mapped = schema.evaluate(source);
    return schema.validate(mapped);
  }

  private static PropertySource loadProperties(Reader input)
    throws IOException {

    SubstitutableProperties properties = new SubstitutableProperties();
    properties.load(input);
    return properties;
  }
}
