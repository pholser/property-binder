package com.pholser.util.properties.internal;

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
}
