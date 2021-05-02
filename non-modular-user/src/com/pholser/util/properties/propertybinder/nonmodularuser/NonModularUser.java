package com.pholser.util.properties.propertybinder.nonmodularuser;

import java.io.InputStream;
import com.pholser.util.properties.PropertyBinder;

public class NonModularUser {
  public static void main(String[] args) throws Exception {
    InputStream propsIn =
      NonModularUser.class.getResourceAsStream("/test.properties");
    PropertyBinder<Config> binder = PropertyBinder.forType(Config.class);

    Config config = binder.bind(propsIn);

    System.out.println(config.enumProperty());
  }

}
