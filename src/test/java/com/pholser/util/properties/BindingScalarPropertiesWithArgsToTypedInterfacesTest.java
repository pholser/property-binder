package com.pholser.util.properties;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pholser.util.properties.internal.exceptions.ValueConversionException;

import com.pholser.util.properties.boundtypes.ScalarPropertyWithArgsHaver;
import org.junit.Test;

import static org.junit.Assert.*;

public class BindingScalarPropertiesWithArgsToTypedInterfacesTest
    extends TypedBindingTestSupport<ScalarPropertyWithArgsHaver> {

    @Test
    public void shouldBeAbleToPassArgumentsToPropertyMethodAndHaveFormattingOccurOnResult() {
        assertEquals("foo to the bar", bound.stringPropertyWithArguments("foo", "bar"));
    }

    @Test
    public void shouldBeAbleToPassArgumentsOfVariousTypesToPropertyMethodAndHaveFormattingOccurOnResult()
        throws Exception {

        Date time = new SimpleDateFormat("MM/dd/yyyy").parse("12/22/2003");
        assertEquals("10 seconds to 12:00:00 AM", bound.stringPropertyWithTypedArguments(10, time));
    }

    @Test(expected = ValueConversionException.class)
    public void shouldRejectIllTypedArgumentsToPropertyMethodsWithArgs() throws Exception {
        Date time = new SimpleDateFormat("MM/dd/yyyy").parse("12/22/2003");
        bound.stringPropertyWithIllTypedArguments(10, time);
    }

    @Override
    protected Class<ScalarPropertyWithArgsHaver> boundType() {
        return ScalarPropertyWithArgsHaver.class;
    }
}
