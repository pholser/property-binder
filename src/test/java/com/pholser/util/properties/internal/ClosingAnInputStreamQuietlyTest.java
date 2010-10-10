/*
 The MIT License

 Copyright (c) 2009-2010 Paul R. Holser, Jr.

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

package com.pholser.util.properties.internal;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

import static com.pholser.util.properties.internal.IO.*;
import static org.junit.Assert.*;

public class ClosingAnInputStreamQuietlyTest {
    @Test
    public void doingNothingIfStreamNull() {
        closeQuietly(null);
    }

    @Test
    public void closingWithoutIncidentIfStreamNotNullAndNoExceptionThrown() {
        FakeInputStream fake = new FakeInputStream();

        closeQuietly(fake);

        assertTrue(fake.closed);
    }

    @Test
    public void closingWithoutIncidentIfStreamNotNullAndExceptionThrown() {
        FakeInputStream fake = new PukeOnCloseInputStream();

        closeQuietly(fake);

        assertTrue(fake.closed);
    }

    private static class FakeInputStream extends ByteArrayInputStream {
        private static final byte[] BUFFER = new byte[0];

        boolean closed;

        FakeInputStream() {
            super(BUFFER);
        }

        @Override
        public void close() throws IOException {
            closed = true;
            super.close();
        }
    }

    private static class PukeOnCloseInputStream extends FakeInputStream {
        @Override
        public void close() throws IOException {
            super.close();
            throw new IOException();
        }
    }
}
