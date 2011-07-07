/*
 The MIT License

 Copyright (c) 2009-2011 Paul R. Holser, Jr.

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

package com.pholser.util.properties.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ValuesSeparatedBy;

import static com.pholser.util.properties.internal.Reflection.*;

public final class Schemata {
    private Schemata() {
        throw new UnsupportedOperationException();
    }

    public static String propertyNameFor(Method method) {
        BoundProperty marker = method.getAnnotation(BoundProperty.class);
        return marker != null ? marker.value() : method.getDeclaringClass().getName() + '.' + method.getName();
    }

    public static Object annotationDefault(Class<? extends Annotation> clazz, String methodName) {
        try {
            return clazz.getMethod(methodName).getDefaultValue();
        } catch (NoSuchMethodException ex) {
            throw new AssertionError(ex);
        }
    }

    private static boolean isDefault(Object annotation, Class<? extends Annotation> clazz, String methodName) {
        Object annotationDefault = annotationDefault(clazz, methodName);
        return annotationDefault.equals(invokeQuietly(clazz, methodName, annotation));
    }

    public static boolean isDefaultSeparatorValueOf(ValuesSeparatedBy spec) {
        return isDefault(spec, ValuesSeparatedBy.class, "valueOf");
    }

    public static boolean isDefaultPattern(ValuesSeparatedBy spec) {
        return isDefault(spec, ValuesSeparatedBy.class, "pattern");
    }

    public static boolean isDefaultDefaultValue(DefaultsTo spec) {
        return isDefault(spec, DefaultsTo.class, "value");
    }

    public static boolean isDefaultDefaultValueOf(DefaultsTo spec) {
        return isDefault(spec, DefaultsTo.class, "valueOf");
    }
}
