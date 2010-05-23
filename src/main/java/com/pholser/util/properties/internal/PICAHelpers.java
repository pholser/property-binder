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

package com.pholser.util.properties.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ValuesSeparatedBy;

import static com.pholser.util.properties.internal.Reflection.*;

final class PICAHelpers {
    static {
        new PICAHelpers();
    }

    private PICAHelpers() {
        // nothing to do here
    }

    static String propertyNameFor(Method method) {
        BoundProperty marker = method.getAnnotation(BoundProperty.class);
        return marker != null ? marker.value() : method.getDeclaringClass().getName() + '.' + method.getName();
    }

    static Object annotationDefault(Class<? extends Annotation> type, String methodName) {
        try {
            return type.getMethod(methodName).getDefaultValue();
        } catch (NoSuchMethodException ex) {
            throw new AssertionError(ex);
        }
    }

    static boolean isDefault(Object annotation, Class<? extends Annotation> annotationClass, String methodName) {
        Object annotationDefault = annotationDefault(annotationClass, methodName);
        return annotationDefault.equals(invokeQuietly(annotationClass, methodName, annotation));
    }

    static boolean isDefaultSeparatorValueOf(ValuesSeparatedBy separatorSpec) {
        return isDefault(separatorSpec, ValuesSeparatedBy.class, "valueOf");
    }

    static boolean isDefaultPattern(ValuesSeparatedBy separatorSpec) {
        return isDefault(separatorSpec, ValuesSeparatedBy.class, "pattern");
    }

    static boolean isDefaultDefaultValue(DefaultsTo defaultValueSpec) {
        return isDefault(defaultValueSpec, DefaultsTo.class, "value");
    }

    static boolean isDefaultDefaultValueOf(DefaultsTo defaultValueSpec) {
        return isDefault(defaultValueSpec, DefaultsTo.class, "valueOf");
    }
}
