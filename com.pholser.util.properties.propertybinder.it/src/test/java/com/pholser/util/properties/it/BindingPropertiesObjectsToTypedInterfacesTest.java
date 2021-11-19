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

import com.pholser.util.properties.it.boundtypes.ScalarProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import static com.pholser.util.properties.it.IO.closeQuietly;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BindingPropertiesObjectsToTypedInterfacesTest
  extends TypedStringBindingTestSupport<ScalarProperties> {

  private InputStream inputStream;
  private Properties props;
  private ScalarProperties fromObject;

  BindingPropertiesObjectsToTypedInterfacesTest() {
    super("/test.properties", "test", "properties");
  }

  @BeforeEach final void initializeProperties() throws Exception {
    inputStream = new FileInputStream(propertiesFile);
    props = new Properties();
    props.load(inputStream);
    fromObject = binder.bind(props);
  }

  @AfterEach final void closeInputStream() {
    closeQuietly(inputStream);
  }

  @Test void loadingFromPropertiesObject() throws Exception {
    ScalarProperties fromFile =
      binder.bind(new FileReader(propertiesFile, UTF_8));

    assertPropertiesEqual(fromFile, fromObject);
  }

  @Test
  void alteringPropertiesAfterBindingDoesNotAffectPropertiesBoundToPICA() {
    props.setProperty("big.decimal.property", "!@#!@#!@#!@#!@#");

    assertThrows(
      IllegalArgumentException.class,
      () -> fromObject.bigDecimalProperty());
  }

  @Override protected Class<ScalarProperties> boundType() {
    return ScalarProperties.class;
  }
}
