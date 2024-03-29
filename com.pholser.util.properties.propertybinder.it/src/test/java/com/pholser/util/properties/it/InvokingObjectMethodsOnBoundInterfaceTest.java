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

package com.pholser.util.properties.it;


import com.pholser.util.properties.BoundProperty;
import org.junit.jupiter.api.Test;

import java.io.FileReader;

import static java.lang.System.identityHashCode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InvokingObjectMethodsOnBoundInterfaceTest
  extends TypedStringBindingTestSupport<InvokingObjectMethodsOnBoundInterfaceTest.PropertyFacade> {

  InvokingObjectMethodsOnBoundInterfaceTest() {
    super("/test.properties", "test", "properties");
  }

  @Test void answeringEquals() throws Exception {
    PropertyFacade second =
      binder.bind(new FileReader(propertiesFile, UTF_8));

    assertEquals(bound, bound);
    assertNotEquals(bound, second);
    assertEquals(second, second);
    assertNotEquals(second, bound);
  }

  @Test void answeringHashCode() {
    assertEquals(identityHashCode(bound), bound.hashCode());
  }

  @Test void answeringToString() {
    assertThat(bound.toString()).contains("wrapped.integer");
  }

  @Override protected Class<PropertyFacade> boundType() {
    return PropertyFacade.class;
  }

  interface PropertyFacade {
    @BoundProperty(value = "string.property") String stringProperty();
  }
}
