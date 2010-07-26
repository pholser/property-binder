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

package com.pholser.util.properties.internal.separators;

import java.lang.reflect.Method;

import com.pholser.util.properties.ValuesSeparatedBy;

import static com.pholser.util.properties.internal.PICAHelpers.*;

public class ValueSeparatorFactory {
    public ValueSeparator createSeparator(ValuesSeparatedBy separatorSpec, Method method) {
        Object patternDefault = annotationDefault(ValuesSeparatedBy.class, "pattern");
        return separatorSpec == null
            ? new RegexValueSeparator(patternDefault.toString(), method)
            : createSeparatorBasedOnSpec(separatorSpec, method);
    }

    private ValueSeparator createSeparatorBasedOnSpec(ValuesSeparatedBy separatorSpec, Method method) {
        if (isDefaultPattern(separatorSpec) && !isDefaultSeparatorValueOf(separatorSpec))
            return new SubstitutableRegexValueSeparator(separatorSpec.valueOf(), method);
        return new RegexValueSeparator(separatorSpec.pattern(), method);
    }
}