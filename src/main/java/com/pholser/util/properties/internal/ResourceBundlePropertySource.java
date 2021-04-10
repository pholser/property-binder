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

package com.pholser.util.properties.internal;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.PropertySource;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static com.pholser.util.properties.internal.Substitutions.maybeSubstitute;

public class ResourceBundlePropertySource implements PropertySource {
    private final ResourceBundle backing;

    public ResourceBundlePropertySource(ResourceBundle backing) {
        if (backing == null)
            throw new NullPointerException("null backing");

        this.backing = backing;
    }

    @Override public Object propertyFor(BoundProperty key) {
        try {
            return maybeSubstitute(this, key, backing.getObject(key.value()));
        } catch (MissingResourceException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        return backing.toString();
    }
}
