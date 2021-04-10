/*
 The MIT License

 Copyright (c) 2009-2021 Paul R. Holser, Jr.

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

package com.pholser.util.properties;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BindingSystemPropertiesToTypedInterfaceTest {
  private SystemProperties systemProperties;

  @Before public void setUp() {
    systemProperties = PropertyBinder.getSystemProperties();
  }

  @Test public void javaVersion() {
    assertEquals(System.getProperty("java.version"), systemProperties.javaVersion());
  }

  @Test public void javaVendor() {
    assertEquals(System.getProperty("java.vendor"), systemProperties.javaVendor());
  }

  @Test public void javaVendorUrl() {
    assertEquals(System.getProperty("java.vendor.url"), systemProperties.javaVendorUrl().toExternalForm());
  }

  @Test public void javaHome() {
    assertEquals(System.getProperty("java.home"), systemProperties.javaHome().getPath());
  }

  @Test public void javaVmSpecificationVersion() {
    assertEquals(System.getProperty("java.vm.specification.version"), systemProperties.javaVmSpecificationVersion());
  }

  @Test public void javaVmSpecificationVendor() {
    assertEquals(System.getProperty("java.vm.specification.vendor"), systemProperties.javaVmSpecificationVendor());
  }

  @Test public void javaVmSpecificationName() {
    assertEquals(System.getProperty("java.vm.specification.name"), systemProperties.javaVmSpecificationName());
  }

  @Test public void javaVmVersion() {
    assertEquals(System.getProperty("java.vm.version"), systemProperties.javaVmVersion());
  }

  @Test public void javaVmVendor() {
    assertEquals(System.getProperty("java.vm.vendor"), systemProperties.javaVmVendor());
  }

  @Test public void javaVmName() {
    assertEquals(System.getProperty("java.vm.name"), systemProperties.javaVmName());
  }

  @Test public void javaSpecificationVersion() {
    assertEquals(System.getProperty("java.specification.version"), systemProperties.javaSpecificationVersion());
  }

  @Test public void javaSpecificationVendor() {
    assertEquals(System.getProperty("java.specification.vendor"), systemProperties.javaSpecificationVendor());
  }

  @Test public void javaSpecificationName() {
    assertEquals(System.getProperty("java.specification.name"), systemProperties.javaSpecificationName());
  }

  @Test public void javaClassVersion() {
    assertEquals(System.getProperty("java.class.version"), systemProperties.javaClassVersion().toString());
  }

  @Test public void javaIoTmpdir() {
    assertEquals(
      System.getProperty("java.io.tmpdir"),
      systemProperties.javaIoTmpdir().getPath() + systemProperties.fileSeparator());
  }

  @Test public void javaCompiler() {
    assertEquals(System.getProperty("java.compiler"), systemProperties.javaCompiler());
  }

  @Test public void shouldGiveOsName() {
    assertEquals(System.getProperty("os.name"), systemProperties.osName());
  }

  @Test public void shouldGiveOsArch() {
    assertEquals(System.getProperty("os.arch"), systemProperties.osArch());
  }

  @Test public void shouldGiveOsVersion() {
    assertEquals(System.getProperty("os.version"), systemProperties.osVersion());
  }

  @Test public void shouldGiveFileSeparator() {
    assertEquals(System.getProperty("file.separator"), String.valueOf(systemProperties.fileSeparator()));
  }

  @Test public void shouldGivePathSeparator() {
    assertEquals(System.getProperty("path.separator"), String.valueOf(systemProperties.pathSeparator()));
  }

  @Test public void shouldGiveLineSeparator() {
    assertEquals(System.getProperty("line.separator"), systemProperties.lineSeparator());
  }

  @Test public void shouldGiveUserName() {
    assertEquals(System.getProperty("user.name"), systemProperties.userName());
  }

  @Test public void shouldGiveUserHome() {
    assertEquals(System.getProperty("user.home"), systemProperties.userHome().getPath());
  }

  @Test public void shouldGiveUserDir() {
    assertEquals(System.getProperty("user.dir"), systemProperties.userDir().getPath());
  }

  @Test public void javaClassPath() {
    assertEquals(toFiles(System.getProperty("java.class.path")), systemProperties.javaClassPath());
  }

  @Test public void javaLibraryPath() {
    assertEquals(toFiles(System.getProperty("java.library.path")), systemProperties.javaLibraryPath());
  }

  private static List<File> toFiles(String path) {
    String[] pieces = path.split(System.getProperty("path.separator"));
    List<File> files = new ArrayList<>(pieces.length);
    for (String each : pieces)
      files.add(new File(each));
    return files;
  }
}
