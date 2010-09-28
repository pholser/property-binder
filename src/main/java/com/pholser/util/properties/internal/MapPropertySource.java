package com.pholser.util.properties.internal;

import java.util.Map;

import com.pholser.util.properties.PropertySource;

public class MapPropertySource implements PropertySource {
    private final Map<String, ?> backing;

    public MapPropertySource(Map<String, ?> backing) {
        this.backing = backing;
    }

    @Override
    public Object propertyFor(String key) {
        return backing.get(key);
    }
}
