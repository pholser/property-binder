/*
 The MIT License

 Copyright (c) 2009-2013 Paul R. Holser, Jr.

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

import java.lang.reflect.InvocationTargetException;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ExceptionMatchers {
    private ExceptionMatchers() {
        throw new UnsupportedOperationException();
    }

    public static Matcher<Throwable> causeOfType(final Class<? extends Throwable> type) {
        return new TypeSafeMatcher<Throwable>() {
            @Override public boolean matchesSafely(Throwable item) {
                return type.isInstance(item.getCause());
            }

            @Override public void describeTo(Description description) {
                description.appendText("exception with cause whose type is ");
                description.appendValue(type);
            }
        };
    }

    public static Matcher<InvocationTargetException> targetOfType(final Class<? extends Throwable> type) {
        return new org.hamcrest.TypeSafeMatcher<InvocationTargetException>() {
            @Override public boolean matchesSafely(InvocationTargetException item) {
                return type.isInstance(item.getTargetException());
            }

            @Override public void describeTo(Description description) {
                description.appendText("InvocationTargetException with target whose type is ");
                description.appendValue(type);
            }
        };
    }
}
