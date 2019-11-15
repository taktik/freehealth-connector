package org.taktik.freehealth.middleware.service.impl

import org.junit.Assert
import org.junit.Test
import org.taktik.freehealth.middleware.domain.FaultNoNsType
import java.io.ByteArrayInputStream
import javax.xml.bind.JAXBContext
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.validation.SchemaFactory


class ValidatorTest {
    @Test
    fun instanceOfValidator() {
        val s = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(this.javaClass.classLoader.getResource("ehealth-kmehr/XSD/recipe/recipe_PP_kmehr_elements-1_19.xsd"))
        Assert.assertNotNull("Not null", s)
    }

    @Test
    fun unmarshalFault() {
        val s= """
            <?xml version="1.0" encoding="UTF-8"?>
            <Fault xmlns:ns2="urn:oasis:names:tc:SAML:2.0:assertion" xmlns:ns3="http://www.w3.org/2000/09/xmldsig#" xmlns:ns4="urn:oasis:names:tc:SAML:2.0:protocol" xmlns:ns5="http://www.w3.org/2001/04/xmlenc#" xmlns:ns6="urn:be:cin:types:v1" xmlns:ns7="urn:be:cin:nippin:memberData:support:v2" xmlns:ns8="urn:be:cin:nippin:memberdata:saml:extension">
              <FaultCode>INPUT_ERROR</FaultCode>
              <FaultSource>IO-BE-500</FaultSource>
              <Message>Mismatched ContactType</Message>
              <Details>
                <Detail>
                  <DetailCode>MISMATCHED_CONTACTTYPE</DetailCode>
                  <DetailSource>Insurability</DetailSource>
                  <Location>*:AttributeQuery/*:Extensions/*:Facet[@id='urn:be:cin:nippin:insurability']/*:Dimension[@id='contactType']</Location>
                  <Message>The value of contactType is hospitalized and member is not hospitalized</Message>
                </Detail>
              </Details>
            </Fault>
        """.trimIndent()

        val factory = DocumentBuilderFactory.newInstance()
        factory.isNamespaceAware = true
        val builder = factory.newDocumentBuilder()

        val root = builder.parse(ByteArrayInputStream(s.toByteArray())).documentElement

        val jaxbContext = JAXBContext.newInstance(FaultNoNsType::class.java)

        val jaxbUnmarshaller = jaxbContext.createUnmarshaller()
        val fault = jaxbUnmarshaller.unmarshal(root) as FaultNoNsType

        Assert.assertNotNull("Not null", fault)
    }
}
