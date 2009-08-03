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

package com.pholser.util.properties.boundtypes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ValuesSeparatedBy;

public interface ListPropertyHaver {
    @BoundProperty( value = "string.list.property" )
    List<String> stringListProperty();

    @BoundProperty( value = "string.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<String> stringListPropertyWithCustomSeparator();

    @BoundProperty( value = "string.list.property.with.default" )
    @DefaultsTo( "g,hh,iii" )
    List<String> stringListPropertyWithDefault();

    @BoundProperty( value = "string.list.property.with.default.and.custom.separator" )
    @DefaultsTo( "jjj|kk|L" )
    @ValuesSeparatedBy( "\\|" )
    List<String> stringListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "boolean.list.property" )
    List<Boolean> booleanListProperty();

    @BoundProperty( value = "boolean.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<Boolean> booleanListPropertyWithCustomSeparator();

    @BoundProperty( value = "boolean.list.property.with.default" )
    @DefaultsTo( "false,false,true,false,true" )
    List<Boolean> booleanListPropertyWithDefault();

    @BoundProperty( value = "boolean.list.property.with.default.and.separator" )
    @DefaultsTo( "true|true|true|false" )
    @ValuesSeparatedBy( "\\|" )
    List<Boolean> booleanListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "byte.list.property" )
    List<Byte> byteListProperty();

    @BoundProperty( value = "byte.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<Byte> byteListPropertyWithCustomSeparator();

    @BoundProperty( value = "byte.list.property.with.default" )
    @DefaultsTo( "33,34,35" )
    List<Byte> byteListPropertyWithDefault();

    @BoundProperty( value = "byte.list.property.with.default.and.separator" )
    @DefaultsTo( "36|37|38|39" )
    @ValuesSeparatedBy( "\\|" )
    List<Byte> byteListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "character.list.property" )
    List<Character> characterListProperty();

    @BoundProperty( value = "character.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<Character> characterListPropertyWithCustomSeparator();

    @BoundProperty( value = "character.list.property.with.default" )
    @DefaultsTo( "w,x,y" )
    List<Character> characterListPropertyWithDefault();

    @BoundProperty( value = "character.list.property.with.default.and.separator" )
    @DefaultsTo( "z|0|1|2" )
    @ValuesSeparatedBy( "\\|" )
    List<Character> characterListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "double.list.property" )
    List<Double> doubleListProperty();

    @BoundProperty( value = "double.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<Double> doubleListPropertyWithCustomSeparator();

    @BoundProperty( value = "double.list.property.with.default" )
    @DefaultsTo( "-10.0,-11.0,-12.0" )
    List<Double> doubleListPropertyWithDefault();

    @BoundProperty( value = "double.list.property.with.default.and.separator" )
    @DefaultsTo( "-13.0|-14.0|-15.0|-16.0" )
    @ValuesSeparatedBy( "\\|" )
    List<Double> doubleListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "float.list.property" )
    List<Float> floatListProperty();

    @BoundProperty( value = "float.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<Float> floatListPropertyWithCustomSeparator();

    @BoundProperty( value = "float.list.property.with.default" )
    @DefaultsTo( "2.0,2.1,2.2" )
    List<Float> floatListPropertyWithDefault();

    @BoundProperty( value = "float.list.property.with.default.and.separator" )
    @DefaultsTo( "2.3|2.4|2.5|2.6" )
    @ValuesSeparatedBy( "\\|" )
    List<Float> floatListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "integer.list.property" )
    List<Integer> integerListProperty();

    @BoundProperty( value = "integer.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<Integer> integerListPropertyWithCustomSeparator();

    @BoundProperty( value = "integer.list.property.with.default" )
    @DefaultsTo( "-10,-11,-12" )
    List<Integer> integerListPropertyWithDefault();

    @BoundProperty( value = "integer.list.property.with.default.and.separator" )
    @DefaultsTo( "-13|-14|-15|-16" )
    @ValuesSeparatedBy( "\\|" )
    List<Integer> integerListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "long.list.property" )
    List<Long> longListProperty();

    @BoundProperty( value = "long.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<Long> longListPropertyWithCustomSeparator();

    @BoundProperty( value = "long.list.property.with.default" )
    @DefaultsTo( "53,54,55" )
    List<Long> longListPropertyWithDefault();

    @BoundProperty( value = "long.list.property.with.default.and.separator" )
    @DefaultsTo( "56|57|58|59" )
    @ValuesSeparatedBy( "\\|" )
    List<Long> longListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "short.list.property" )
    List<Short> shortListProperty();

    @BoundProperty( value = "short.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<Short> shortListPropertyWithCustomSeparator();

    @BoundProperty( value = "short.list.property.with.default" )
    @DefaultsTo( "-29,-30,-31" )
    List<Short> shortListPropertyWithDefault();

    @BoundProperty( value = "short.list.property.with.default.and.separator" )
    @DefaultsTo( "-32|-33|-34|-35" )
    @ValuesSeparatedBy( "\\|" )
    List<Short> shortListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "big.integer.list.property" )
    List<BigInteger> bigIntegerListProperty();

    @BoundProperty( value = "big.integer.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<BigInteger> bigIntegerListPropertyWithCustomSeparator();

    @BoundProperty( value = "big.integer.list.property.with.default" )
    @DefaultsTo( "128,129,130" )
    List<BigInteger> bigIntegerListPropertyWithDefault();

    @BoundProperty( value = "big.integer.list.property.with.default.and.separator" )
    @DefaultsTo( "131|132|133" )
    @ValuesSeparatedBy( "\\|" )
    List<BigInteger> bigIntegerListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "big.decimal.list.property" )
    List<BigDecimal> bigDecimalListProperty();

    @BoundProperty( value = "big.decimal.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<BigDecimal> bigDecimalListPropertyWithCustomSeparator();

    @BoundProperty( value = "big.decimal.list.property.with.default" )
    @DefaultsTo( "345.67,890.12" )
    List<BigDecimal> bigDecimalListPropertyWithDefault();

    @BoundProperty( value = "big.decimal.list.property.with.default.and.separator" )
    @DefaultsTo( "3456.78|9012.34" )
    @ValuesSeparatedBy( "\\|" )
    List<BigDecimal> bigDecimalListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "enum.list.property" )
    List<Trinary> enumListProperty();

    @BoundProperty( value = "enum.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<Trinary> enumListPropertyWithCustomSeparator();

    @BoundProperty( value = "enum.list.property.with.default" )
    @DefaultsTo( "YES,NO,NO,MAYBE,YES" )
    List<Trinary> enumListPropertyWithDefault();

    @BoundProperty( value = "enum.list.property.with.default.and.separator" )
    @DefaultsTo( "NO|MAYBE|YES|MAYBE" )
    @ValuesSeparatedBy( "\\|" )
    List<Trinary> enumListPropertyWithDefaultAndSeparator();

    @SuppressWarnings( "unchecked" )
    @BoundProperty( value = "raw.list.property" )
    List rawListProperty();

    @SuppressWarnings( "unchecked" )
    @BoundProperty( value = "raw.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List rawListPropertyWithCustomSeparator();

    @SuppressWarnings( "unchecked" )
    @BoundProperty( value = "raw.list.property.with.default" )
    @DefaultsTo( "YES,NO,NO,MAYBE,YES" )
    List rawListPropertyWithDefault();

    @SuppressWarnings( "unchecked" )
    @BoundProperty( value = "raw.list.property.with.default.and.separator" )
    @DefaultsTo( "NO|MAYBE|YES|MAYBE" )
    @ValuesSeparatedBy( "\\|" )
    List rawListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "huh.list.property" )
    List<?> huhListProperty();

    @BoundProperty( value = "huh.list.property.with.custom.separator" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<?> huhListPropertyWithCustomSeparator();

    @BoundProperty( value = "huh.list.property.with.default" )
    @DefaultsTo( "YES,NO,MAYBE" )
    List<?> huhListPropertyWithDefault();

    @BoundProperty( value = "huh.list.property.with.default.and.separator" )
    @DefaultsTo( "NO|MAYBE|YES" )
    @ValuesSeparatedBy( "\\|" )
    List<?> huhListPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "missing.list.property" )
    List<String> missingListProperty();
}
