package com.pholser.util.properties.propertybinder.nonmodularuser;

import java.io.InputStream;
import java.lang.reflect.Method;

import com.pholser.util.properties.PropertyBinder;
import org.junit.jupiter.api.Test;

class NonModularUserTest {
  @Test void exerciseProperties() throws Exception {
    InputStream propsIn =
      NonModularUserTest.class.getResourceAsStream("/test.properties");
    PropertyBinder<Config> binder = PropertyBinder.forType(Config.class);

    Config config = binder.bind(propsIn);

    for (Method each : Config.class.getMethods()) {
      System.out.println(each + " = " + each.invoke(config));
    }
  }
}
