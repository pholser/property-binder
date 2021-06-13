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

import com.pholser.util.properties.PropertyBinder;
import com.pholser.util.properties.it.boundtypes.InvalidArgs;
import com.pholser.util.properties.it.boundtypes.InvalidZeroArgReturnValue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationTest {
  @Test void invalidZeroArgReturnValue() {
    IllegalArgumentException thrown =
      assertThrows(
        IllegalArgumentException.class,
        () ->
          PropertyBinder.forType(InvalidZeroArgReturnValue.class)
            .validated()
            .bind(getClass().getResourceAsStream("/v8n.properties")));

    assertThat(thrown.getMessage())
      .matches("present.*must not be null");
  }

  @Test void invalidArgsOnMethodCall() throws Exception {
    InvalidArgs bound =
      PropertyBinder.forType(InvalidArgs.class)
        .validated()
        .bind(getClass().getResourceAsStream("/v8n.properties"));

    IllegalArgumentException thrown =
      assertThrows(
        IllegalArgumentException.class,
        () -> bound.composed(-2, -3));

    assertThat(thrown.getMessage())
      .matches(m -> m.contains("composed.y: must be greater than 0"))
      .matches(m -> m.contains("composed.x: must be greater than 0"));
  }

  // TODO: move these tests to main tests. eliminate these modules.
}
