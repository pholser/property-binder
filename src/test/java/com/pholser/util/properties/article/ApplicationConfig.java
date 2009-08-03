package com.pholser.util.properties.article;

import java.io.File;
import java.net.URL;
import java.util.List;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.DefaultsTo;
import com.pholser.util.properties.ValuesSeparatedBy;

public interface ApplicationConfig {
    @BoundProperty( "request.recipient" )
    @DefaultsTo( "http://example.com" )
    URL requestRecipient();

    @DefaultsTo( "30000" )
    long timeoutInMilliseconds();

    @BoundProperty( "search.paths" )
    @ValuesSeparatedBy( "\\s*,\\s*" )
    List<File> searchPaths();
}
