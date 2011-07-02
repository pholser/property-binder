package com.pholser.util.properties.examples;

import java.io.File;

import com.pholser.util.properties.BoundProperty;

public interface Config {
    @BoundProperty("/config/timeout")
    long timeout();

    @BoundProperty("/config/output-file")
    File outputFile();
}
