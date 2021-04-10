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

package com.pholser.util.properties.internal.separators;

import java.lang.reflect.Method;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.pholser.util.properties.PropertySource;
import com.pholser.util.properties.ValuesSeparatedBy;
import com.pholser.util.properties.internal.exceptions.MalformedSeparatorException;

import static com.pholser.util.properties.internal.Schemata.*;

class RegexValueSeparator implements ValueSeparator {
    private final Pattern regex;

    RegexValueSeparator(String pattern, Method method) {
        try {
            regex = Pattern.compile(pattern);
        } catch (PatternSyntaxException ex) {
            throw new MalformedSeparatorException(pattern, method, ex);
        }
    }

    @Override public String[] separate(String raw) {
        return regex.split(raw);
    }

    @Override public void resolve(PropertySource properties) {
        // nothing to do here
    }

    @Override public boolean isDefault() {
        return regex.pattern().equals(annotationDefault(ValuesSeparatedBy.class, "pattern"));
    }
}
