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

import static com.pholser.util.properties.internal.PrimitiveClasses.*;
import static java.lang.reflect.Modifier.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

import com.pholser.util.properties.internal.exceptions.UnsupportedValueTypeException;

class ValueConverterFactory {
    ValueConverter createConverter( Method propertyMethod, Pattern separator ) {
        Class<?> valueType = targetTypeFor( propertyMethod );

        if ( valueType.isArray() )
            return new ArrayValueConverter( valueType, separator );

        if ( List.class.equals( valueType ) )
            return new ListValueConverter( propertyMethod.getGenericReturnType(), separator );

        return createScalarConverter( valueType );
    }

    static ValueConverter createScalarConverter( Class<?> valueType ) {
        Class<?> returnType = wrapperIfPrimitive( valueType );

        ValueConverter valueOf = valueOfConverter( returnType );
        if ( valueOf != null )
            return valueOf;

        ValueConverter constructor = constructorConverter( returnType );
        if ( constructor != null )
            return constructor;

        throw new UnsupportedValueTypeException( valueType );
    }

    private static ValueConverter valueOfConverter( Class<?> valueType ) {
        if ( Character.class.equals( valueType ) )
            return new CharacterValueOfConverter();
        try {
            Method valueOf = valueType.getDeclaredMethod( "valueOf", String.class );
            return meetsConverterRequirements( valueOf, valueType )
                ? new MethodInvokingValueConverter( valueOf, valueType )
                : null;
        }
        catch ( NoSuchMethodException ignored ) {
            return null;
        }
    }

    private static ValueConverter constructorConverter( Class<?> valueType ) {
        try {
            return new ConstructorInvokingValueConverter( valueType.getConstructor( String.class ) );
        }
        catch ( NoSuchMethodException ignored ) {
            return null;
        }
    }

    private static boolean meetsConverterRequirements( Method method, Class<?> expectedReturnType ) {
        int modifiers = method.getModifiers();
        return isPublic( modifiers ) && isStatic( modifiers ) && expectedReturnType.equals( method.getReturnType() );
    }

    private Class<?> targetTypeFor( Method method ) {
        Class<?> returnType = method.getReturnType();
        return returnType.isPrimitive() ? wrapperIfPrimitive( returnType ) : returnType;
    }
}
