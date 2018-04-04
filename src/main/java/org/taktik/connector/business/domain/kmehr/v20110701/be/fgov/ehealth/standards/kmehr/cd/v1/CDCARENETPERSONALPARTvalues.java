//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.03.05 à 11:47:59 AM CET 
//


package org.taktik.connector.business.domain.kmehr.v20110701.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-CARENET-PERSONAL-PARTvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-CARENET-PERSONAL-PARTvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="code1"/>
 *     &lt;enumeration value="code2"/>
 *     &lt;enumeration value="future"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-CARENET-PERSONAL-PARTvalues")
@XmlEnum
public enum CDCARENETPERSONALPARTvalues {

    @XmlEnumValue("code1")
    CODE_1("code1"),
    @XmlEnumValue("code2")
    CODE_2("code2"),
    @XmlEnumValue("future")
    FUTURE("future");
    private final String value;

    CDCARENETPERSONALPARTvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDCARENETPERSONALPARTvalues fromValue(String v) {
        for (CDCARENETPERSONALPARTvalues c: CDCARENETPERSONALPARTvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
