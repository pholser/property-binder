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

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * Mark an interface method with this annotation to indicate a default value for the properties file key
 * represented by the method. If no property is associated with the key for the method, the default value will be
 * returned instead. The default can be a plain value given by {@link #value()} or a value comprised in whole or
 * in part of the values of other properties, given by {@link #valueOf()}. References to other properties in a
 * {@code valueOf()} expression are delimited by {@code [} and {@code ]}.
 *
 * Default values for aggregate types can use a separator given by {@link ValuesSeparatedBy}.
 *
 * @author <a href="http://www.pholser.com">Paul Holser</a>
 * @see BoundProperty
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface DefaultsTo {
    /**
     * String representation of the default value for the properties file key associated with the marked method.
     */
    String value() default "";

    /**
     * Expression to give the string representation of a default value for the properties file key associated
     * with the marked method. This expression can be given in terms of property references.
     */
    String valueOf() default "";
}
