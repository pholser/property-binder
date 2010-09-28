package com.pholser.util.properties.internal.conversions;

import org.junit.Test;

public class ErrorsThatOccurWhenTryingToConvertARawValueAsAStringTest {
    @Test(expected = AssertionError.class)
    public void rejectingAttemptsToConvertAString() {
        new RawValueConverter().convert("");
    }
}
