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

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.xml.wss.saml.Assertion;

/**
 * The Class SAML10Converter.
 */
public class SAML10Converter {

	/** The Constant OASIS_NAMESPACE. */
	public static final String OASIS_NAMESPACE = "oasis.names.tc.saml._1_0.protocol:oasis.names.tc.saml._1_0.assertion";
	
	
	/**
	 * To W3C Element
	 * 
	 * @param assertionType the assertion type
	 * @return the element
	 */
	@Deprecated
	public static Element toElement(Assertion assertionType) {
		return toElement(toXMLString(assertionType));
	}
	
	/**
	 * To xml string.
	 * 
	 * @param assertionType the assertion type
	 * @return the string
	 */
	@Deprecated
	public static String toXMLString(Assertion assertion) {
		try {
//			JAXBContext context = JAXBContext.newInstance(OASIS_NAMESPACE);
			JAXBContext context = JAXBContext.newInstance(Assertion.class);
			StringWriter writer = new StringWriter();			
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(assertion,writer);
			
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * To assertion type.
	 * 
	 * @param assertion the assertion
	 * @return the assertion type
	 * @throws IntegrationModuleException the integration module exception
	 */
	@Deprecated
	public static Assertion toAssertion(String assertion) throws IntegrationModuleException {
		try {
			JAXBContext context = JAXBContext.newInstance(OASIS_NAMESPACE);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Assertion) unmarshaller.unmarshal(new StringReader(assertion));
		} catch (Exception e) {
			throw new IntegrationModuleException(e);
		}
	}
	
	/**
	 * To element.
	 * 
	 * @param assertion the assertion
	 * @return the element
	 */
	public static Element toElement(String assertion) {
		try {
			DOMParser parser = new DOMParser();
			InputSource source = new InputSource(new StringReader(assertion));
			parser.parse(source);
            return parser.getDocument().getDocumentElement();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * To xml string.
	 * 
	 * @param element the element
	 * @return the string
	 */
	public static String toXMLString(Element element) {
		try {
			Source source = new DOMSource(element);
	        StringWriter stringWriter = new StringWriter();
	        Result result = new StreamResult(stringWriter);
	        TransformerFactory factory = TransformerFactory.newInstance();
	        Transformer transformer = factory.newTransformer();
	        transformer.transform(source, result);
			return stringWriter.getBuffer().toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
