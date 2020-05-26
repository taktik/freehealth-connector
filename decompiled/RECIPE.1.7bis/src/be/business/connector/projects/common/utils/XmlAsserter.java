/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.taktik.connector.business.recipeprojects.common.utils;
import org.taktik.connector.business.recipeprojects.common.utils.xmlunit.RecursiveNameAttributeAndTextQualifier;
import org.taktik.connector.business.recipeprojects.common.utils.xmlunit.RegexDifferenceListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import be.fgov.ehealth.technicalconnector.tests.utils.xmlunit.RecursiveNameAttributeAndTextQualifier;
//import be.fgov.ehealth.technicalconnector.tests.utils.xmlunit.RegexDifferenceListener;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.custommonkey.xmlunit.*;
import org.junit.Assert;
import org.w3c.dom.Document;

@SuppressWarnings("rawtypes")
public class XmlAsserter {

    public static class AssertResult {
        private boolean similar;
        private List<Difference> differences = new ArrayList<Difference>();

        void setSimilar(boolean similar) {
            this.similar = similar;
        }

        public boolean isSimilar() {
            return similar;
        }

        public List<Difference> getDifferences() {
            return differences;
        }
    }

    private static final Logger LOG = Logger.getLogger(XmlAsserter.class);

    private static Map<Class, JAXBContext> jaxbContextMap = new HashMap<Class, JAXBContext>();

    private static DifferenceListener listener = new RegexDifferenceListener();

    private static ElementQualifier qualifier = new RecursiveNameAttributeAndTextQualifier();

    static {
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
    }

    public static void assertSimilar(Object expected, Object actual) {
        assertSimilar(toString(expected), toString(actual));
    }

    public static void assertSimilar(DOMSource expected, DOMSource actual) {
        assertSimilar(toString(expected), toString(actual));
    }

    public static void assertSimilar(Document expected, Document actual) {
        assertSimilar(toString(expected), toString(actual));
    }

    public static void assertSimilar(String expected, String actual) {
        boolean similar = isSimilar(expected, actual);
        Assert.assertTrue("both xmls should be similar", similar);
    }

    public static boolean isSimilar(String expected, String actual) {
        return assertXml(expected, actual).isSimilar();
    }

    public static List<Difference> getDifferences(DOMSource expected, DOMSource actual) {
        return assertXml(toString(expected), toString(actual)).getDifferences();
    }



    @SuppressWarnings("unchecked")
    public static AssertResult assertXml(String expected, String actual) {
        AssertResult result = new AssertResult();
        try {
            LOG.debug("expected  : " + linearize(expected));
            LOG.debug("actual    : " + linearize(actual));

            Diff diff = new Diff(expected, actual);
            diff.overrideDifferenceListener(listener);
            diff.overrideElementQualifier(qualifier);
            result.setSimilar(diff.similar());
            LOG.debug("Similar : " + result.isSimilar());

            DetailedDiff detDiff = new DetailedDiff(diff);
            List<Difference> differences = detDiff.getAllDifferences();
            for (Difference difference : differences) {
                if (!difference.isRecoverable()) {
                    LOG.debug(difference.toString() + " expected :" + difference.getControlNodeDetail() + " but was :" + difference.getTestNodeDetail() + "  " + difference.getDescription());
                    result.getDifferences().add(difference);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
        return result;
    }

    private static String linearize(String xml) {
        String result = xml.replaceAll("[\t\n\r]", "");
        while (result.contains(" <")) {
            result = result.replace(" <", "<");
        }
        return result;
    }

    private static String toString(Document doc) {
        return toString(new DOMSource(doc));
    }

    private static String toString(DOMSource domSource) {
        try {
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
        return null;
    }

    private static String toString(Object obj) {
        if (obj.getClass().isAnnotationPresent(XmlType.class)) {
            return convert(obj);
        } else if (obj instanceof InputStream) {
            return convert((InputStream) obj);
        }
        LOG.info("obj instanceof " + obj.getClass().getName() + " using default toString() method.");
        return obj.toString();

    }

    private static String convert(InputStream is) {
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer, "UTF-8");
            return writer.toString();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    private static String convert(Object obj) {
        try {
            Class clazz = obj.getClass();
            if (!jaxbContextMap.containsKey(clazz)) {
                jaxbContextMap.put(clazz, JAXBContext.newInstance(clazz));
            }
            JAXBContext context = jaxbContextMap.get(clazz);
            Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
            if (obj.getClass().isAnnotationPresent(XmlRootElement.class)) {
                marshaller.marshal(obj, writer);
            } else if (obj.getClass().isAnnotationPresent(XmlType.class)) {
                JAXBElement jaxbElement = new JAXBElement(new QName("", obj.getClass().getSimpleName()), clazz, obj);
                marshaller.marshal(jaxbElement, writer);
            }
            return writer.toString();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
        return null;
    }
}