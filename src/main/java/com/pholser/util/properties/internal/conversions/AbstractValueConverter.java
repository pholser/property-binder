package com.pholser.util.properties.internal.conversions;

public abstract class AbstractValueConverter implements ValueConverter {
    @Override
    public Object convert(Object raw, Object... args) {
        return raw instanceof String ? convert((String) raw, args) : raw;
    }
}
