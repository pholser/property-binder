/*
 The MIT License

 Copyright (c) 2009 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.pholser.util.properties.article;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.pholser.util.properties.PropertyBinder;

@SuppressWarnings( "unused" )
public class UntypedPropertyAccess {
    public static void main( String... args ) throws Exception {
        UntypedPropertyAccess access = new UntypedPropertyAccess();
        access.lameWay();
        access.picaWay();
    }

    private void lameWay() throws Exception {
        InputStream propertyIn = getClass().getResourceAsStream( "/app-config.properties" );
        Properties properties = new Properties();
        properties.load( propertyIn );
        propertyIn.close();

        URL requestRecipient = new URL( properties.getProperty( "request.recipient" ) );
        long timeoutInMilliseconds = Long.valueOf( properties.getProperty( "timeoutInMilliseconds" ) );
        String[] pieces = properties.getProperty( "search.paths" ).split( "\\s*,\\s*" );
        List<File> searchPaths = new ArrayList<File>();
        for ( String each : pieces )
            searchPaths.add( new File( each ) );
    }

    private void picaWay() throws Exception {
        String[] args = { "" };
        InputStream propertyIn = getClass().getResourceAsStream( "/app-config.properties" );
        ApplicationConfig config = PropertyBinder.forType( ApplicationConfig.class ).bind( propertyIn );
        URL requestRecipient = config.requestRecipient();
        long timeoutInMilliseconds = config.timeoutInMilliseconds();
        List<File> searchPaths = config.searchPaths();
    }
}
