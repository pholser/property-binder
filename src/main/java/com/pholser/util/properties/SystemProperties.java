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
import java.util.ArrayList;
import java.util.List;

/**
 * Typed access to {@linkplain System#getProperties() system properties}.
 *
 * @author <a href="http://www.pholser.com">Paul Holser</a>
 */
public class SystemProperties implements RawSystemProperties {
    private final RawSystemProperties systemProperties;

    SystemProperties( RawSystemProperties systemProperties ) {
        this.systemProperties = systemProperties;
    }

    /**
     * @return file.separator
     */
    public char fileSeparator() {
        return systemProperties.fileSeparator();
    }

    /**
     * @return java.class.path
     */
    public String rawJavaClassPath() {
        return systemProperties.rawJavaClassPath();
    }

    /**
     * @return java.class.version
     */
    public BigDecimal javaClassVersion() {
        return systemProperties.javaClassVersion();
    }

    /**
     * @return java.compiler
     */
    public String javaCompiler() {
        return systemProperties.javaCompiler();
    }

    /**
     * @return java.ext.dirs
     */
    public String rawJavaExtDirs() {
        return systemProperties.rawJavaExtDirs();
    }

    /**
     * @return java.home
     */
    public File javaHome() {
        return systemProperties.javaHome();
    }

    /**
     * @return java.io.tmpdir
     */
    public File javaIoTmpdir() {
        return systemProperties.javaIoTmpdir();
    }

    /**
     * @return java.library.path
     */
    public String rawJavaLibraryPath() {
        return systemProperties.rawJavaLibraryPath();
    }

    /**
     * @return java.specification.name
     */
    public String javaSpecificationName() {
        return systemProperties.javaSpecificationName();
    }

    /**
     * @return java.specification.vendor
     */
    public String javaSpecificationVendor() {
        return systemProperties.javaSpecificationVendor();
    }

    /**
     * @return java.specification.version
     */
    public String javaSpecificationVersion() {
        return systemProperties.javaSpecificationVersion();
    }

    /**
     * @return java.vendor
     */
    public String javaVendor() {
        return systemProperties.javaVendor();
    }

    /**
     * @return java.vendor.url
     */
    public URL javaVendorUrl() {
        return systemProperties.javaVendorUrl();
    }

    /**
     * @return java.version
     */
    public String javaVersion() {
        return systemProperties.javaVersion();
    }

    /**
     * @return java.vm.name
     */
    public String javaVmName() {
        return systemProperties.javaVmName();
    }

    /**
     * @return java.vm.specification.name
     */
    public String javaVmSpecificationName() {
        return systemProperties.javaVmSpecificationName();
    }

    /**
     * @return java.vm.specification.version
     */
    public String javaVmSpecificationVendor() {
        return systemProperties.javaVmSpecificationVendor();
    }

    /**
     * @return java.vm.specification.version
     */
    public String javaVmSpecificationVersion() {
        return systemProperties.javaVmSpecificationVersion();
    }

    /**
     * @return java.vm.vendor
     */
    public String javaVmVendor() {
        return systemProperties.javaVmVendor();
    }

    /**
     * @return java.vm.version
     */
    public String javaVmVersion() {
        return systemProperties.javaVmVersion();
    }

    /**
     * @return line.separator
     */
    public String lineSeparator() {
        return systemProperties.lineSeparator();
    }

    /**
     * @return os.arch
     */
    public String osArch() {
        return systemProperties.osArch();
    }

    /**
     * @return os.name
     */
    public String osName() {
        return systemProperties.osName();
    }

    /**
     * @return os.version
     */
    public String osVersion() {
        return systemProperties.osVersion();
    }

    /**
     * @return path.separator
     */
    public char pathSeparator() {
        return systemProperties.pathSeparator();
    }

    /**
     * @return user.dir
     */
    public File userDir() {
        return systemProperties.userDir();
    }

    /**
     * @return user.home
     */
    public File userHome() {
        return systemProperties.userHome();
    }

    /**
     * @return user.name
     */
    public String userName() {
        return systemProperties.userName();
    }

    /**
     * @return java.class.path
     */
    public List<File> javaClassPath() {
        return toFiles( systemProperties.rawJavaClassPath() );
    }

    /**
     * @return java.library.path
     */
    public List<File> javaLibraryPath() {
        return toFiles( systemProperties.rawJavaLibraryPath() );
    }

    /**
     * @return java.ext.dirs
     */
    public List<File> javaExtDirs() {
        return toFiles( systemProperties.rawJavaExtDirs() );
    }

    private List<File> toFiles( String pathPropertyValue ) {
        String separator = String.valueOf( systemProperties.pathSeparator() );
        List<File> files = new ArrayList<File>();
        for ( String eachPiece : pathPropertyValue.split( separator ) )
            files.add( new File( eachPiece ) );
        return files;
    }
}
