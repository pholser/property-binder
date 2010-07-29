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

package com.pholser.util.properties.internal.conversions;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.*;

final class PrimitiveClasses {
    private static final Map<Class<?>, Class<?>> WRAPPERS;

    private static final int SMALL_PRIME = 13;

    static {
        new PrimitiveClasses();

        Map<Class<?>, Class<?>> wrappers = new HashMap<Class<?>, Class<?>>(SMALL_PRIME);
        wrappers.put(Boolean.TYPE, Boolean.class);
        wrappers.put(Byte.TYPE, Byte.class);
        wrappers.put(Character.TYPE, Character.class);
        wrappers.put(Double.TYPE, Double.class);
        wrappers.put(Float.TYPE, Float.class);
        wrappers.put(Integer.TYPE, Integer.class);
        wrappers.put(Long.TYPE, Long.class);
        wrappers.put(Short.TYPE, Short.class);
        wrappers.put(Void.TYPE, Void.class);
        WRAPPERS = unmodifiableMap(wrappers);
    }

    private PrimitiveClasses() {
        // nothing to do here
    }

    static Class<?> wrapperIfPrimitive(Class<?> clazz) {
        return WRAPPERS.containsKey(clazz) ? WRAPPERS.get(clazz) : clazz;
    }
}
