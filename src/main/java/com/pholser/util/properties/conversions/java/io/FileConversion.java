package com.pholser.util.properties.conversions.java.io;

import com.pholser.util.properties.Conversion;
import com.pholser.util.properties.ParsedAs;

import java.io.File;

public class FileConversion extends Conversion<File> {
  public FileConversion() {
    super(File.class);
  }

  @Override public File convert(String formatted, ParsedAs patterns) {
    return new File(formatted);
  }
}
