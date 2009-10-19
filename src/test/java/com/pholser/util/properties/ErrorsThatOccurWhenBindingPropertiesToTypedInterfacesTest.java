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

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import com.pholser.util.properties.boundtypes.ArrayOfUnconvertibleTypePropertyHaver;
import com.pholser.util.properties.boundtypes.BadDefaultValuePropertyHaver;
import com.pholser.util.properties.boundtypes.BadValueSeparatorPropertyHaver;
import com.pholser.util.properties.boundtypes.DefaultValueWithBothValueAndValueOf;
import com.pholser.util.properties.boundtypes.DefaultValueWithNeitherValueNorValueOf;
import com.pholser.util.properties.boundtypes.InterfaceWithSuperinterfaces;
import com.pholser.util.properties.boundtypes.ListOfArrayPropertyHaver;
import com.pholser.util.properties.boundtypes.ListOfUnconvertibleTypePropertyHaver;
import com.pholser.util.properties.boundtypes.LowerBoundedListPropertyHaver;
import com.pholser.util.properties.boundtypes.SeparatedPropertyHaverWithBothPatternAndValueOf;
import com.pholser.util.properties.boundtypes.SeparatorOnNonAggregateTypePropertyHaver;
import com.pholser.util.properties.boundtypes.TypeWithNonPublicValueOfPropertyHaver;
import com.pholser.util.properties.boundtypes.TypeWithNonStaticValueOfPropertyHaver;
import com.pholser.util.properties.boundtypes.TypeWithValueOfWithBadReturnTypePropertyHaver;
import com.pholser.util.properties.boundtypes.UnsupportedAggregateTypePropertyHaver;
import com.pholser.util.properties.boundtypes.UpperBoundedListPropertyHaver;
import com.pholser.util.properties.boundtypes.CharacterPropertyHaverWithTooLongDefault;
import com.pholser.util.properties.boundtypes.MissingPrimitivePropertyHaver;
import com.pholser.util.properties.boundtypes.ScalarPropertyHaver;
import com.pholser.util.properties.internal.exceptions.AppliedSeparatorToNonAggregateTypeException;
import com.pholser.util.properties.internal.exceptions.BoundTypeNotAnInterfaceException;
import com.pholser.util.properties.internal.exceptions.InterfaceHasSuperinterfacesException;
import com.pholser.util.properties.internal.exceptions.MalformedDefaultValueException;
import com.pholser.util.properties.internal.exceptions.MalformedSeparatorException;
import com.pholser.util.properties.internal.exceptions.MultipleDefaultValueSpecificationException;
import com.pholser.util.properties.internal.exceptions.MultipleSeparatorSpecificationException;
import com.pholser.util.properties.internal.exceptions.NoDefaultValueSpecificationException;
import com.pholser.util.properties.internal.exceptions.UnsupportedAggregateTypeException;
import com.pholser.util.properties.internal.exceptions.UnsupportedValueTypeException;

import org.junit.Test;

public class ErrorsThatOccurWhenBindingPropertiesToTypedInterfacesTest extends BindingTestSupport {
    @Test( expected = BoundTypeNotAnInterfaceException.class )
    public void shouldRejectNonInterfaceClass() {
        PropertyBinder.forType( Object.class );
    }

    @Test( expected = NullPointerException.class )
    public void shouldRejectNullFile() throws Exception {
        PropertyBinder.forType( ScalarPropertyHaver.class ).bind( (File) null );
    }

    @Test( expected = NullPointerException.class )
    public void shouldRejectNullInputStream() throws Exception {
        PropertyBinder.forType( ScalarPropertyHaver.class ).bind( (InputStream) null );
    }

    @Test( expected = NullPointerException.class )
    public void shouldRejectNullPropertiesObject() {
        PropertyBinder.forType( ScalarPropertyHaver.class ).bind( (Properties) null );
    }

    @Test( expected = NullPointerException.class )
    public void shouldRejectNullClass() {
        PropertyBinder.forType( null );
    }

    @Test( expected = MalformedDefaultValueException.class )
    public void shouldRejectNonSingleCharacterValueForCharacterProperty() {
        PropertyBinder.forType( CharacterPropertyHaverWithTooLongDefault.class );
    }

    @Test( expected = UnsupportedValueTypeException.class )
    public void shouldRejectUpperBoundedListType() {
        PropertyBinder.forType( UpperBoundedListPropertyHaver.class );
    }

    @Test( expected = UnsupportedValueTypeException.class )
    public void shouldRejectLowerBoundedListType() {
        PropertyBinder.forType( LowerBoundedListPropertyHaver.class );
    }

    @Test( expected = UnsupportedValueTypeException.class )
    public void shouldRejectListOfArrayType() {
        PropertyBinder.forType( ListOfArrayPropertyHaver.class );
    }

    @Test( expected = UnsupportedValueTypeException.class )
    public void shouldRejectArrayOfUnconvertibleType() {
        PropertyBinder.forType( ArrayOfUnconvertibleTypePropertyHaver.class );
    }

    @Test( expected = UnsupportedAggregateTypeException.class )
    public void shouldRejectUnsupportedAggregateType() {
        PropertyBinder.forType( UnsupportedAggregateTypePropertyHaver.class );
    }

    @Test( expected = UnsupportedValueTypeException.class )
    public void shouldRejectListOfUnconvertibleType() {
        PropertyBinder.forType( ListOfUnconvertibleTypePropertyHaver.class );
    }

    @Test( expected = UnsupportedValueTypeException.class )
    public void shouldRejectTypeWithNonPublicValueOf() {
        PropertyBinder.forType( TypeWithNonPublicValueOfPropertyHaver.class );
    }

    @Test( expected = UnsupportedValueTypeException.class )
    public void shouldRejectTypeWithNonStaticValueOf() {
        PropertyBinder.forType( TypeWithNonStaticValueOfPropertyHaver.class );
    }

    @Test( expected = UnsupportedValueTypeException.class )
    public void shouldRejectTypeWithValueOfWithBadReturnType() {
        PropertyBinder.forType( TypeWithValueOfWithBadReturnTypePropertyHaver.class );
    }

    @Test( expected = NullPointerException.class )
    public void shouldRejectMissingPrimitiveProperty() throws Exception {
        PropertyBinder<MissingPrimitivePropertyHaver> binder =
            PropertyBinder.forType( MissingPrimitivePropertyHaver.class );
        MissingPrimitivePropertyHaver bound = binder.bind( propertiesFile );

        bound.missingCharacterProperty();
    }

    @Test( expected = MalformedDefaultValueException.class )
    public void shouldRejectBadDefaultValue() {
        PropertyBinder.forType( BadDefaultValuePropertyHaver.class );
    }

    @Test( expected = MalformedSeparatorException.class )
    public void shouldRejectBadValueSeparator() {
        PropertyBinder.forType( BadValueSeparatorPropertyHaver.class );
    }

    @Test( expected = AppliedSeparatorToNonAggregateTypeException.class )
    public void shouldRejectApplyingSeparatorToNonAggregateType() {
        PropertyBinder.forType( SeparatorOnNonAggregateTypePropertyHaver.class );
    }

    @Test( expected = MultipleSeparatorSpecificationException.class )
    public void shouldRejectSeparatorWithBothPatternAndValueOf() {
        PropertyBinder.forType( SeparatedPropertyHaverWithBothPatternAndValueOf.class );
    }

    @Test( expected = InterfaceHasSuperinterfacesException.class )
    public void shouldRejectTypeWithSuperinterfaces() {
        PropertyBinder.forType( InterfaceWithSuperinterfaces.class );
    }

    @Test( expected = MultipleDefaultValueSpecificationException.class )
    public void shouldRejectDefaultValueSpecWithBothValueAndValueOf() {
        PropertyBinder.forType( DefaultValueWithBothValueAndValueOf.class );
    }

    @Test( expected = NoDefaultValueSpecificationException.class )
    public void shouldRejectDefaultValueSpecWithNeitherValueNorValueOf() {
        PropertyBinder.forType( DefaultValueWithNeitherValueNorValueOf.class );
    }
}
