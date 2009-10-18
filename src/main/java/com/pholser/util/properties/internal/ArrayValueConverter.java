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

package com.pholser.util.properties.internal;

import static com.pholser.util.properties.internal.ValueConverterFactory.*;

import java.lang.reflect.Array;
import java.util.Properties;

class ArrayValueConverter implements ValueConverter {
    private final Class<?> componentType;
    private final ValueConverter componentTypeConverter;
    private final ValueSeparator separator;

    public ArrayValueConverter( Class<?> arrayType, ValueSeparator separator ) {
        this.componentType = arrayType.getComponentType();
        this.componentTypeConverter = createScalarConverter( componentType );
        this.separator = separator;
    }

    public Object convert( String raw ) {
        String[] pieces = separator.separate( raw );
        Object array = Array.newInstance( componentType, pieces.length );
        for ( int i = 0; i < pieces.length; ++i )
            Array.set( array, i, componentTypeConverter.convert( pieces[ i ] ) );

        return array;
    }

    public Object nilValue() {
        return Array.newInstance( componentType, 0 );
    }

    public void resolve( Properties properties ) {
        separator.resolve( properties );
    }
}
