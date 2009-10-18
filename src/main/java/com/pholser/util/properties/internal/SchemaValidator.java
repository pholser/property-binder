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

import static com.pholser.util.properties.internal.PICAHelpers.*;
import static com.pholser.util.properties.internal.Types.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ValuesSeparatedBy;
import com.pholser.util.properties.internal.exceptions.AppliedSeparatorToNonAggregateTypeException;
import com.pholser.util.properties.internal.exceptions.BoundTypeNotAnInterfaceException;
import com.pholser.util.properties.internal.exceptions.InterfaceHasSuperinterfacesException;
import com.pholser.util.properties.internal.exceptions.MalformedDefaultValueException;
import com.pholser.util.properties.internal.exceptions.MultipleSeparatorSpecificationException;
import com.pholser.util.properties.internal.exceptions.UnsupportedAggregateTypeException;
import com.pholser.util.properties.internal.exceptions.ValueConversionException;

public class SchemaValidator {
    private final ValueConverterFactory converterFactory = new ValueConverterFactory();

    private final ValueSeparatorFactory separatorFactory = new ValueSeparatorFactory();

    public <T> ValidatedSchema<T> validate( Class<T> schema ) {
        ensureInterface( schema );
        ensureNoSuperinterfaces( schema );

        Map<String, ValueConverter> converters = new HashMap<String, ValueConverter>();
        Map<String, Object> defaultValues = new HashMap<String, Object>();
        Map<String, ValueSeparator> separators = new HashMap<String, ValueSeparator>();

        for ( Method each : schema.getDeclaredMethods() ) {
            String propertyName = propertyNameFor( each );
            ensureAggregateTypeIsSupported( each );
            collectSeparatorIfAggregateType( separators, each, propertyName );
            collectConverter( converters, separators, each, propertyName );
            collectDefaultValue( defaultValues, converters.get( propertyName ), each, propertyName );
        }

        return new ValidatedSchema<T>( schema, defaultValues, converters );
    }

    private void ensureInterface( Class<?> schema ) {
        if ( !schema.isInterface() )
            throw new BoundTypeNotAnInterfaceException( schema );
    }

    private void ensureNoSuperinterfaces( Class<?> schema ) {
        if ( schema.getInterfaces().length != 0 )
            throw new InterfaceHasSuperinterfacesException( schema );
    }

    private void ensureAggregateTypeIsSupported( Method method ) {
        if ( isAggregateType( method.getReturnType() ) && !isSupportedAggregateType( method.getReturnType() ) )
            throw new UnsupportedAggregateTypeException( method );
    }

    private void collectSeparatorIfAggregateType( Map<String, ValueSeparator> separators, Method method,
        String propertyName ) {

        boolean isAggregate = isAggregateType( method.getReturnType() );
        ValuesSeparatedBy separator = method.getAnnotation( ValuesSeparatedBy.class );
        if ( separator != null ) {
            if ( !isAggregate )
                throw new AppliedSeparatorToNonAggregateTypeException( method );

            if ( !( separator.pattern().equals( annotationDefault( ValuesSeparatedBy.class, "pattern" ) )
                || separator.valueOf().equals( annotationDefault( ValuesSeparatedBy.class, "valueOf" ) ) ) ) {
                throw new MultipleSeparatorSpecificationException( separator, method );
            }
        }

        if ( isAggregate )
            separators.put( propertyName, separatorFactory.createSeparator( separator, method ) );
    }

    private void collectConverter( Map<String, ValueConverter> converters, Map<String, ValueSeparator> separators,
        Method method, String propertyName ) {

        converters.put(
            propertyName,
            converterFactory.createConverter( method, separators.get( propertyName ) ) );
    }

    private void collectDefaultValue( Map<String, Object> defaults, ValueConverter converter, Method method,
        String propertyName ) {

        Object defaultValue = defaultValue( method, converter );
        if ( defaultValue != null )
            defaults.put( propertyName, defaultValue );
    }

    private Object defaultValue( Method method, ValueConverter converter ) {
        DefaultsTo defaultValue = method.getAnnotation( DefaultsTo.class );
        if ( defaultValue == null )
            return null;

        try {
            return converter.convert( defaultValue.value() );
        }
        catch ( ValueConversionException ex ) {
            throw new MalformedDefaultValueException( defaultValue, method, ex );
        }
    }
}
