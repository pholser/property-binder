package com.pholser.util.properties;

import static com.pholser.util.properties.internal.Objects.*;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Properties class with support for property values which can be comprised of the values of other
 * properties.  Such values specify other property keys delimited by {@code [} and {@code ]}.</p>
 *
 * <p>Inspired by <a href="http://www2.sys-con.com/ITSG/virtualcd/Java/archives/0612/mair/index.html">Enabling
 * Constant Substitution in Property Values</a>.
 *
 * @author <a href="http://www.pholser.com">Paul Holser</a>
 */
public class SubstitutableProperties extends Properties {
    private static final long serialVersionUID = 1L;
    private static final Pattern REFERENCE = Pattern.compile( "\\[(.*?)\\]" );

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProperty( String key ) {
        return substitute( super.getProperty( key ), this );
    }

    /**
     * Performs property value substitution on the given value, drawing on the values of the given collection
     * of properties.
     *
     * @param value the value to perform substitution on
     * @param properties the properties from which to draw substitution values
     * @return the result of the substitution
     */
    public static String substitute( String value, Properties properties ) {
        String previous = null;
        String substituted = value;

        while ( !areEqual( previous, substituted ) ) {
            previous = substituted;
            substituted = substituteReferences( previous, properties );
        }

        return substituted;
    }

    private static String substituteReferences( String value, Properties properties ) {
        Matcher matcher = REFERENCE.matcher( value );
        StringBuffer buffer = new StringBuffer();
        while ( matcher.find() )
            matcher.appendReplacement( buffer, properties.getProperty( matcher.group( 1 ) ) );
        matcher.appendTail( buffer );
        return buffer.toString();
    }
}
