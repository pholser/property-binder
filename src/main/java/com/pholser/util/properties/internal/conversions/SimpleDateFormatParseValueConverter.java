package com.pholser.util.properties.internal.conversions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import static java.util.Arrays.*;

import com.pholser.util.properties.internal.exceptions.ValueConversionException;

import com.pholser.util.properties.ParsedAs;

class SimpleDateFormatParseValueConverter implements ValueConverter {
    private final ParsedAs parsePatterns;

    public SimpleDateFormatParseValueConverter(ParsedAs parsePatterns) {
        this.parsePatterns = parsePatterns;
    }

    @Override
    public Object convert(String raw, Object... args) {
        for (String each : parsePatterns.value()) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(each);
                formatter.setLenient(false);
                return formatter.parse(String.format(raw, args));
            } catch (ParseException ex) {
                // try the next pattern
            }
        }

        throw new ValueConversionException(
            "Could not parse value [" + raw + "] using any of the patterns: " + asList(parsePatterns.value()));
    }

    @Override
    public Object nilValue() {
        return null;
    }

    @Override
    public void resolve(Properties properties) {
        // nothing to do here
    }
}
