package com.pholser.util.properties.examples.ant.filters;

import org.apache.tools.ant.filters.BaseFilterReader;
import org.apache.tools.ant.filters.ChainableReader;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Ant filter class that transforms HTML special characters into their equivalent entities.
 *
 * @author <a href="http://www.pholser.com">Paul Holser</a>
 */
public class HTMLEntitifier extends BaseFilterReader implements ChainableReader {
    private static final Map<Integer, String> ENTITIES = new HashMap<Integer, String>();

    static {
        ENTITIES.put((int) '<', "&lt;");
        ENTITIES.put((int) '>', "&gt;");
        ENTITIES.put((int) '"', "&quot;");
        ENTITIES.put((int) '&', "&amp;");
    }

    private String replacementData;
    private int replacementIndex = -1;

    /**
     * Creates "dummy" instances.
     */
    public HTMLEntitifier() {
        // empty on purpose
    }

    public HTMLEntitifier(Reader source) {
        super(source);
    }

    public Reader chain(Reader source) {
        HTMLEntitifier newFilter = new HTMLEntitifier(source);
        newFilter.setInitialized(true);
        return newFilter;
    }

    @Override public int read() throws IOException {
        if (!getInitialized())
            setInitialized(true);

        if (replacementIndex > -1) {
            char ch = replacementData.charAt(replacementIndex++);

            if (replacementIndex >= replacementData.length())
                replacementIndex = -1;

            return ch;
        }

        int nextChar = in.read();

        if (ENTITIES.containsKey(nextChar)) {
            replacementData = ENTITIES.get(nextChar);
            replacementIndex = 1;
            return replacementData.charAt(0);
        }

        return nextChar;
    }
}
