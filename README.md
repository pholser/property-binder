# Property Binder: Typed access to sources of configuration in Java

![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pholser/property-binder/badge.svg?style=plastic)

The Property Binder Java library provides typed access to entries
in properties files or other sources of configuration. It offers such access
by allowing a programmer to provide it a Java interface whose methods
represent the keys of the properties file. Programmers annotate these methods
can be annotated to indicate what property the method represents,
what default value(s) it should assume if the property is not present,
what pattern separates the individual values of multi-valued properties,
and what formatting hints to use, if any.

Property Binder admits properties files, resource bundles, and string-keyed
maps out of the box. You can bind other types of string-keyed configuration
by providing an implementation of interface `PropertySource`.

Property Binder builds on work described in
[this blog post](https://lemnik.wordpress.com/2007/03/28/code-at-runtime-in-java-56/).
That post, along with a similar technique used in
[JewelCli](https://github.com/lexicalscope/jewelcli) and
[Preon](https://github.com/preon/preon), demonstrate a means of declarative
programming in Java that seemed unique and powerful and enough to deserve
its own name: PICA (Proxied Interfaces Configured with Annotations).
The PICA technique and the beginnings of Property Binder are described
in [this article](http://www.devx.com/Java/Article/42492).


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

### TODO:
* Java 8 date/time artifacts (+ array/list/opt) tests
* Optional(Int|Long|Double) (+ array/list/opt) tests
* Allow for simple reflective cases? One-arg String ctor, valueOf?
* List<String> patterns -> List<U>, where U is another converted thing?
  (DateTimeFormatter? SimpleDateFormat? Pattern? ...)
* Chained conversions (e.g. Path <- URI <- String)?
* Clarify in documentation whether modifying the underlying config
  store affects the answers a proxy gives

* Make README be the docs
* Restore Javadocs
* Publish Javadocs via javadocs.io
* Current Maven version in README
