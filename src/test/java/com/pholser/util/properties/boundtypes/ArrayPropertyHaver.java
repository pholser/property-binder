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

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ValuesSeparatedBy;

public interface ArrayPropertyHaver {
    @BoundProperty( value = "string.array.property" )
    String[] stringArrayProperty();

    @BoundProperty( value = "string.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    String[] stringArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "string.array.property.with.default" )
    @DefaultsTo( "g,hh,iii" )
    String[] stringArrayPropertyWithDefault();

    @BoundProperty( value = "string.array.property.with.default.and.custom.separator" )
    @DefaultsTo( "jjj|kk|L" )
    @ValuesSeparatedBy( pattern = "\\|" )
    String[] stringArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "primitive.boolean.array.property" )
    boolean[] primitiveBooleanArrayProperty();

    @BoundProperty( value = "primitive.boolean.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    boolean[] primitiveBooleanArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "primitive.boolean.array.property.with.default" )
    @DefaultsTo( "false,false,true,false,true" )
    boolean[] primitiveBooleanArrayPropertyWithDefault();

    @BoundProperty( value = "primitive.boolean.array.property.with.default.and.separator" )
    @DefaultsTo( "true|true|true|false" )
    @ValuesSeparatedBy( pattern = "\\|" )
    boolean[] primitiveBooleanArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "wrapped.boolean.array.property" )
    Boolean[] wrappedBooleanArrayProperty();

    @BoundProperty( value = "wrapped.boolean.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Boolean[] wrappedBooleanArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "wrapped.boolean.array.property.with.default" )
    @DefaultsTo( "false,false,true,false,true" )
    Boolean[] wrappedBooleanArrayPropertyWithDefault();

    @BoundProperty( value = "wrapped.boolean.array.property.with.default.and.separator" )
    @DefaultsTo( "true|true|true|false" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Boolean[] wrappedBooleanArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "primitive.byte.array.property" )
    byte[] primitiveByteArrayProperty();

    @BoundProperty( value = "primitive.byte.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    byte[] primitiveByteArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "primitive.byte.array.property.with.default" )
    @DefaultsTo( "24,25,26,27,28" )
    byte[] primitiveByteArrayPropertyWithDefault();

    @BoundProperty( value = "primitive.byte.array.property.with.default.and.separator" )
    @DefaultsTo( "29|30|31|32" )
    @ValuesSeparatedBy( pattern = "\\|" )
    byte[] primitiveByteArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "wrapped.byte.array.property" )
    Byte[] wrappedByteArrayProperty();

    @BoundProperty( value = "wrapped.byte.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Byte[] wrappedByteArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "wrapped.byte.array.property.with.default" )
    @DefaultsTo( "33,34,35" )
    Byte[] wrappedByteArrayPropertyWithDefault();

    @BoundProperty( value = "wrapped.byte.array.property.with.default.and.separator" )
    @DefaultsTo( "36|37|38|39" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Byte[] wrappedByteArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "primitive.character.array.property" )
    char[] primitiveCharacterArrayProperty();

    @BoundProperty( value = "primitive.character.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    char[] primitiveCharacterArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "primitive.character.array.property.with.default" )
    @DefaultsTo( "h,i,j,k,l" )
    char[] primitiveCharacterArrayPropertyWithDefault();

    @BoundProperty( value = "primitive.character.array.property.with.default.and.separator" )
    @DefaultsTo( "m|n|o|p" )
    @ValuesSeparatedBy( pattern = "\\|" )
    char[] primitiveCharacterArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "wrapped.character.array.property" )
    Character[] wrappedCharacterArrayProperty();

    @BoundProperty( value = "wrapped.character.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Character[] wrappedCharacterArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "wrapped.character.array.property.with.default" )
    @DefaultsTo( "w,x,y" )
    Character[] wrappedCharacterArrayPropertyWithDefault();

    @BoundProperty( value = "wrapped.character.array.property.with.default.and.separator" )
    @DefaultsTo( "z|0|1|2" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Character[] wrappedCharacterArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "primitive.double.array.property" )
    double[] primitiveDoubleArrayProperty();

    @BoundProperty( value = "primitive.double.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    double[] primitiveDoubleArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "primitive.double.array.property.with.default" )
    @DefaultsTo( "-1.0,-2.0,-3.0,-4.0,-5.0" )
    double[] primitiveDoubleArrayPropertyWithDefault();

    @BoundProperty( value = "primitive.double.array.property.with.default.and.separator" )
    @DefaultsTo( "-6.0|-7.0|-8.0|-9.0" )
    @ValuesSeparatedBy( pattern = "\\|" )
    double[] primitiveDoubleArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "wrapped.double.array.property" )
    Double[] wrappedDoubleArrayProperty();

    @BoundProperty( value = "wrapped.double.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Double[] wrappedDoubleArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "wrapped.double.array.property.with.default" )
    @DefaultsTo( "-10.0,-11.0,-12.0" )
    Double[] wrappedDoubleArrayPropertyWithDefault();

    @BoundProperty( value = "wrapped.double.array.property.with.default.and.separator" )
    @DefaultsTo( "-13.0|-14.0|-15.0|-16.0" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Double[] wrappedDoubleArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "primitive.float.array.property" )
    float[] primitiveFloatArrayProperty();

    @BoundProperty( value = "primitive.float.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    float[] primitiveFloatArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "primitive.float.array.property.with.default" )
    @DefaultsTo( "1.1,1.2,1.3,1.4,1.5" )
    float[] primitiveFloatArrayPropertyWithDefault();

    @BoundProperty( value = "primitive.float.array.property.with.default.and.separator" )
    @DefaultsTo( "1.6|1.7|1.8|1.9" )
    @ValuesSeparatedBy( pattern = "\\|" )
    float[] primitiveFloatArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "wrapped.float.array.property" )
    Float[] wrappedFloatArrayProperty();

    @BoundProperty( value = "wrapped.float.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Float[] wrappedFloatArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "wrapped.float.array.property.with.default" )
    @DefaultsTo( "2.0,2.1,2.2" )
    Float[] wrappedFloatArrayPropertyWithDefault();

    @BoundProperty( value = "wrapped.float.array.property.with.default.and.separator" )
    @DefaultsTo( "2.3|2.4|2.5|2.6" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Float[] wrappedFloatArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "primitive.integer.array.property" )
    int[] primitiveIntegerArrayProperty();

    @BoundProperty( value = "primitive.integer.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    int[] primitiveIntegerArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "primitive.integer.array.property.with.default" )
    @DefaultsTo( "-1,-2,-3,-4,-5" )
    int[] primitiveIntegerArrayPropertyWithDefault();

    @BoundProperty( value = "primitive.integer.array.property.with.default.and.separator" )
    @DefaultsTo( "-6|-7|-8|-9" )
    @ValuesSeparatedBy( pattern = "\\|" )
    int[] primitiveIntegerArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "wrapped.integer.array.property" )
    Integer[] wrappedIntegerArrayProperty();

    @BoundProperty( value = "wrapped.integer.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Integer[] wrappedIntegerArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "wrapped.integer.array.property.with.default" )
    @DefaultsTo( "-10,-11,-12" )
    Integer[] wrappedIntegerArrayPropertyWithDefault();

    @BoundProperty( value = "wrapped.integer.array.property.with.default.and.separator" )
    @DefaultsTo( "-13|-14|-15|-16" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Integer[] wrappedIntegerArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "primitive.long.array.property" )
    long[] primitiveLongArrayProperty();

    @BoundProperty( value = "primitive.long.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    long[] primitiveLongArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "primitive.long.array.property.with.default" )
    @DefaultsTo( "44,45,46,47,48" )
    long[] primitiveLongArrayPropertyWithDefault();

    @BoundProperty( value = "primitive.long.array.property.with.default.and.separator" )
    @DefaultsTo( "49|50|51|52" )
    @ValuesSeparatedBy( pattern = "\\|" )
    long[] primitiveLongArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "wrapped.long.array.property" )
    Long[] wrappedLongArrayProperty();

    @BoundProperty( value = "wrapped.long.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Long[] wrappedLongArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "wrapped.long.array.property.with.default" )
    @DefaultsTo( "53,54,55" )
    Long[] wrappedLongArrayPropertyWithDefault();

    @BoundProperty( value = "wrapped.long.array.property.with.default.and.separator" )
    @DefaultsTo( "56|57|58|59" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Long[] wrappedLongArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "primitive.short.array.property" )
    short[] primitiveShortArrayProperty();

    @BoundProperty( value = "primitive.short.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    short[] primitiveShortArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "primitive.short.array.property.with.default" )
    @DefaultsTo( "-20,-21,-22,-23,-24" )
    short[] primitiveShortArrayPropertyWithDefault();

    @BoundProperty( value = "primitive.short.array.property.with.default.and.separator" )
    @DefaultsTo( "-25|-26|-27|-28" )
    @ValuesSeparatedBy( pattern = "\\|" )
    short[] primitiveShortArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "wrapped.short.array.property" )
    Short[] wrappedShortArrayProperty();

    @BoundProperty( value = "wrapped.short.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Short[] wrappedShortArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "wrapped.short.array.property.with.default" )
    @DefaultsTo( "-29,-30,-31" )
    Short[] wrappedShortArrayPropertyWithDefault();

    @BoundProperty( value = "wrapped.short.array.property.with.default.and.separator" )
    @DefaultsTo( "-32|-33|-34|-35" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Short[] wrappedShortArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "big.integer.array.property" )
    BigInteger[] bigIntegerArrayProperty();

    @BoundProperty( value = "big.integer.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    BigInteger[] bigIntegerArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "big.integer.array.property.with.default" )
    @DefaultsTo( "128,129,130" )
    BigInteger[] bigIntegerArrayPropertyWithDefault();

    @BoundProperty( value = "big.integer.array.property.with.default.and.separator" )
    @DefaultsTo( "131|132|133" )
    @ValuesSeparatedBy( pattern = "\\|" )
    BigInteger[] bigIntegerArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "big.decimal.array.property" )
    BigDecimal[] bigDecimalArrayProperty();

    @BoundProperty( value = "big.decimal.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    BigDecimal[] bigDecimalArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "big.decimal.array.property.with.default" )
    @DefaultsTo( "345.67,890.12" )
    BigDecimal[] bigDecimalArrayPropertyWithDefault();

    @BoundProperty( value = "big.decimal.array.property.with.default.and.separator" )
    @DefaultsTo( "3456.78|9012.34" )
    @ValuesSeparatedBy( pattern = "\\|" )
    BigDecimal[] bigDecimalArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "enum.array.property" )
    Trinary[] enumArrayProperty();

    @BoundProperty( value = "enum.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Trinary[] enumArrayPropertyWithCustomSeparator();

    @BoundProperty( value = "enum.array.property.with.default" )
    @DefaultsTo( "YES,NO,NO,MAYBE,YES" )
    Trinary[] enumArrayPropertyWithDefault();

    @BoundProperty( value = "enum.array.property.with.default.and.separator" )
    @DefaultsTo( "NO|MAYBE|YES|MAYBE" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Trinary[] enumArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( value = "missing.primitive.array.property" )
    int[] missingPrimitiveArrayProperty();

    @BoundProperty( value = "missing.object.array.property" )
    String[] missingObjectArrayProperty();
}
