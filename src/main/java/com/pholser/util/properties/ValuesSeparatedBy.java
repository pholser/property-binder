/*
 The MIT License

 Copyright (c) 2009 Paul R. Holser, Jr.

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
 * Mark an interface method with this annotation to indicate a regular expression that separates multiple values
 * of the value for the properties file key indicated by {@link BoundProperty}. This makes sense only on methods
 * which return array types or {@link java.util.List}s. If the method is not marked with this annotation, the
 * separator is taken to be "{@code ,}" (single comma, no surrounding whitespaces).

 * Use {@link #pattern()} to specify a plain regular expression, or {@link #valueOf()} to specify a regular
 * expression parts of which can be comprised of the values of bound properties. The keys of such properties are
 * delimited in the pattern by {@code [} and {@code ]}. It is illegal to specify both {@link #pattern()} and
 * {@link #valueOf()} at the same time with values other than the default.
 *
 * @author <a href="http://www.pholser.com">Paul Holser</a>
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface ValuesSeparatedBy {
    /**
     * A {@linkplain java.util.regex.Pattern regular expression} specifying a separator to be used on values of
     * a given property.
     */
    String pattern() default ",";

    /**
     * An expression evaluating to a {@linkplain java.util.regex.Pattern regular expression} specifying a
     * separator to be used on values of a given property. This expression can be given in terms of property
     * references.
     */
    String valueOf() default "";
}
