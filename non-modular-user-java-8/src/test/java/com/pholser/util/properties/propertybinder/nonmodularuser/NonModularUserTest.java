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

package com.pholser.util.properties.propertybinder.nonmodularuser;

import com.pholser.util.properties.PropertyBinder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import static java.nio.charset.StandardCharsets.UTF_8;

class NonModularUserTest {
  private static final Logger LOG =
    LoggerFactory.getLogger(NonModularUserTest.class);

  @Test void exerciseProperties() throws Exception {
    InputStream propsIn =
      NonModularUserTest.class.getResourceAsStream("/test.properties");
    PropertyBinder<Config> binder = PropertyBinder.forType(Config.class);

    Config config = binder.bind(new InputStreamReader(propsIn, UTF_8));

    for (Method each : Config.class.getMethods()) {
      LOG.info("{} = {}", each, each.invoke(config));
    }
  }
}
