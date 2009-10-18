package com.pholser.util.properties.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.pholser.util.properties.BoundProperty;

class PICAHelpers {
    static {
        new PICAHelpers();
    }

    private PICAHelpers() {
        // nothing to do here
    }

    static String propertyNameFor( Method method ) {
        BoundProperty marker = method.getAnnotation( BoundProperty.class );
        return marker != null ? marker.value() : method.getDeclaringClass().getName() + '.' + method.getName();
    }

    static Object annotationDefault( Class<? extends Annotation> type, String methodName ) {
        try {
            return type.getMethod( methodName ).getDefaultValue();
        }
        catch ( NoSuchMethodException ex ) {
            throw new AssertionError( ex );
        }
    }
}
