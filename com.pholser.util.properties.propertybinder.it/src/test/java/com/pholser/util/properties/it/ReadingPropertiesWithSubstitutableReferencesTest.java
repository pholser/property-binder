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

import com.pholser.util.properties.SubstitutableProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadingPropertiesWithSubstitutableReferencesTest {
  private Properties substitutable;

  @BeforeEach void setUp() {
    substitutable = new SubstitutableProperties();
  }

  @Test void referencesToPropertiesInTheCollection() {
    substitutable.setProperty("one", "1");
    substitutable.setProperty("two", "2");
    substitutable.setProperty("first", "look out for number [one]");
    substitutable.setProperty("second", "We're #[two]");

    assertEquals("look out for number 1", substitutable.getProperty("first"));
    assertEquals("We're #2", substitutable.getProperty("second"));
  }

  @Test void depthOfReferences() {
    substitutable.setProperty("one", "1");
    substitutable.setProperty("two", "2[one]");
    substitutable.setProperty("three", "3[one]3[two]");
    substitutable.setProperty("four", "4[three]4[two]4[one]");

    assertEquals("43132142141", substitutable.getProperty("four"));
  }

  @Test void regexMetacharactersInPropertyReferences() {
    substitutable.setProperty("se.pa.ra.ted", "1");
    substitutable.setProperty("reference", "3 + [se.pa.ra.ted]");

    assertEquals("3 + 1", substitutable.getProperty("reference"));
  }

  @Test void replacingUnresolvedPropertyReferences() {
    substitutable.setProperty("other.reference", "4 + [se_pa_ra_ted]");

    assertEquals("4 + ", substitutable.getProperty("other.reference"));
  }
}
