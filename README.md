# Property Binder: Typed access to sources of configuration in Java

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pholser/property-binder/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.pholser/property-binder)

The Property Binder Java library provides typed access to entries
in properties files or other sources of configuration. It offers such access
by allowing a programmer to provide it a Java interface whose methods
represent the keys of the properties file. Programmers annotate these methods
to indicate:
  * What property the method represents
  * What default value(s) it should assume if the property is not present
  * What pattern separates the individual values of multi-valued properties,
    and what formatting hints to use, if any

Property Binder is compatible with JDK 8 or better, and its JAR includes JPMS
metadata. Thus, you can use Property Binder as a module on the module path,
or as a regular JAR on the class path.

Property Binder builds on work described in
[this blog post](https://lemnik.wordpress.com/2007/03/28/code-at-runtime-in-java-56/).
That post, along with a similar technique used in
[JewelCli](https://github.com/lexicalscope/jewelcli) and
[Preon](https://github.com/preon/preon), demonstrate a means of declarative
programming in Java that seemed unique and powerful and enough to deserve
its own name: PICA (Proxied Interfaces Configured with Annotations).
[This article](http://www.devx.com/Java/Article/42492) describes the PICA
technique and the beginnings of Property Binder.


## TL;DR

* Create an interface whose methods represent keys in a properties file,
map, resource bundle, or other source of configuration.
* Annotate the methods of the interface as needed to designate property keys,
default values, etc.
* Create an instance of `PropertyBinder`, giving it the `Class` object
of your interface.
* Stamp out instances of your interface that are bound to specific
configuration sources using `bind()`.


## Examples

Given this properties file:

https://github.com/pholser/property-binder/blob/555f94d49fabe48645b722f6dc53e7f6a98fd285/com.pholser.util.properties.propertybinder.it/src/test/resources/example.properties

and this interface:

https://github.com/pholser/property-binder/blob/7d38472ab4918d1b0f7b98f5b4612c9cb64432f2/com.pholser.util.properties.propertybinder.it/src/test/java/com/pholser/util/properties/examples/ExampleSchema.java

Then the following tests should pass:

https://github.com/pholser/property-binder/blob/7d38472ab4918d1b0f7b98f5b4612c9cb64432f2/com.pholser.util.properties.propertybinder.it/src/test/java/com/pholser/util/properties/examples/ExampleTest.java


## So?

By presenting bits of configuration to your application as instances of
a Java interface, you decouple the things that use the configuration
from the means by which they're read/stored. You thereby enable easier testing
of those pieces of your application that use the configuration -- supply
mocks or stubs of the interface that answer different values for
the configuration properties.

By letting Property Binder create instances of those interfaces for you,
you relieve your application of the grunt work of converting configuration
values to sensible Java types, supplying default values, and so forth.


## Converting values

Out of the box, Property Binder can convert property values to these and
more types:

* all the Java primitives and their object counterparts
* any `enum` type
* `java.math.Big(Decimal|Integer)`
* `java.util.Date`
* many date/time concepts from `java.time`
* `java.io.File`
* `java.net.(InetAddress|URI|URL)`
* `java.lang.Class`
* arrays of the above
* `java.util.List`s of the above
* `Optional` of the above; also `Optional(Int|Long|Double)`

To convert different kinds of values, or to complement the conversions
for supported types, register subclasses of `Conversion` as a
[service](https://docs.oracle.com/javase/8/docs/api/java/util/ServiceLoader.html)
of type `com.pholser.util.properties.conversions.Conversion`.

When converting a given property value, Property Binder tries the
registered conversions in an unspecified order until one succeeds.
If none succeeds, Property Binder raises an `IllegalArgumentException`.
For congruency with the supported conversions, you should arrange for
your custom conversions to raise `IllegalArgumentException` if something
goes wrong during the conversion of a property value.


## Other sources of configuration

Property Binder admits properties files, resource bundles, and string-keyed
maps out of the box. You can bind other types of string-keyed configuration
by providing an implementation of interface `PropertySource`.


## Validation

You may mark return types and/or parameter types of interface methods with
Bean Validation API annotations. To enforce the validations, call fluent
method `validated()` on your instance of `PropertyBinder`. Then:

* Any zero-parameter methods will have their corresponding property values
  validated when the instance `bind()`s a source of configuration
* Any methods with one or more parameters, upon invocation, will validate
  the corresponding arguments and resulting property value.


### TODO:
* Java 8 date/time artifacts (+ array/list/opt) tests
* `Optional(Int|Long|Double)` (+ array/list/opt) tests
* Allow for simple reflective cases? One-arg String ctor, valueOf?
* `List<String> patterns` -> `List<U>`, where U is another converted thing?
  (DateTimeFormatter? SimpleDateFormat? Pattern? ...)
* Chained conversions (e.g. Path <- URI <- String)?

* Docs:
  * Demonstrate in tests whether modifying the underlying config
    store affects the answers a proxy gives
  * Publish Javadocs via javadocs.io
