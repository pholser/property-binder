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

import java.io.FileInputStream;
import java.io.InputStream;

import com.pholser.util.properties.boundtypes.ScalarPropertyHaver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.pholser.util.properties.internal.IO.*;

public class BindingPropertiesReadFromInputStreamTest
    extends TypedStringBindingTestSupport<ScalarPropertyHaver> {

    private InputStream inputStream;

    @Before public final void initializeInputStream() throws Exception {
        inputStream = new FileInputStream(propertiesFile);
    }

    @After public final void closeInputStream() {
        closeQuietly(inputStream);
    }

    @Test public void loadingFromInputStream() throws Exception {
        ScalarPropertyHaver fromInputStream = binder.bind(inputStream);

        assertPropertiesEqual(bound, fromInputStream);
    }

    @Override protected Class<ScalarPropertyHaver> boundType() {
        return ScalarPropertyHaver.class;
    }
}
