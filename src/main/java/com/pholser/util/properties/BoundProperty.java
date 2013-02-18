/*
 The MIT License

 Copyright (c) 2009-2013 Paul R. Holser, Jr.

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

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * Mark an interface method with this annotation to indicate that it should return the value associated with a
 * given key from a property source, converted to the type indicated by the method's return type.
 *
 * @author <a href="http://www.pholser.com">Paul Holser</a>
 * @see DefaultsTo
 * @see ValuesSeparatedBy
 * @see ParsedAs
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface BoundProperty {
    /**
     * The property source key to associate with the marked method.
     *
     * @return a property key
     */
    String value();

    /**
     * <p>Tells whether substitution should be suppressed when resolving this property.</p>
     *
     * <p>This would be useful if, for example, you expect the value of your property to be a regex.</p>
     *
     * @return {@code true} if substitution should be suppressed
     */
    boolean suppressSubstitution() default false;
}
