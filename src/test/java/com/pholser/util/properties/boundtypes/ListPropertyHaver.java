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

package com.pholser.util.properties.boundtypes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ValuesSeparatedBy;

public interface ListPropertyHaver {
    @BoundProperty("string.list.property")
    List<String> stringListProperty();

    @BoundProperty("string.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<String> stringListPropertyWithCustomSeparator();

    @BoundProperty("string.list.property.with.default")
    @DefaultsTo("g,hh,iii")
    @ValuesSeparatedBy
    List<String> stringListPropertyWithDefault();

    @BoundProperty("string.list.property.with.default.and.custom.separator")
    @DefaultsTo("jjj|kk|L")
    @ValuesSeparatedBy(pattern = "\\|")
    List<String> stringListPropertyWithDefaultAndSeparator();

    @BoundProperty("boolean.list.property")
    List<Boolean> booleanListProperty();

    @BoundProperty("boolean.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<Boolean> booleanListPropertyWithCustomSeparator();

    @BoundProperty("boolean.list.property.with.default")
    @DefaultsTo("false,false,true,false,true")
    List<Boolean> booleanListPropertyWithDefault();

    @BoundProperty("boolean.list.property.with.default.and.separator")
    @DefaultsTo("true|true|true|false")
    @ValuesSeparatedBy(pattern = "\\|")
    List<Boolean> booleanListPropertyWithDefaultAndSeparator();

    @BoundProperty("byte.list.property")
    List<Byte> byteListProperty();

    @BoundProperty("byte.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<Byte> byteListPropertyWithCustomSeparator();

    @BoundProperty("byte.list.property.with.default")
    @DefaultsTo("33,34,35")
    List<Byte> byteListPropertyWithDefault();

    @BoundProperty("byte.list.property.with.default.and.separator")
    @DefaultsTo("36|37|38|39")
    @ValuesSeparatedBy(pattern = "\\|")
    List<Byte> byteListPropertyWithDefaultAndSeparator();

    @BoundProperty("character.list.property")
    List<Character> characterListProperty();

    @BoundProperty("character.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<Character> characterListPropertyWithCustomSeparator();

    @BoundProperty("character.list.property.with.default")
    @DefaultsTo("w,x,y")
    List<Character> characterListPropertyWithDefault();

    @BoundProperty("character.list.property.with.default.and.separator")
    @DefaultsTo("z|0|1|2")
    @ValuesSeparatedBy(pattern = "\\|")
    List<Character> characterListPropertyWithDefaultAndSeparator();

    @BoundProperty("double.list.property")
    List<Double> doubleListProperty();

    @BoundProperty("double.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<Double> doubleListPropertyWithCustomSeparator();

    @BoundProperty("double.list.property.with.default")
    @DefaultsTo("-10.0,-11.0,-12.0")
    List<Double> doubleListPropertyWithDefault();

    @BoundProperty("double.list.property.with.default.and.separator")
    @DefaultsTo("-13.0|-14.0|-15.0|-16.0")
    @ValuesSeparatedBy(pattern = "\\|")
    List<Double> doubleListPropertyWithDefaultAndSeparator();

    @BoundProperty("float.list.property")
    List<Float> floatListProperty();

    @BoundProperty("float.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<Float> floatListPropertyWithCustomSeparator();

    @BoundProperty("float.list.property.with.default")
    @DefaultsTo("2.0,2.1,2.2")
    List<Float> floatListPropertyWithDefault();

    @BoundProperty("float.list.property.with.default.and.separator")
    @DefaultsTo("2.3|2.4|2.5|2.6")
    @ValuesSeparatedBy(pattern = "\\|")
    List<Float> floatListPropertyWithDefaultAndSeparator();

    @BoundProperty("integer.list.property")
    List<Integer> integerListProperty();

    @BoundProperty("integer.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<Integer> integerListPropertyWithCustomSeparator();

    @BoundProperty("integer.list.property.with.default")
    @DefaultsTo("-10,-11,-12")
    List<Integer> integerListPropertyWithDefault();

    @BoundProperty("integer.list.property.with.default.and.separator")
    @DefaultsTo("-13|-14|-15|-16")
    @ValuesSeparatedBy(pattern = "\\|")
    List<Integer> integerListPropertyWithDefaultAndSeparator();

    @BoundProperty("long.list.property")
    List<Long> longListProperty();

    @BoundProperty("long.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<Long> longListPropertyWithCustomSeparator();

    @BoundProperty("long.list.property.with.default")
    @DefaultsTo("53,54,55")
    List<Long> longListPropertyWithDefault();

    @BoundProperty("long.list.property.with.default.and.separator")
    @DefaultsTo("56|57|58|59")
    @ValuesSeparatedBy(pattern = "\\|")
    List<Long> longListPropertyWithDefaultAndSeparator();

    @BoundProperty("short.list.property")
    List<Short> shortListProperty();

    @BoundProperty("short.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<Short> shortListPropertyWithCustomSeparator();

    @BoundProperty("short.list.property.with.default")
    @DefaultsTo("-29,-30,-31")
    List<Short> shortListPropertyWithDefault();

    @BoundProperty("short.list.property.with.default.and.separator")
    @DefaultsTo("-32|-33|-34|-35")
    @ValuesSeparatedBy(pattern = "\\|")
    List<Short> shortListPropertyWithDefaultAndSeparator();

    @BoundProperty("big.integer.list.property")
    List<BigInteger> bigIntegerListProperty();

    @BoundProperty("big.integer.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<BigInteger> bigIntegerListPropertyWithCustomSeparator();

    @BoundProperty("big.integer.list.property.with.default")
    @DefaultsTo("128,129,130")
    List<BigInteger> bigIntegerListPropertyWithDefault();

    @BoundProperty("big.integer.list.property.with.default.and.separator")
    @DefaultsTo("131|132|133")
    @ValuesSeparatedBy(pattern = "\\|")
    List<BigInteger> bigIntegerListPropertyWithDefaultAndSeparator();

    @BoundProperty("big.decimal.list.property")
    List<BigDecimal> bigDecimalListProperty();

    @BoundProperty("big.decimal.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<BigDecimal> bigDecimalListPropertyWithCustomSeparator();

    @BoundProperty("big.decimal.list.property.with.default")
    @DefaultsTo("345.67,890.12")
    List<BigDecimal> bigDecimalListPropertyWithDefault();

    @BoundProperty("big.decimal.list.property.with.default.and.separator")
    @DefaultsTo("3456.78|9012.34")
    @ValuesSeparatedBy(pattern = "\\|")
    List<BigDecimal> bigDecimalListPropertyWithDefaultAndSeparator();

    @BoundProperty("enum.list.property")
    List<Ternary> enumListProperty();

    @BoundProperty("enum.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<Ternary> enumListPropertyWithCustomSeparator();

    @BoundProperty("enum.list.property.with.default")
    @DefaultsTo("YES,NO,NO,MAYBE,YES")
    List<Ternary> enumListPropertyWithDefault();

    @BoundProperty("enum.list.property.with.default.and.separator")
    @DefaultsTo("NO|MAYBE|YES|MAYBE")
    @ValuesSeparatedBy(pattern = "\\|")
    List<Ternary> enumListPropertyWithDefaultAndSeparator();

    @SuppressWarnings("unchecked")
    @BoundProperty("raw.list.property")
    List rawListProperty();

    @SuppressWarnings("unchecked")
    @BoundProperty("raw.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List rawListPropertyWithCustomSeparator();

    @SuppressWarnings("unchecked")
    @BoundProperty("raw.list.property.with.default")
    @DefaultsTo("YES,NO,NO,MAYBE,YES")
    List rawListPropertyWithDefault();

    @SuppressWarnings("unchecked")
    @BoundProperty("raw.list.property.with.default.and.separator")
    @DefaultsTo("NO|MAYBE|YES|MAYBE")
    @ValuesSeparatedBy(pattern = "\\|")
    List rawListPropertyWithDefaultAndSeparator();

    @BoundProperty("huh.list.property")
    List<?> huhListProperty();

    @BoundProperty("huh.list.property.with.custom.separator")
    @ValuesSeparatedBy(pattern = "\\s*,\\s*")
    List<?> huhListPropertyWithCustomSeparator();

    @BoundProperty("huh.list.property.with.default")
    @DefaultsTo("YES,NO,MAYBE")
    List<?> huhListPropertyWithDefault();

    @BoundProperty("huh.list.property.with.default.and.separator")
    @DefaultsTo("NO|MAYBE|YES")
    @ValuesSeparatedBy(pattern = "\\|")
    List<?> huhListPropertyWithDefaultAndSeparator();

    @BoundProperty("missing.list.property")
    List<String> missingListProperty();
}
