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
    @BoundProperty( "string.array.property" )
    String[] stringArrayProperty();

    @BoundProperty( "string.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    String[] stringArrayPropertyWithCustomSeparator();

    @BoundProperty( "string.array.property.with.default" )
    @DefaultsTo( "g,hh,iii" )
    String[] stringArrayPropertyWithDefault();

    @BoundProperty( "string.array.property.with.default.and.custom.separator" )
    @DefaultsTo( "jjj|kk|L" )
    @ValuesSeparatedBy( pattern = "\\|" )
    String[] stringArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "primitive.boolean.array.property" )
    boolean[] primitiveBooleanArrayProperty();

    @BoundProperty( "primitive.boolean.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    boolean[] primitiveBooleanArrayPropertyWithCustomSeparator();

    @BoundProperty( "primitive.boolean.array.property.with.default" )
    @DefaultsTo( "false,false,true,false,true" )
    boolean[] primitiveBooleanArrayPropertyWithDefault();

    @BoundProperty( "primitive.boolean.array.property.with.default.and.separator" )
    @DefaultsTo( "true|true|true|false" )
    @ValuesSeparatedBy( pattern = "\\|" )
    boolean[] primitiveBooleanArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "wrapped.boolean.array.property" )
    Boolean[] wrappedBooleanArrayProperty();

    @BoundProperty( "wrapped.boolean.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Boolean[] wrappedBooleanArrayPropertyWithCustomSeparator();

    @BoundProperty( "wrapped.boolean.array.property.with.default" )
    @DefaultsTo( "false,false,true,false,true" )
    Boolean[] wrappedBooleanArrayPropertyWithDefault();

    @BoundProperty( "wrapped.boolean.array.property.with.default.and.separator" )
    @DefaultsTo( "true|true|true|false" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Boolean[] wrappedBooleanArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "primitive.byte.array.property" )
    byte[] primitiveByteArrayProperty();

    @BoundProperty( "primitive.byte.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    byte[] primitiveByteArrayPropertyWithCustomSeparator();

    @BoundProperty( "primitive.byte.array.property.with.default" )
    @DefaultsTo( "24,25,26,27,28" )
    byte[] primitiveByteArrayPropertyWithDefault();

    @BoundProperty( "primitive.byte.array.property.with.default.and.separator" )
    @DefaultsTo( "29|30|31|32" )
    @ValuesSeparatedBy( pattern = "\\|" )
    byte[] primitiveByteArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "wrapped.byte.array.property" )
    Byte[] wrappedByteArrayProperty();

    @BoundProperty( "wrapped.byte.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Byte[] wrappedByteArrayPropertyWithCustomSeparator();

    @BoundProperty( "wrapped.byte.array.property.with.default" )
    @DefaultsTo( "33,34,35" )
    Byte[] wrappedByteArrayPropertyWithDefault();

    @BoundProperty( "wrapped.byte.array.property.with.default.and.separator" )
    @DefaultsTo( "36|37|38|39" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Byte[] wrappedByteArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "primitive.character.array.property" )
    char[] primitiveCharacterArrayProperty();

    @BoundProperty( "primitive.character.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    char[] primitiveCharacterArrayPropertyWithCustomSeparator();

    @BoundProperty( "primitive.character.array.property.with.default" )
    @DefaultsTo( "h,i,j,k,l" )
    char[] primitiveCharacterArrayPropertyWithDefault();

    @BoundProperty( "primitive.character.array.property.with.default.and.separator" )
    @DefaultsTo( "m|n|o|p" )
    @ValuesSeparatedBy( pattern = "\\|" )
    char[] primitiveCharacterArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "wrapped.character.array.property" )
    Character[] wrappedCharacterArrayProperty();

    @BoundProperty( "wrapped.character.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Character[] wrappedCharacterArrayPropertyWithCustomSeparator();

    @BoundProperty( "wrapped.character.array.property.with.default" )
    @DefaultsTo( "w,x,y" )
    Character[] wrappedCharacterArrayPropertyWithDefault();

    @BoundProperty( "wrapped.character.array.property.with.default.and.separator" )
    @DefaultsTo( "z|0|1|2" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Character[] wrappedCharacterArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "primitive.double.array.property" )
    double[] primitiveDoubleArrayProperty();

    @BoundProperty( "primitive.double.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    double[] primitiveDoubleArrayPropertyWithCustomSeparator();

    @BoundProperty( "primitive.double.array.property.with.default" )
    @DefaultsTo( "-1.0,-2.0,-3.0,-4.0,-5.0" )
    double[] primitiveDoubleArrayPropertyWithDefault();

    @BoundProperty( "primitive.double.array.property.with.default.and.separator" )
    @DefaultsTo( "-6.0|-7.0|-8.0|-9.0" )
    @ValuesSeparatedBy( pattern = "\\|" )
    double[] primitiveDoubleArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "wrapped.double.array.property" )
    Double[] wrappedDoubleArrayProperty();

    @BoundProperty( "wrapped.double.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Double[] wrappedDoubleArrayPropertyWithCustomSeparator();

    @BoundProperty( "wrapped.double.array.property.with.default" )
    @DefaultsTo( "-10.0,-11.0,-12.0" )
    Double[] wrappedDoubleArrayPropertyWithDefault();

    @BoundProperty( "wrapped.double.array.property.with.default.and.separator" )
    @DefaultsTo( "-13.0|-14.0|-15.0|-16.0" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Double[] wrappedDoubleArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "primitive.float.array.property" )
    float[] primitiveFloatArrayProperty();

    @BoundProperty( "primitive.float.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    float[] primitiveFloatArrayPropertyWithCustomSeparator();

    @BoundProperty( "primitive.float.array.property.with.default" )
    @DefaultsTo( "1.1,1.2,1.3,1.4,1.5" )
    float[] primitiveFloatArrayPropertyWithDefault();

    @BoundProperty( "primitive.float.array.property.with.default.and.separator" )
    @DefaultsTo( "1.6|1.7|1.8|1.9" )
    @ValuesSeparatedBy( pattern = "\\|" )
    float[] primitiveFloatArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "wrapped.float.array.property" )
    Float[] wrappedFloatArrayProperty();

    @BoundProperty( "wrapped.float.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Float[] wrappedFloatArrayPropertyWithCustomSeparator();

    @BoundProperty( "wrapped.float.array.property.with.default" )
    @DefaultsTo( "2.0,2.1,2.2" )
    Float[] wrappedFloatArrayPropertyWithDefault();

    @BoundProperty( "wrapped.float.array.property.with.default.and.separator" )
    @DefaultsTo( "2.3|2.4|2.5|2.6" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Float[] wrappedFloatArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "primitive.integer.array.property" )
    int[] primitiveIntegerArrayProperty();

    @BoundProperty( "primitive.integer.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    int[] primitiveIntegerArrayPropertyWithCustomSeparator();

    @BoundProperty( "primitive.integer.array.property.with.default" )
    @DefaultsTo( "-1,-2,-3,-4,-5" )
    int[] primitiveIntegerArrayPropertyWithDefault();

    @BoundProperty( "primitive.integer.array.property.with.default.and.separator" )
    @DefaultsTo( "-6|-7|-8|-9" )
    @ValuesSeparatedBy( pattern = "\\|" )
    int[] primitiveIntegerArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "wrapped.integer.array.property" )
    Integer[] wrappedIntegerArrayProperty();

    @BoundProperty( "wrapped.integer.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Integer[] wrappedIntegerArrayPropertyWithCustomSeparator();

    @BoundProperty( "wrapped.integer.array.property.with.default" )
    @DefaultsTo( "-10,-11,-12" )
    Integer[] wrappedIntegerArrayPropertyWithDefault();

    @BoundProperty( "wrapped.integer.array.property.with.default.and.separator" )
    @DefaultsTo( "-13|-14|-15|-16" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Integer[] wrappedIntegerArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "primitive.long.array.property" )
    long[] primitiveLongArrayProperty();

    @BoundProperty( "primitive.long.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    long[] primitiveLongArrayPropertyWithCustomSeparator();

    @BoundProperty( "primitive.long.array.property.with.default" )
    @DefaultsTo( "44,45,46,47,48" )
    long[] primitiveLongArrayPropertyWithDefault();

    @BoundProperty( "primitive.long.array.property.with.default.and.separator" )
    @DefaultsTo( "49|50|51|52" )
    @ValuesSeparatedBy( pattern = "\\|" )
    long[] primitiveLongArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "wrapped.long.array.property" )
    Long[] wrappedLongArrayProperty();

    @BoundProperty( "wrapped.long.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Long[] wrappedLongArrayPropertyWithCustomSeparator();

    @BoundProperty( "wrapped.long.array.property.with.default" )
    @DefaultsTo( "53,54,55" )
    Long[] wrappedLongArrayPropertyWithDefault();

    @BoundProperty( "wrapped.long.array.property.with.default.and.separator" )
    @DefaultsTo( "56|57|58|59" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Long[] wrappedLongArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "primitive.short.array.property" )
    short[] primitiveShortArrayProperty();

    @BoundProperty( "primitive.short.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    short[] primitiveShortArrayPropertyWithCustomSeparator();

    @BoundProperty( "primitive.short.array.property.with.default" )
    @DefaultsTo( "-20,-21,-22,-23,-24" )
    short[] primitiveShortArrayPropertyWithDefault();

    @BoundProperty( "primitive.short.array.property.with.default.and.separator" )
    @DefaultsTo( "-25|-26|-27|-28" )
    @ValuesSeparatedBy( pattern = "\\|" )
    short[] primitiveShortArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "wrapped.short.array.property" )
    Short[] wrappedShortArrayProperty();

    @BoundProperty( "wrapped.short.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Short[] wrappedShortArrayPropertyWithCustomSeparator();

    @BoundProperty( "wrapped.short.array.property.with.default" )
    @DefaultsTo( "-29,-30,-31" )
    Short[] wrappedShortArrayPropertyWithDefault();

    @BoundProperty( "wrapped.short.array.property.with.default.and.separator" )
    @DefaultsTo( "-32|-33|-34|-35" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Short[] wrappedShortArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "big.integer.array.property" )
    BigInteger[] bigIntegerArrayProperty();

    @BoundProperty( "big.integer.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    BigInteger[] bigIntegerArrayPropertyWithCustomSeparator();

    @BoundProperty( "big.integer.array.property.with.default" )
    @DefaultsTo( "128,129,130" )
    BigInteger[] bigIntegerArrayPropertyWithDefault();

    @BoundProperty( "big.integer.array.property.with.default.and.separator" )
    @DefaultsTo( "131|132|133" )
    @ValuesSeparatedBy( pattern = "\\|" )
    BigInteger[] bigIntegerArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "big.decimal.array.property" )
    BigDecimal[] bigDecimalArrayProperty();

    @BoundProperty( "big.decimal.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    BigDecimal[] bigDecimalArrayPropertyWithCustomSeparator();

    @BoundProperty( "big.decimal.array.property.with.default" )
    @DefaultsTo( "345.67,890.12" )
    BigDecimal[] bigDecimalArrayPropertyWithDefault();

    @BoundProperty( "big.decimal.array.property.with.default.and.separator" )
    @DefaultsTo( "3456.78|9012.34" )
    @ValuesSeparatedBy( pattern = "\\|" )
    BigDecimal[] bigDecimalArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "enum.array.property" )
    Trinary[] enumArrayProperty();

    @BoundProperty( "enum.array.property.with.custom.separator" )
    @ValuesSeparatedBy( pattern = "\\s*,\\s*" )
    Trinary[] enumArrayPropertyWithCustomSeparator();

    @BoundProperty( "enum.array.property.with.default" )
    @DefaultsTo( "YES,NO,NO,MAYBE,YES" )
    Trinary[] enumArrayPropertyWithDefault();

    @BoundProperty( "enum.array.property.with.default.and.separator" )
    @DefaultsTo( "NO|MAYBE|YES|MAYBE" )
    @ValuesSeparatedBy( pattern = "\\|" )
    Trinary[] enumArrayPropertyWithDefaultAndSeparator();

    @BoundProperty( "missing.primitive.array.property" )
    int[] missingPrimitiveArrayProperty();

    @BoundProperty( "missing.object.array.property" )
    String[] missingObjectArrayProperty();
}
