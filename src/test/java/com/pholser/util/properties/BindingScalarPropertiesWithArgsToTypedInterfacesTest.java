package com.pholser.util.properties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Arrays.*;

import com.pholser.util.properties.boundtypes.ScalarPropertyWithArgsHaver;
import com.pholser.util.properties.internal.exceptions.ValueConversionException;
import org.junit.Test;

import static com.pholser.util.properties.ArrayUtils.*;
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

        assertEquals("10 seconds to 12:00:00 AM", bound.stringPropertyWithTypedArguments(10, mmddyyyy("12/22/2003")));
    }

    @Test(expected = ValueConversionException.class)
    public void shouldRejectIllTypedArgumentsToPropertyMethodsWithArgs() throws Exception {
        bound.stringPropertyWithIllTypedArguments(10, mmddyyyy("12/22/2003"));
    }

    @Test
    public void shouldBeAbleToConvertPropertyMethodsWithArgsToTypeOtherThanString() throws Exception {
        assertEquals(mmddyyyy("02/24/2010"), bound.datePropertyByMonthDayYear(2, 24, 2010));
    }

    @Test
    public void shouldBeAbleToApplyArgumentsOfPropertyMethodsToEachOfAnArrayType() {
        assertEquals(toList(new String[] {"foobar", "barfoo"}),
            toList(bound.stringArrayPropertyWithArguments("foo", "bar")));
    }

    @Test
    public void shouldBeAbleToApplyArgumentsOfPropertyMethodsToEachOfListTypeWithCustomSeparator() throws Exception {
        assertEquals(asList(mmddyyyy("03/23/2010"), mmddyyyy("03/23/2010")),
            bound.dateListPropertyWithArgumentsAndSeparator(3, 23, 2010));
    }

    @Override
    protected Class<ScalarPropertyWithArgsHaver> boundType() {
        return ScalarPropertyWithArgsHaver.class;
    }

    private Date mmddyyyy(String raw) throws ParseException {
        return new SimpleDateFormat("MM/dd/yyyy").parse(raw);
    }
}
