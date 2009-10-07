package com.pholser.util.properties;

import static com.pholser.util.properties.internal.Objects.*;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubstitutableProperties extends Properties {
    private static final long serialVersionUID = 1L;
    private static final Pattern REFERENCE = Pattern.compile( "\\[(.*?)\\]" );

    @Override
    public String getProperty( String key ) {
        String previous = null;
        String substituted = super.getProperty( key );

        while ( !areEqual( previous, substituted ) ) {
            previous = substituted;
            substituted = substituteReferences( previous );
        }

        return substituted;
    }

    private String substituteReferences( String value ) {
        Matcher matcher = REFERENCE.matcher( value );
        StringBuffer buffer = new StringBuffer();
        while ( matcher.find() )
            matcher.appendReplacement( buffer, super.getProperty( matcher.group( 1 ) ) );
        matcher.appendTail( buffer );
        return buffer.toString();
    }
}
