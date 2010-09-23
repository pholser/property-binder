package com.pholser.util.properties.internal;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.pholser.util.properties.SubstitutableProperties;

public class ResourceBundleBackedSubstitutableProperties extends SubstitutableProperties {
    private static final long serialVersionUID = 1L;

    private final ResourceBundle backing;

    public ResourceBundleBackedSubstitutableProperties(ResourceBundle bundle) {
        backing = bundle;
    }

    @Override
    protected String retrieve(String key) {
        try {
            return backing.getString(key);
        } catch (MissingResourceException ex) {
            return null;
        }
    }
}
