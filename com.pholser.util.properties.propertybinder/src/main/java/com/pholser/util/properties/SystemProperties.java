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

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

public interface SystemProperties {
  @BoundProperty("java.version")
  String javaVersion();

  @BoundProperty("java.vendor")
  String javaVendor();

  @BoundProperty("java.vendor.url")
  URL javaVendorUrl();

  @BoundProperty("java.home")
  File javaHome();

  @BoundProperty("java.vm.specification.version")
  String javaVmSpecificationVersion();

  @BoundProperty("java.vm.specification.vendor")
  String javaVmSpecificationVendor();

  @BoundProperty("java.vm.specification.name")
  String javaVmSpecificationName();

  @BoundProperty("java.vm.version")
  String javaVmVersion();

  @BoundProperty("java.vm.vendor")
  String javaVmVendor();

  @BoundProperty("java.vm.name")
  String javaVmName();

  @BoundProperty("java.specification.version")
  String javaSpecificationVersion();

  @BoundProperty("java.specification.vendor")
  String javaSpecificationVendor();

  @BoundProperty("java.specification.name")
  String javaSpecificationName();

  @BoundProperty("java.class.version")
  BigDecimal javaClassVersion();

  @BoundProperty("java.class.path")
  @ValuesSeparatedBy(valueOf = "[path.separator]")
  List<File> javaClassPath();

  @BoundProperty("java.library.path")
  @ValuesSeparatedBy(valueOf = "[path.separator]")
  List<File> javaLibraryPath();

  @BoundProperty("java.io.tmpdir")
  File javaIoTmpdir();

  @BoundProperty("java.compiler")
  String javaCompiler();

  @BoundProperty("os.name")
  String osName();

  @BoundProperty("os.arch")
  String osArch();

  @BoundProperty("os.version")
  String osVersion();

  @BoundProperty("file.separator")
  char fileSeparator();

  @BoundProperty("path.separator")
  char pathSeparator();

  @BoundProperty("line.separator")
  String lineSeparator();

  @BoundProperty("user.name")
  String userName();

  @BoundProperty("user.home")
  File userHome();

  @BoundProperty("user.dir")
  File userDir();
}
