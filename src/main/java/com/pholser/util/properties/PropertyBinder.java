/*
 The MIT License

 Copyright (c) 2009-2010 Paul R. Holser, Jr.

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import com.pholser.util.properties.internal.ResourceBundlePropertySource;

import com.pholser.util.properties.internal.MapPropertySource;

import com.pholser.util.properties.internal.ValidatedSchema;
import com.pholser.util.properties.internal.validation.SchemaValidator;

import static com.pholser.util.properties.internal.IO.*;

/**
 * <p>Creates proxies that provide typed access to values in {@linkplain PropertySource sources of property
 * configuration} via the <acronym title="Proxied Interfaces Configured with Annotations">PICA</acronym>
 * technique.</p>
 *
 * Inspired by <a href="http://lemnik.wordpress.com/2007/03/28/code-at-runtime-in-java-56/">this blog entry</a>.
 *
 * <p>The {@link Class} object given to a binder at {@link PropertyBinder#PropertyBinder(Class) construction time}
 * should be an interface with no super-interfaces. It is referred to as a "schema". A schema's methods prescribe the
 * keys of a source of property configuration and the intended types of their associated values. Binding a source of
 * property configuration to the schema gives the caller a proxy that implements the schema's interface. Invoking a
 * proxy method attempts to retrieve the value associated with the key prescribed by the invoked method, and to
 * convert the value to the type prescribed by the method's return value.</p>
 *
 * <p>Every schema method maps to a property key. A method can be marked with {@link BoundProperty} to indicate the
 * property key to be used when the method is invoked; if it is not so marked, the key used is:
 * fully qualified name of the schema interface + '.' + the method's name.</p>
 *
 * <p>Prior to version 3, binders accepted only {@link Properties} and {@link java.util.Map Map}s of string keys
 * to string values. Beginning with version 3, binders admit sources of property configuration that map string keys
 * to values of unknown type. If a given value in such a source happens to be a string, that value will undergo
 * conversion to the method's return type if possible. Any problems with such a conversion will result in runtime
 * type errors.</p>
 *
 * <p>In order for a given property value to be converted from a string, the associated schema method should have a
 * return type that is a value type, an array of value types, or a {@link java.util.List} of value types. A
 * <dfn>value type</dfn> is any primitive type, primitive wrapper type, or type which possesses either:</p>
 *
 * <ul>
 *   <li>a {@code public static} method called {@code valueOf} which takes one argument, of type {@link String},
 *   and whose return type is the type itself</li>
 *   <li>a {@code public} constructor which takes one argument, of type {@link String}</li>
 * </ul>
 *
 * <p>If a value type has both of these, the {@code valueOf} method takes priority over the constructor. Note that
 * {@code enum}s have a {@code valueOf} method.</p>
 *
 * <p>The supported aggregate return types for schema methods are arrays and {@link java.util.List}. A List type
 * may be a raw List or a {@code List<?>}. If the underlying property's value is a string, the elements of the list
 * will undergo conversion from the string; otherwise the value is assumed to be a list and is left untouched.</li>
 *
 * <p>The {@link ValuesSeparatedBy} annotation can be applied only to schema methods with an aggregate return type,
 * and must specify a well-formed {@linkplain java.util.regex.Pattern regular expression} as a separator.</p>
 *
 * <p>If a schema method specifies a default value via {@link DefaultsTo}, that value must be convertible to the
 * return type of the schema method via the above rules.
 *
 * <p>If a schema method accepts no arguments, the property's value will be converted via the above rules. If the
 * schema method accepts one or more arguments, the property's value is treated as a {@linkplain
 * String#format(String, Object...) format string}, and the arguments assigned to the format specifiers accordingly,
 * before the entire value is converted. If the property's value is not a string, any arguments to the schema method
 * are ignored.</p>
 *
 * <p>Schema methods returning {@link java.util.Date} can be annotated with {@link ParsedAs} to indicate that the
 * corresponding property's value(s) should be parsed using the given {@linkplain java.text.SimpleDateFormat date
 * patterns}.
 *
 * <p>After binding, invoking a schema method returns the value of the property it represents if that property is
 * present; else the value given by the method's {@link DefaultsTo} marker if present; else {@code null} for scalar
 * types, a zero-length array for array types, and an {@linkplain java.util.Collection#isEmpty() empty list} for
 * list types. If the schema method returns a primitive type and neither a property nor its default is present, the
 * method will raise {@link NullPointerException}.</p>
 *
 * @param <T> the type of accessor proxies the binder is to create
 * @author <a href="http://www.pholser.com">Paul Holser</a>
 */
public class PropertyBinder<T> {
    private final ValidatedSchema<T> validated;

    /**
     * Creates a new property binder from the given schema.
     *
     * @param schema the PICA type used to create and configure accessor proxies
     * @throws NullPointerException if {@code schema} is {@code null}
     * @throws IllegalArgumentException if {@code schema}'s configuration is invalid in any way
     */
    public PropertyBinder(Class<T> schema) {
        validated = new SchemaValidator().validate(schema);
    }

    /**
     * Factory method for property binders. Leverages generics type inference so that if you don't wish to specify
     * the type more than once at {@linkplain PropertyBinder#PropertyBinder(Class) construction time}, you can call
     * this method instead (at the expense of not offering a seam for testing).
     *
     * @param <U> the type of property accessor objects the binder is to create
     * @param schema the PICA type used to create and configure accessor proxies
     * @return a new property binder that binds proxies to sources of property configuration
     * @throws NullPointerException if {@code schema} is {@code null}
     * @throws IllegalArgumentException if {@code schema}'s configuration is invalid in any way
     */
    public static <U> PropertyBinder<U> forType(Class<U> schema) {
        return new PropertyBinder<U>(schema);
    }

    /**
     * Makes a new proxy bound to the properties purported to be in the given input stream.
     *
     * @see #bind(File)
     * @param propertyInput an input stream containing properties to be bound
     * @return a proxy bound to the properties
     * @throws IOException if there is a problem reading from the input stream
     * @throws NullPointerException if {@code propertyInput} is {@code null}
     */
    public T bind(InputStream propertyInput) throws IOException {
        return evaluate(loadProperties(propertyInput));
    }

    /**
     * Makes a new proxy bound to the properties purported to be in the given file.
     *
     * @see #bind(InputStream)
     * @param propertiesFile a file containing properties to be bound
     * @return a proxy bound to the properties
     * @throws IOException if there is a problem reading from the file
     * @throws NullPointerException if {@code propertiesFile} is {@code null}
     */
    public T bind(File propertiesFile) throws IOException {
        FileInputStream input = null;

        try {
            input = new FileInputStream(propertiesFile);
            return bind(input);
        } finally {
            closeQuietly(input);
        }
    }

    /**
     * Makes a new proxy bound to the given properties.
     *
     * If the caller alters the contents of the properties object via her reference to it, the properties that the
     * proxy refers to are affected.
     *
     * @param properties the properties to be bound
     * @return a proxy bound to the properties
     */
    public T bind(Properties properties) {
        return evaluate(new SubstitutableProperties(properties));
    }

    /**
     * Makes a new proxy bound to the properties in the given map.
     *
     * If the caller alters the contents of the map via her reference to it, the properties that the proxy refers to
     * are affected.
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
     *
     * If the caller manages to alter the contents of the resource bundle somehow, the properties that the proxy
     * refers to are affected.
     *
     * @param bundle the bundle to be bound
     * @return a proxy bound to the bundle
     * @throws NullPointerException if {@code bundle} is {@code null}
     */
    public T bind(ResourceBundle bundle) {
        return evaluate(new ResourceBundlePropertySource(bundle));
    }

    /**
     * Makes a new proxy bound to the properties represented by the given property source.
     *
     * If the caller manages to affect somehow the responses the property source gives, the properties that the PICA
     * refers to are affected.
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
     * Makes a new proxy bound to {@linkplain System#getProperties() system properties}.
     *
     * @return a proxy bound to system properties
     */
    public static SystemProperties getSystemProperties() {
        return forType(SystemProperties.class).bind(System.getProperties());
    }

    private static SubstitutableProperties loadProperties(InputStream input) throws IOException {
        SubstitutableProperties properties = new SubstitutableProperties();
        properties.load(input);
        return properties;
    }
}
