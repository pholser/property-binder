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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;

public final class Reflection {
  private Reflection() {
    throw new UnsupportedOperationException();
  }

  public static Object invokeQuietly(
    Class<?> clazz,
    String methodName,
    Object target,
    Object... args) {

    try {
      return invokeQuietly(clazz.getMethod(methodName), target, args);
    } catch (NoSuchMethodException ex) {
      throw new AssertionError(ex);
    }
  }

  public static Object invokeQuietly(
    Method method,
    Object target,
    Object... args) {

    try {
      return method.invoke(target, args);
    } catch (IllegalAccessException ex) {
      throw new IllegalArgumentException(ex);
    } catch (InvocationTargetException ex) {
      throw new IllegalArgumentException(ex.getTargetException());
    }
  }

  public static boolean acceptablePropertyMethodAccessLevel(Method method) {
    if (method.isDefault()) {
      return false;
    }

    int mods = method.getModifiers();
    return isPublic(mods) && isAbstract(mods)
      && !isStatic(mods) && !isPrivate(mods);
  }
}
