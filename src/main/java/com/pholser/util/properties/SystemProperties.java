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

package com.pholser.util.properties;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

/**
 * <p>
 * PICA interface for the standard {@link System#getProperties() system properties}.
 * </p>
 *
 * @author <a href="http://www.pholser.com">Paul Holser</a>
 */
public interface SystemProperties {
    /**
     * Accessor for system property {@code java.version}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.version}
     */
    @BoundProperty("java.version")
    String javaVersion();

    /**
     * Accessor for system property {@code java.vendor}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.vendor}
     */
    @BoundProperty("java.vendor")
    String javaVendor();

    /**
     * Accessor for system property {@code java.vendor.url}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.vendor.url}
     */
    @BoundProperty("java.vendor.url")
    URL javaVendorUrl();

    /**
     * Accessor for system property {@code java.home}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.home}
     */
    @BoundProperty("java.home")
    File javaHome();

    /**
     * Accessor for system property {@code java.vm.specification.version}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.vm.specification.version}
     */
    @BoundProperty("java.vm.specification.version")
    String javaVmSpecificationVersion();

    /**
     * Accessor for system property {@code java.vm.specification.vendor}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.vm.specification.vendor}
     */
    @BoundProperty("java.vm.specification.vendor")
    String javaVmSpecificationVendor();

    /**
     * Accessor for system property {@code java.vm.specification.name}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.vm.specification.name}
     */
    @BoundProperty("java.vm.specification.name")
    String javaVmSpecificationName();

    /**
     * Accessor for system property {@code java.vm.version}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.vm.version}
     */
    @BoundProperty("java.vm.version")
    String javaVmVersion();

    /**
     * Accessor for system property {@code java.vm.vendor}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.vendor}
     */
    @BoundProperty("java.vm.vendor")
    String javaVmVendor();

    /**
     * Accessor for system property {@code java.vm.name}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.vm.name}
     */
    @BoundProperty("java.vm.name")
    String javaVmName();

    /**
     * Accessor for system property {@code java.specification.version}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.specification.version}
     */
    @BoundProperty("java.specification.version")
    String javaSpecificationVersion();

    /**
     * Accessor for system property {@code java.specification.vendor}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.specification.vendor}
     */
    @BoundProperty("java.specification.vendor")
    String javaSpecificationVendor();

    /**
     * Accessor for system property {@code java.specification.name}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.specification.name}
     */
    @BoundProperty("java.specification.name")
    String javaSpecificationName();

    /**
     * Accessor for system property {@code java.class.version}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.class.version}
     */
    @BoundProperty("java.class.version")
    BigDecimal javaClassVersion();

    /**
     * Accessor for system property {@code java.class.path}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.class.path}
     */
    @BoundProperty("java.class.path")
    @ValuesSeparatedBy(valueOf = "[path.separator]")
    List<File> javaClassPath();

    /**
     * Accessor for system property {@code java.library.path}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.library.path}
     */
    @BoundProperty("java.library.path")
    @ValuesSeparatedBy(valueOf = "[path.separator]")
    List<File> javaLibraryPath();

    /**
     * Accessor for system property {@code java.io.tmpdir}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.io.tmpdir}
     */
    @BoundProperty("java.io.tmpdir")
    File javaIoTmpdir();

    /**
     * Accessor for system property {@code java.compiler}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.compiler}
     */
    @BoundProperty("java.compiler")
    String javaCompiler();

    /**
     * Accessor for system property {@code java.ext.dirs}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code java.ext.dirs}
     */
    @BoundProperty("java.ext.dirs")
    @ValuesSeparatedBy(valueOf = "[path.separator]")
    List<File> javaExtDirs();

    /**
     * Accessor for system property {@code os.name}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code os.name}
     */
    @BoundProperty("os.name")
    String osName();

    /**
     * Accessor for system property {@code os.arch}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code os.arch}
     */
    @BoundProperty("os.arch")
    String osArch();

    /**
     * Accessor for system property {@code os.version}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code os.version}
     */
    @BoundProperty("os.version")
    String osVersion();

    /**
     * Accessor for system property {@code file.separator}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code file.separator}
     */
    @BoundProperty("file.separator")
    char fileSeparator();

    /**
     * Accessor for system property {@code path.separator}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code path.separator}
     */
    @BoundProperty("path.separator")
    char pathSeparator();

    /**
     * Accessor for system property {@code line.separator}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code line.separator}
     */
    @BoundProperty("line.separator")
    String lineSeparator();

    /**
     * Accessor for system property {@code user.name}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code user.name}
     */
    @BoundProperty("user.name")
    String userName();

    /**
     * Accessor for system property {@code user.home}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code user.home}
     */
    @BoundProperty("user.home")
    File userHome();

    /**
     * Accessor for system property {@code user.dir}.
     *
     * @return {@link System#getProperty(String) System.getProperty} of {@code user.dir}
     */
    @BoundProperty("user.dir")
    File userDir();
}
