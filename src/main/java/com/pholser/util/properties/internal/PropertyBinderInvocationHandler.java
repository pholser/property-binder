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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;

import static java.lang.System.*;

public class PropertyBinderInvocationHandler implements InvocationHandler {
    private final Properties properties;
    private final ValidatedSchema<?> validatedSchema;

    public PropertyBinderInvocationHandler(Properties properties, ValidatedSchema<?> validatedSchema) {
        this.properties = properties;
        this.validatedSchema = validatedSchema;
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        if (Object.class.equals(method.getDeclaringClass()))
            return handleObjectMethod(proxy, method, args);

        return validatedSchema.convert(properties, method, args);
    }

    private Object handleObjectMethod(Object proxy, Method method, Object[] args) {
        if ("equals".equals(method.getName()))
            return proxy == args[0];

        if ("hashCode".equals(method.getName()))
            return identityHashCode(proxy);

        return handleToString();
    }

    private String handleToString() {
        return validatedSchema.getName() + '[' + properties + ']';
    }
}
