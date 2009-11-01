package com.pholser.util.properties.examples;

import java.io.File;
import java.util.Arrays;
import static java.util.Arrays.*;

import com.pholser.util.properties.PropertyBinder;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ExampleTest {
    private PropertyBinder<ExamplePICA> binder;
    private ExamplePICA bound;

    @Before
    public void initializeFixture() throws Exception {
        binder = PropertyBinder.forType( ExamplePICA.class );
        bound = binder.bind( new File( "src/test/resources/example.properties" ) );
    }

    @Test
    public void shouldBindToPropertyWithMethodName() {
        assertEquals( "no conversion", bound.unadorned() );
    }

    @Test
    public void shouldBindToPropertyWithAnnotation() {
        assertEquals( "also no conversion", bound.annotated() );
    }

    @Test
    public void shouldConvertPrimitivePropertyValues() {
        assertEquals( 2, bound.intProperty() );
    }

    @Test
    public void shouldConvertWrappedPrimitivePropertyValues() {
        assertEquals( Long.valueOf( -1L ), bound.wrappedLongProperty() );
    }

    @Test
    public void shouldConvertCommaSeparatedValuedPropertyToArray() {
        assertTrue( Arrays.equals( new char[] { 'a', 'b', 'c' }, bound.charArrayProperty() ) );
    }

    @Test
    public void shouldConvertCommaSeparatedValuedPropertyToList() {
        assertEquals( Arrays.asList( 'd', 'e', 'f' ), bound.charListProperty() );
    }
}
