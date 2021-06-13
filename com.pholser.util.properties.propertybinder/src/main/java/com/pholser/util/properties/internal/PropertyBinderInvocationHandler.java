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

package com.pholser.util.properties.internal;

import com.pholser.util.properties.PropertySource;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static java.lang.System.identityHashCode;

class PropertyBinderInvocationHandler implements InvocationHandler {
  private final PropertySource properties;
  private final Schema<?> schema;

  PropertyBinderInvocationHandler(
    PropertySource properties,
    Schema<?> schema) {

    this.properties = properties;
    this.schema = schema;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args)
    throws Throwable {

    if (isEquals(method)) {
      return proxy == args[0];
    }
    if (isHashCode(method)) {
      return identityHashCode(proxy);
    }
    if (isToString(method)) {
      return handleToString();
    }

    if (method.isDefault()) {
      return MethodHandles.lookup()
        .findSpecial(
          method.getDeclaringClass(),
          method.getName(),
          MethodType.methodType(
            method.getReturnType(),
            method.getParameterTypes()),
          method.getDeclaringClass())
        .bindTo(proxy)
        .invokeWithArguments(args);
    }

    return schema.convert(proxy, properties, method, args);
  }

  private boolean isEquals(Method method) {
    return "equals".equals(method.getName())
      && method.getParameterCount() == 1
      && Object.class.equals(method.getParameterTypes()[0]);
  }

  private boolean isHashCode(Method method) {
    return "hashCode".equals(method.getName())
      && method.getParameterCount() == 0;
  }

  private boolean isToString(Method method) {
    return "toString".equals(method.getName())
      && method.getParameterCount() == 0;
  }

  private String handleToString() {
    return schema.getName() + '[' + properties + ']';
  }
}
