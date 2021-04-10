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

package com.pholser.util.properties;

import com.pholser.util.properties.boundtypes.ScalarPropertyHaver;
import com.pholser.util.properties.internal.exceptions.ValueConversionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static com.pholser.util.properties.internal.IO.closeQuietly;
import static org.junit.Assert.assertThrows;

public class BindingStringMapsToTypedInterfacesTest
  extends TypedStringBindingTestSupport<ScalarPropertyHaver> {

  private InputStream inputStream;
  private Map<String, String> asStringMap;
  private ScalarPropertyHaver fromMap;

  @SuppressWarnings("unchecked")
  @Before public final void initializeProperties() throws Exception {
    inputStream = new FileInputStream(propertiesFile);
    Properties props = new Properties();
    props.load(inputStream);
    Map<?, ?> asMap = props;
    asStringMap = (Map<String, String>) asMap;
    fromMap = binder.bind(asStringMap);
  }

  @After public final void closeInputStream() {
    closeQuietly(inputStream);
  }

  @Test public void loadingFromPropertiesObject() throws Exception {
    ScalarPropertyHaver fromFile = binder.bind(propertiesFile);

    assertPropertiesEqual(fromFile, fromMap);
  }

  @Test public void alteringMapAfterBindingAffectsPropertiesBoundToPICA() {
    asStringMap.put("primitive.integer.property", "!@#!@#!@#!@#!@#");

    assertThrows(
      ValueConversionException.class,
      () -> fromMap.primitiveIntegerProperty());
  }

  @Override protected Class<ScalarPropertyHaver> boundType() {
    return ScalarPropertyHaver.class;
  }
}
