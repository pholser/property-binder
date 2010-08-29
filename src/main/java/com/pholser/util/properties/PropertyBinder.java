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
import java.util.Properties;

import com.pholser.util.properties.internal.ValidatedSchema;
import com.pholser.util.properties.internal.validation.SchemaValidator;

import static com.pholser.util.properties.internal.IO.*;

/**
 * Creates instances of proxies that provide typed access to values in properties files via the <acronym
 * title="Proxied Interfaces Configured with Annotations">PICA</acronym> technique.
 *
 * Inspired by <a href="http://lemnik.wordpress.com/2007/03/28/code-at-runtime-in-java-56/">this blog entry</a>.
 *
 * @param <T> the type of bound property accessor objects this binder creates
 * @author <a href="http://www.pholser.com">Paul Holser</a>
 */
public class PropertyBinder<T> {
    private final ValidatedSchema<T> validated;

    /**
     * Creates a new properties file accessor from the given PICA schema.
     *
     * The PICA schema is validated to ensure that there are no inconsistencies. Here are the constraints placed
     * on the PICA schema:
     *
     * <ol>
     * <li>Must be an interface with no superinterfaces.</li>
     *
     * <li>Every method maps to a properties file key. A method can be marked with {@link BoundProperty} to indicate
     * the properties file key; if it is not, the key is the fully qualified name of the PICA interface + '.' + the
     * method's name.</li>
     *
     * <li>A method's return type must be a <dfn>value type</dfn>, an array of value types, or a List of value
     * types. A value type is any primitive type (except {@code void}), primitive wrapper type (except
     * {@link Void}), or type which possesses either:
     *
     * <ul>
     * <li>a {@code public static} method called {@code valueOf} which takes one argument, of type
     * {@link String}, and whose return type is the type itself</li>
     *
     * <li>a {@code public} constructor which takes one argument, of type {@link String}</li>
     * </ul>
     *
     * If a value type has both of these, the {@code valueOf} method takes priority over the constructor. Note
     * that {@code enum}s have a {@code valueOf} method.</li>
     *
     * <li>The supported aggregate return types are arrays and {@link java.util.List}.</li>
     *
     * <li>The {@link ValuesSeparatedBy} annotation can be applied only to methods with an aggregate return
     * type, and must specify a well-formed {@linkplain java.util.regex.Pattern regular expression} as a
     * separator.</li>
     *
     * <li>A List type may be a raw List or a {@code List<?>}. The elements are {@code String}s in such cases.</li>
     *
     * <li>If a method specifies a default value via {@link DefaultsTo}, that value must be convertible to the
     * return type of the method.</li>
     *
     * <li>If a method accepts no arguments, the property's value will be converted as is to the type specified
     * by the method's return type. If the method accepts one or more arguments, the property's value is treated
     * as a {@linkplain String#format(String, Object...) format string}, and the arguments assigned to the format
     * specifiers accordingly, before the entire value is converted.</li>
     *
     * <li>Methods returning {@link java.util.Date} can be annotated with {@link ParsedAs} to indicate that the
     * corresponding property's value(s) should be parsed using the given {@linkplain java.text.SimpleDateFormat
     * date patterns}.
     * </ol>
     *
     * @param schema the PICA type used to configure the accessor and provide access to properties
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
     * @param <U> the type of bound property accessor objects this binder creates
     * @param schema the PICA type used to configure the accessor and provide access to properties
     * @return a new property binder that binds instances of {@code} schema to properties objects
     * @throws NullPointerException if {@code schema} is {@code null}
     * @throws IllegalArgumentException if {@code schema}'s configuration is invalid in any way
     */
    public static <U> PropertyBinder<U> forType(Class<U> schema) {
        return new PropertyBinder<U>(schema);
    }

    /**
     * Binds the properties purported to be in the given input stream to an instance of this binder's PICA.
     *
     * @see #bind(File)
     * @param propertyInput an input stream containing properties to be bound
     * @return a PICA instance bound to the properties
     * @throws IOException if there is a problem reading from the input stream
     * @throws NullPointerException if {@code propertyInput} is {@code null}
     */
    public T bind(InputStream propertyInput) throws IOException {
        return evaluate(loadProperties(propertyInput));
    }

    /**
     * Binds the properties purported to be in the given file to an instance of this binder's PICA.
     *
     * After binding, invoking a PICA method returns the value of the property it represents if that property is
     * present; else the value given by the method's {@link DefaultsTo} marker if present; else {@code null} for
     * scalar types, a zero-length array for array types, and an {@linkplain java.util.Collection#isEmpty()
     * empty list} for list types. If the PICA method returns a primitive type and neither a property nor its
     * default is present, the PICA method will raise {@link NullPointerException}.
     *
     * @see #bind(InputStream)
     * @param propertiesFile a file containing properties to be bound
     * @return a PICA instance bound to the properties
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
     * Binds the given properties to an instance of this binder's PICA.
     *
     * This method binds the PICA to a clone of the given properties. If the caller alters the contents of the
     * properties object via her reference to it, the properties that the PICA refers to remain unchanged.
     *
     * @param properties the properties to be bound
     * @return a PICA instance bound to the properties
     * @throws NullPointerException if {@code properties} is {@code null}
     */
    public T bind(Properties properties) {
        return evaluate(new SubstitutableProperties(properties));
    }

    private T evaluate(SubstitutableProperties properties) {
        return validated.evaluate(properties);
    }

    /**
     * Gives a typed accessor for {@linkplain System#getProperties() system properties}.
     *
     * @return a typed system property accessor
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
