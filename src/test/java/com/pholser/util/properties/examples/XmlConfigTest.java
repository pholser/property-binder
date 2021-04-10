package com.pholser.util.properties.examples;

import com.pholser.util.properties.BoundProperty;
import com.pholser.util.properties.PropertyBinder;
import com.pholser.util.properties.PropertySource;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;

import static org.junit.Assert.assertEquals;

public class XmlConfigTest {
    private Config config;

    @Before public final void initialize() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(getClass().getResourceAsStream("/config.xml"));

        PropertyBinder<Config> binder = PropertyBinder.forType(Config.class);
        config = binder.bind(new XmlConfigSource(document));
    }

    @Test public void retrievesTimeoutValue() {
        assertEquals(5000L, config.timeout());
    }

    @Test public void retrievesOutputFileValue() {
        assertEquals(new File("/home/joeblow/out.txt"), config.outputFile());
    }
}

class XmlConfigSource implements PropertySource {
    private final Document document;
    private final XPath xpath;

    XmlConfigSource(Document document) {
        this.document = document;
        xpath = XPathFactory.newInstance().newXPath();
    }

    @Override public Object propertyFor(BoundProperty key) {
        try {
            return xpath.compile(key.value()).evaluate(document);
        } catch (XPathExpressionException e) {
            throw new IllegalStateException(e);
        }
    }
}
