package com.pholser.util.properties.it;

import java.lang.reflect.Method;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;

public final class Reflection {
  public static boolean acceptablePropertyMethodAccessLevel(Method method) {
    if (method.isDefault()) {
      return false;
    }

    int mods = method.getModifiers();
    return isPublic(mods) && isAbstract(mods)
      && !isStatic(mods) && !isPrivate(mods);
  }
}
