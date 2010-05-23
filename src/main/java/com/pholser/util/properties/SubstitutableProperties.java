/*
 The MIT License

 Copyright (c) 2009 Paul R. Holser, Jr.

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

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pholser.util.properties.internal.Objects.*;

/**
 * Properties class with support for property values which can be comprised of the values of other properties.
 * Such values specify other property keys delimited by {@code [} and {@code ]}.
 *
 * Inspired by <a href="http://www2.sys-con.com/ITSG/virtualcd/Java/archives/0612/mair/index.html">Enabling
 * Constant Substitution in Property Values</a>.
 *
 * @author <a href="http://www.pholser.com">Paul Holser</a>
 */
public class SubstitutableProperties extends Properties {
    private static final long serialVersionUID = 1L;
    private static final Pattern REFERENCE = Pattern.compile("\\[(.*?)\\]");

    @Override
    public String getProperty(String key) {
        return substitute(super.getProperty(key), this);
    }

    /**
     * Performs property value substitution on the given value, drawing on the values of the given collection of
     * properties. If a property reference cannot be resolved, it will be replaced with the zero-length string.
     *
     * @param value the value to perform substitution on
     * @param properties the properties from which to draw substitution values
     * @return the result of the substitution
     */
    public static String substitute(String value, Properties properties) {
        String previous = null;
        String substituted = value;

        while (!areEqual(previous, substituted)) {
            previous = substituted;
            substituted = substituteReferences(previous, properties);
        }

        return substituted;
    }

    private static String substituteReferences(String value, Properties properties) {
        Matcher matcher = REFERENCE.matcher(value);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String reference = properties.getProperty(matcher.group(1));
            matcher.appendReplacement(buffer, reference == null ? "" : reference);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
