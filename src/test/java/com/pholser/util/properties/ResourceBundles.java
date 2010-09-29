package com.pholser.util.properties;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;

class ResourceBundles {
    static {
        new ResourceBundles();
    }

    private ResourceBundles() {
        // nothing to do here
    }

    static ResourceBundle bundleWith(final String key, final Object value) {
        return bundleWith(new Object[][] { { key, value } });
    }

    static ResourceBundle bundleWith(final String firstKey, final Object firstValue, final String secondKey,
        final Object secondValue) {

        return bundleWith(new Object[][] { { firstKey, firstValue }, { secondKey, secondValue } });
    }

    private static ResourceBundle bundleWith(final Object[][] items) {
        return new ListResourceBundle() {
            @Override
            protected Object[][] getContents() {
                return items;
            }
        };
    }
}
