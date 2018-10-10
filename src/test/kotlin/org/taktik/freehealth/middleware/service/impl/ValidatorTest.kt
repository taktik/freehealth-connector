package org.taktik.freehealth.middleware.service.impl

import org.junit.Assert
import org.junit.Test
import javax.xml.validation.SchemaFactory

class CalculatorTest {
    @Test
    fun instanceOfValidator() {
        val s = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(this.javaClass.classLoader.getResource("ehealth-kmehr/XSD/recipe/recipe_PP_kmehr_elements-1_19.xsd"))
        Assert.assertNotNull("Not null", s)
    }
}
