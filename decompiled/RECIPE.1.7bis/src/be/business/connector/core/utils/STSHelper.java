/**
 * Copyright (C) 2010 Recip-e
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.taktik.connector.business.recipeprojects.core.utils;

import java.io.StringWriter;
import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import be.apb.gfddpp.domain.PharmacyIdType;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;

/**
 * The Class STSHelper.
 */
public class STSHelper {

    /**
     * The Constant SAML_ATTRIBUTE_NAMESPACE.
     */
    private static final String SAML_ATTRIBUTE_NAMESPACE = "AttributeNamespace";

    /**
     * The Constant SAML_ATTRIBUTE_NAME.
     */
    private static final String SAML_ATTRIBUTE_NAME = "AttributeName";

    /**
     * The Constant SAML_CONDITIONS.
     */
    public static final String SAML_CONDITIONS = "Conditions";

    /**
     * The Constant SAML_NOTONORAFTER.
     */
    public static final String SAML_NOTONORAFTER = "NotOnOrAfter";

    /**
     * The Constant SAML_SUCCESS.
     */
    public static final String SAML_SUCCESS = "samlp:Success";

    /**
     * The Constant SAML_STATUSCODE.
     */
    public static final String SAML_STATUSCODE = "StatusCode";

    /**
     * The Constant SAML_STATUSMESSAGE.
     */
    public static final String SAML_STATUSMESSAGE = "StatusMessage";

    /**
     * The Constant SAML_VALUE.
     */
    public static final String SAML_VALUE = "Value";

    /**
     * The Constant SAML_ASSERTION.
     */
    public static final String SAML_ASSERTION = "Assertion";

    /**
     * The Constant SAML_ATTRIBUTESTATEMENT.
     */
    public static final String SAML_ATTRIBUTESTATEMENT = "AttributeStatement";

    /**
     * The Constant SAML_ATTRIBUTE.
     */
    public static final String SAML_ATTRIBUTE = "Attribute";
    private static final String ASSERTION_URI = "urn:oasis:names:tc:SAML:1.0:assertion";
    private static final String PHARMACIST_NIHII_URI = "urn:be:fgov:ehealth:1.0:pharmacy:nihii-number";
    private static final String PHARMACIST_NIHII_URI1 = "urn:be:fgov:ehealth:1.0:nihii:pharmacy:nihii-number";
    private static final String MIDWIFE_NIHII_URI = "urn:be:fgov:person:ssin:ehealth:1.0:nihii:midwife:nihii11";
    private static final String MIDWIFE_NIHII_URI1 = "urn:be:fgov:person:ssin:ehealth:1.0:midwife:nihii11";
    private static final String PHYSIOTHERAPIST_NIHII_URI = "urn:be:fgov:person:ssin:ehealth:1.0:nihii:physiotherapist:nihii11";
    private static final String PHYSIOTHERAPIST_NIHII_URI1 = "urn:be:fgov:person:ssin:ehealth:1.0:physiotherapist:nihii11";
    private static final String NURSE_NIHII_URI = "urn:be:fgov:person:ssin:ehealth:1.0:nihii:nurse:nihii11";
    private static final String NURSE_NIHII_URI1 = "urn:be:fgov:person:ssin:ehealth:1.0:nurse:nihii11";
    private static final String DENTIST_NIHII_URI = "urn:be:fgov:person:ssin:ehealth:1.0:nihii:dentist:nihii11";
    private static final String DENTIST_NIHII_URI1 = "urn:be:fgov:person:ssin:ehealth:1.0:dentist:nihii11";
    private static final String INDENTIFICATION_NAME_SPACE = "urn:be:fgov:identification-namespace";
    private static final String CERTIFIERD_NAME_SPACE = "urn:be:fgov:certified-namespace:ehealth";
    private static final String DOCTOR_NIHII_URI = "urn:be:fgov:person:ssin:ehealth:1.0:doctor:nihii11";
    private static final String DOCTOR_NIHII_URI1 = "urn:be:fgov:person:ssin:ehealth:1.0:nihii:doctor:nihii11";

    /**
     * Gets the status code.
     *
     * @param stsResponse the sts response
     * @return the status code
     */
    public static String getStatusCode(Element stsResponse) {
        return stsResponse.getElementsByTagName(SAML_STATUSCODE).item(0).getAttributes().getNamedItem(SAML_VALUE).getNodeValue();
    }

    /**
     * Gets the status message.
     *
     * @param stsResponse the sts response
     * @return the status message
     */
    public static String getStatusMessage(Element stsResponse) {
        try {
            return stsResponse.getElementsByTagName(SAML_STATUSMESSAGE).item(0).getTextContent();
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Gets the not on or after conditions.
     *
     * @param stsResponse the sts response
     * @return the not on or after conditions
     */
    public static Calendar getNotOnOrAfterConditions(Element stsResponse) {
        return DatatypeConverter.parseDate(stsResponse.getElementsByTagName(SAML_CONDITIONS).item(0).getAttributes().getNamedItem(SAML_NOTONORAFTER).getTextContent());
    }

    /**
     * Gets the conditions.
     *
     * @param stsResponse the sts response
     * @return the conditions
     */
    public static NodeList getConditions(Element stsResponse) {
        return stsResponse.getElementsByTagName(SAML_CONDITIONS);
    }

    /**
     * Gets the attributes.
     *
     * @param stsResponse the sts response
     * @return the attributes
     */
    public static NodeList getAttributes(Element stsResponse) {
        return ((Element) stsResponse.getElementsByTagName(SAML_ATTRIBUTESTATEMENT).item(0)).getElementsByTagName(SAML_ATTRIBUTE);
    }

    /**
     * Gets the assertion.
     *
     * @param stsResponse the sts response
     * @return the assertion
     */
    public static Element getAssertion(Element stsResponse) {
        return (Element) stsResponse.getElementsByTagName(SAML_ASSERTION).item(0);
    }

    /**
     * Gets the nihii.
     *
     * @param element the element
     * @return the nihii
     * @throws IntegrationModuleException
     */
    public static String getNihii(Element element) throws IntegrationModuleException {
        NodeList attributes = element.getElementsByTagName(SAML_ATTRIBUTE);
        if (attributes.getLength() == 0) {
            attributes = element.getElementsByTagNameNS(ASSERTION_URI, SAML_ATTRIBUTE);
            if (attributes.getLength() == 0) {
                return null;
            }
        }
        for (int i = 0; i < attributes.getLength(); i++) {
            Node node = attributes.item(i);
            String attributeName = node.getAttributes().getNamedItem(SAML_ATTRIBUTE_NAME).getTextContent();
            String attributeNamespace = node.getAttributes().getNamedItem(SAML_ATTRIBUTE_NAMESPACE).getTextContent();
            // Doctor
            if ((DOCTOR_NIHII_URI.equals(attributeName) || DOCTOR_NIHII_URI1.equals(attributeName)) && verifyNameSpace(attributeNamespace)) {
                return node.getTextContent().trim();
            }
            //Dentist
            if ((DENTIST_NIHII_URI.equals(attributeName) || DENTIST_NIHII_URI1.equals(attributeName)) && verifyNameSpace(attributeNamespace)) {
                return node.getTextContent().trim();
            }
            //Midwife
            if ((MIDWIFE_NIHII_URI.equals(attributeName) || MIDWIFE_NIHII_URI1.equals(attributeName)) && verifyNameSpace(attributeNamespace)) {
                return node.getTextContent().trim();
            }
            //PHYSIOTHERAPIST
            if ((PHYSIOTHERAPIST_NIHII_URI.equals(attributeName) || PHYSIOTHERAPIST_NIHII_URI1.equals(attributeName)) && verifyNameSpace(attributeNamespace)) {
                return node.getTextContent().trim();
            }
            //NURSE
            if ((NURSE_NIHII_URI.equals(attributeName) || NURSE_NIHII_URI1.equals(attributeName)) && verifyNameSpace(attributeNamespace)) {
                return node.getTextContent().trim();
            }
            // Pharmacist
            if ((PHARMACIST_NIHII_URI.equals(attributeName) || PHARMACIST_NIHII_URI1.equals(attributeName)) && verifyNameSpace(attributeNamespace)) {
                return (node.getTextContent().trim());
            }
        }
        throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.saml.nihii.not.found"));
    }

    private static boolean verifyNameSpace(String attributeNamespace) {
        return CERTIFIERD_NAME_SPACE.equals(attributeNamespace) ||  INDENTIFICATION_NAME_SPACE.equals(attributeNamespace);
    }

    public static String getType(Element element) throws IntegrationModuleException {
        NodeList attributes = element.getElementsByTagName(SAML_ATTRIBUTE);
        if (attributes.getLength() == 0) {
            attributes = element.getElementsByTagNameNS(
                    ASSERTION_URI, SAML_ATTRIBUTE);
            if (attributes.getLength() == 0) {
                return null;
            }
        }
        for (int i = 0; i < attributes.getLength(); i++) {
            Node node = attributes.item(i);
            String attributeName = node.getAttributes().getNamedItem(SAML_ATTRIBUTE_NAME).getTextContent();
            String attributeNamespace = node.getAttributes().getNamedItem(SAML_ATTRIBUTE_NAMESPACE).getTextContent();
			// Doctor
//			if( "urn:be:fgov:person:ssin:ehealth:1.0:doctor:nihii11".equals(attributeName) && 
//					"urn:be:fgov:certified-namespace:ehealth".equals(attributeNamespace)){
//				return PharmacyIdType.
//						
//						RequestorType.CBE.toString();
//			}else
            // Pharmacist
            if (PHARMACIST_NIHII_URI.equals(attributeName)
                    && INDENTIFICATION_NAME_SPACE.equals(attributeNamespace)) {
                return PharmacyIdType.NIHII.toString();
//						RequestorType.NIHIIPHARMACY.toString();
            }
        }
        throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.saml.type.not.found"));
    }

    /**
     * Gets the niss.
     *
     * @param element the element
     * @return the niss
     */
    public static String getNiss(Element element) {
        NodeList attributes = element.getElementsByTagName(SAML_ATTRIBUTE);
        if (attributes.getLength() == 0) {
            attributes = element.getElementsByTagNameNS(
                    ASSERTION_URI, SAML_ATTRIBUTE);
            if (attributes.getLength() == 0) {
                return null;
            }
        }
        for (int i = 0; i < attributes.getLength(); i++) {
            Node node = attributes.item(i);
            String attributeName = node.getAttributes().getNamedItem(SAML_ATTRIBUTE_NAME).getTextContent();
            String attributeNamespace = node.getAttributes().getNamedItem(SAML_ATTRIBUTE_NAMESPACE).getTextContent();
            if ("urn:be:fgov:person:ssin".equals(attributeName)
                    && INDENTIFICATION_NAME_SPACE.equals(attributeNamespace)) {
                return node.getTextContent().trim();
            }
        }
        return null;
    }

    /**
     * Convert.
     *
     * @param stsResponse the sts response
     * @return the element
     * @throws IntegrationModuleException the integration module exception
     */
    public static Element convert(Source stsResponse) throws IntegrationModuleException {
        try {
            StringWriter stringWriter = new StringWriter();
            Result result = new StreamResult(stringWriter);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(stsResponse, result);
            final String xmlResponse = stringWriter.getBuffer().toString();
            return SAML10Converter.toElement(xmlResponse);
        } catch (TransformerException e) {
            throw new IntegrationModuleException(e);
        }
    }
}
