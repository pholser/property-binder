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

import com.pholser.util.properties.internal.MapPropertySource;
import com.pholser.util.properties.internal.ResourceBundlePropertySource;
import com.pholser.util.properties.internal.ValidatedSchema;
import com.pholser.util.properties.internal.validation.SchemaValidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

/**
 * <p>Creates proxies that provide typed access to values in
 * {@linkplain PropertySource sources of property configuration} via the
 * "Proxied Interfaces Configured with Annotations" (PICA) technique.</p>
 *
 * <p>Inspired by
 * <a href="http://lemnik.wordpress.com/2007/03/28/code-at-runtime-in-java-56/">this blog entry</a>.</p>
 *
 * <p>The {@link Class} given to a binder at
 * {@linkplain PropertyBinder#PropertyBinder(Class) construction time}
 * should be an interface with no super-interfaces. It is referred to as a
 * <dfn>schema</dfn>. A schema's methods prescribe the keys of a source
 * of property configuration and the intended types of their associated values.
 * Binding a source of property configuration to the schema gives the caller
 * a proxy that implements the schema's interface. Invoking a proxy method
 * attempts to retrieve the property value associated with the key prescribed
 * by the invoked method, and to convert the value to the type prescribed by
 * the method's return value.</p>
 *
 * <p>Every schema method maps to a property key. A method can be marked with
 * {@link BoundProperty} to indicate the property key to be used when
 * the method is invoked; if it is not so marked, the key used is:
 * fully qualified name of the schema interface + '.' + the method's name.</p>
 *
 * <p>Prior to version 3, binders accepted only {@link Properties} and
 * {@link java.util.Map Map}s of string keys to string values. Beginning with
 * version 3, binders admit sources of property configuration that map
 * string keys to values of unknown type. If a given value in such a source
 * happens to be a string, that value will undergo conversion to the method's
 * return type if possible. Any problems with such a conversion will result
 * in runtime type errors.</p>
 *
 * <p>In order for a given property value to be converted from a string,
 * the associated schema method should have a return type which:
 *
 * <ul>
 *   <li>has a {@link Conversion} made available as a {@link ServiceLoader}
 *   service</li>
 *   <li>an array of such types</li>
 *   <li>a {@link java.util.List} of such types</li>
 *   <li>a {@link java.util.Optional} of such types</li>
 * </ul>
 *
 * <p>The {@link ValuesSeparatedBy} annotation can be applied only to
 * schema methods with an aggregate return type, and must specify
 * a well-formed {@linkplain java.util.regex.Pattern regular expression}
 * as a separator.</p>
 *
 * <p>If a schema method specifies a default value via {@link DefaultsTo},
 * that value must be convertible to the return type of the schema method
 * via the above rules.
 *
 * <p>If a schema method accepts no arguments, the property's value
 * will be converted via the above rules. If the schema method accepts
 * one or more arguments, the property's value is treated as a
 * {@linkplain String#format(String, Object...) format string},
 * and the arguments assigned to the format specifiers accordingly,
 * before the entire value is converted. If the property's value is not
 * a string, any arguments to the schema method are ignored.</p>
 *
 * <p>A schema method can be annotated with {@link ParsedAs} to indicate
 * that the corresponding property's value(s) may be parsed by a matching
 * {@link Conversion} using the given patterns. For example, a conversion
 * to {@link java.util.Date} might treat such patterns as
 * {@linkplain java.text.SimpleDateFormat date patterns}.
 *
 * <p>After binding, invoking a schema method returns the value
 * of the property it represents if that property is present; else the value
 * given by the method's {@link DefaultsTo} marker if present;
 * else {@code null} for scalar types, a zero-length array for array types,
 * and an {@linkplain java.util.Collection#isEmpty() empty list} for
 * list types. If the schema method returns a primitive type and neither
 * a property nor its default is present, the method will raise
 * {@link NullPointerException}.</p>
 *
 * @param <T> the type of accessor proxies the binder is to create
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public class PropertyBinder<T> {
  private final ValidatedSchema<T> validated;

  /**
   * Creates a new property binder from the given schema.
   *
   * @param schema the type used to create and configure accessor proxies
   * @throws NullPointerException if {@code schema} is {@code null}
   * @throws IllegalArgumentException if {@code schema}'s configuration
   * is invalid in any way
   */
  public PropertyBinder(Class<T> schema) {
    ServiceLoader<Conversion> loader = ServiceLoader.load(Conversion.class);
    validated = new SchemaValidator(loader.iterator()).validate(schema);
  }

  /**
   * Factory method for property binders. Leverages generics type inference
   * so that if you don't wish to specify the type more than once at
   * {@linkplain PropertyBinder#PropertyBinder(Class) construction time},
   * you can call this method instead (at the expense of not offering a seam
   * for testing).
   *
   * @param <U> the type of accessor proxies the binder is to create
   * @param schema the type used to create and configure accessor proxies
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
   * Makes a new proxy bound to the properties purported to be in
   * the given input stream.
   *
   * @param propertyInput an input stream containing properties to be bound
   * @return a proxy bound to the properties
   * @throws IOException if there is a problem reading from the input stream
   * @throws NullPointerException if {@code propertyInput} is {@code null}
   * @see #bind(File)
   */
  public T bind(InputStream propertyInput) throws IOException {
    return evaluate(loadProperties(propertyInput));
  }

  /**
   * Makes a new proxy bound to the properties purported to be in
   * the given file.
   *
   * @param propertiesFile a file containing properties to be bound
   * @return a proxy bound to the properties
   * @throws IOException if there is a problem reading from the file
   * @throws NullPointerException if {@code propertiesFile} is {@code null}
   * @see #bind(InputStream)
   */
  public T bind(File propertiesFile) throws IOException {
    try (InputStream input = new FileInputStream(propertiesFile)) {
      return bind(input);
    }
  }

  /**
   * Makes a new proxy bound to the given properties. If, after binding,
   * the caller alters the contents of the properties object via her reference
   * to it, the properties that the proxy refers to are affected.
   *
   * @param properties the properties to be bound
   * @return a proxy bound to the properties
   */
  public T bind(Properties properties) {
    return evaluate(new SubstitutableProperties(properties));
  }

  /**
   * Makes a new proxy bound to the properties in the given map.
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
   * Makes a new proxy bound to the properties in the given resource bundle.
   * If, after binding, the caller alters the contents of the resource bundle,
   * the properties that the proxy refers to are affected.
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
   * property source. If, after binding, the caller affects the responses
   * the property source gives, the properties that the schema refers to
   * are affected.
   *
   * @param source the property source to be bound
   * @return a proxy bound to the property source
   * @throws NullPointerException if {@code source} is {@code null}
   */
  public T bind(PropertySource source) {
    return evaluate(source);
  }

  private T evaluate(PropertySource source) {
    return validated.evaluate(source);
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

  private static PropertySource loadProperties(InputStream input)
    throws IOException {

    SubstitutableProperties properties = new SubstitutableProperties();
    properties.load(input);
    return properties;
  }
}
