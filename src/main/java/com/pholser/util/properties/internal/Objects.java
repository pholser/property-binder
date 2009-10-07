package com.pholser.util.properties.internal;

public class Objects {
    static {
        new Objects();
    }

    private Objects() {
        // nothing to do here
    }

    public static boolean areEqual( Object first, Object second ) {
        return first == null ? second == null : first.equals( second );
    }
}
