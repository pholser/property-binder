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

package com.pholser.util.properties.it;

import com.pholser.util.properties.PropertyBinder;
import com.pholser.util.properties.SystemProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BindingSystemPropertiesToTypedInterfaceTest {
  private SystemProperties systemProperties;

  @BeforeEach void setUp() {
    systemProperties = PropertyBinder.getSystemProperties();
  }

  @Test void javaVersion() {
    assertEquals(
      System.getProperty("java.version"),
      systemProperties.javaVersion());
  }

  @Test void javaVendor() {
    assertEquals(
      System.getProperty("java.vendor"),
      systemProperties.javaVendor());
  }

  @Test void javaVendorUrl() {
    assertEquals(
      System.getProperty("java.vendor.url"),
      systemProperties.javaVendorUrl().toExternalForm());
  }

  @Test void javaHome() {
    assertEquals(
      System.getProperty("java.home"),
      systemProperties.javaHome().getPath());
  }

  @Test void javaVmSpecificationVersion() {
    assertEquals(
      System.getProperty("java.vm.specification.version"),
      systemProperties.javaVmSpecificationVersion());
  }

  @Test void javaVmSpecificationVendor() {
    assertEquals(
      System.getProperty("java.vm.specification.vendor"),
      systemProperties.javaVmSpecificationVendor());
  }

  @Test void javaVmSpecificationName() {
    assertEquals(
      System.getProperty("java.vm.specification.name"),
      systemProperties.javaVmSpecificationName());
  }

  @Test void javaVmVersion() {
    assertEquals(
      System.getProperty("java.vm.version"),
      systemProperties.javaVmVersion());
  }

  @Test void javaVmVendor() {
    assertEquals(
      System.getProperty("java.vm.vendor"),
      systemProperties.javaVmVendor());
  }

  @Test void javaVmName() {
    assertEquals(
      System.getProperty("java.vm.name"),
      systemProperties.javaVmName());
  }

  @Test void javaSpecificationVersion() {
    assertEquals(
      System.getProperty("java.specification.version"),
      systemProperties.javaSpecificationVersion());
  }

  @Test void javaSpecificationVendor() {
    assertEquals(
      System.getProperty("java.specification.vendor"),
      systemProperties.javaSpecificationVendor());
  }

  @Test void javaSpecificationName() {
    assertEquals(
      System.getProperty("java.specification.name"),
      systemProperties.javaSpecificationName());
  }

  @Test void javaClassVersion() {
    assertEquals(
      System.getProperty("java.class.version"),
      systemProperties.javaClassVersion().toString());
  }

  @Test void javaIoTmpdir() {
    assertEquals(
      System.getProperty("java.io.tmpdir"),
      systemProperties.javaIoTmpdir().getPath()
        + systemProperties.fileSeparator());
  }

  @Test void javaCompiler() {
    assertEquals(
      System.getProperty("java.compiler"),
      systemProperties.javaCompiler());
  }

  @Test void shouldGiveOsName() {
    assertEquals(System.getProperty("os.name"), systemProperties.osName());
  }

  @Test void shouldGiveOsArch() {
    assertEquals(System.getProperty("os.arch"), systemProperties.osArch());
  }

  @Test void shouldGiveOsVersion() {
    assertEquals(
      System.getProperty("os.version"),
      systemProperties.osVersion());
  }

  @Test void shouldGiveFileSeparator() {
    assertEquals(
      System.getProperty("file.separator"),
      String.valueOf(systemProperties.fileSeparator()));
  }

  @Test void shouldGivePathSeparator() {
    assertEquals(
      System.getProperty("path.separator"),
      String.valueOf(systemProperties.pathSeparator()));
  }

  @Test void shouldGiveLineSeparator() {
    assertEquals(
      System.getProperty("line.separator"),
      systemProperties.lineSeparator());
  }

  @Test void shouldGiveUserName() {
    assertEquals(System.getProperty("user.name"), systemProperties.userName());
  }

  @Test void shouldGiveUserHome() {
    assertEquals(
      System.getProperty("user.home"),
      systemProperties.userHome().getPath());
  }

  @Test void shouldGiveUserDir() {
    assertEquals(
      System.getProperty("user.dir"),
      systemProperties.userDir().getPath());
  }

  @Test void javaClassPath() {
    assertEquals(
      toFiles(System.getProperty("java.class.path")),
      systemProperties.javaClassPath());
  }

  @Test void javaLibraryPath() {
    assertEquals(
      toFiles(System.getProperty("java.library.path")),
      systemProperties.javaLibraryPath());
  }

  private static List<File> toFiles(String path) {
    return Arrays.stream(path.split(System.getProperty("path.separator")))
      .map(File::new)
      .collect(toList());
  }
}
