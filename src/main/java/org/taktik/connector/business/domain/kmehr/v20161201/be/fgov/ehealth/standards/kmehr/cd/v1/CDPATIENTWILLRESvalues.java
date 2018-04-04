//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2017.05.11 à 02:53:46 PM CEST 
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-PATIENTWILL-RESvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-PATIENTWILL-RESvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="dnr0"/>
 *     &lt;enumeration value="dnr1"/>
 *     &lt;enumeration value="dnr2"/>
 *     &lt;enumeration value="dnr3"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-PATIENTWILL-RESvalues")
@XmlEnum
public enum CDPATIENTWILLRESvalues {

    @XmlEnumValue("dnr0")
    DNR_0("dnr0"),
    @XmlEnumValue("dnr1")
    DNR_1("dnr1"),
    @XmlEnumValue("dnr2")
    DNR_2("dnr2"),
    @XmlEnumValue("dnr3")
    DNR_3("dnr3");
    private final String value;

    CDPATIENTWILLRESvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDPATIENTWILLRESvalues fromValue(String v) {
        for (CDPATIENTWILLRESvalues c: CDPATIENTWILLRESvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
