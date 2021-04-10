package com.pholser.util.properties.examples;

import com.pholser.util.properties.BoundProperty;

import java.io.File;

public interface Config {
    @BoundProperty("/config/timeout")
    long timeout();

    @BoundProperty("/config/output-file")
    File outputFile();
}
