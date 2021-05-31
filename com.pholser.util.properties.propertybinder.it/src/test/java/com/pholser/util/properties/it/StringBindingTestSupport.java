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

import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

abstract class StringBindingTestSupport {
  protected File propertiesFile;

  private final String propertiesResourcePath;
  private final String basename;
  private final String extension;

  protected StringBindingTestSupport(
    String propertiesResourcePath,
    String basename,
    String extension) {

    this.propertiesResourcePath = propertiesResourcePath;
    this.basename = basename;
    this.extension = extension;
  }

  @BeforeEach final void initializePropertiesFile() throws IOException {
    InputStream propertiesIn =
      getClass().getResourceAsStream(propertiesResourcePath);
    File tempFile = File.createTempFile(basename, extension);
    propertiesIn.transferTo(new FileOutputStream(tempFile));
    propertiesFile = tempFile;
  }
}
