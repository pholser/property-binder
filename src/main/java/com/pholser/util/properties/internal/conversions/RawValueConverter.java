package com.pholser.util.properties.internal.conversions;

public class RawValueConverter extends ScalarValueConverter {
    @Override
    public Object convert(Object raw, Object... args) {
        return raw;
    }

    @Override
    public Object convert(String raw, Object... args) {
        throw new AssertionError("A raw value converter should never be asked to convert a value as a String");
    }
}
