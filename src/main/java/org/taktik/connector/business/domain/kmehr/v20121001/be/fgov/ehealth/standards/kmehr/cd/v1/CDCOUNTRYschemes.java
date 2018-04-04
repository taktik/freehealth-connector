//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.03.05 à 11:48:06 AM CET 
//


package org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-COUNTRYschemes.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-COUNTRYschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-COUNTRY"/>
 *     &lt;enumeration value="CD-FED-COUNTRY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-COUNTRYschemes")
@XmlEnum
public enum CDCOUNTRYschemes {

    @XmlEnumValue("CD-COUNTRY")
    CD_COUNTRY("CD-COUNTRY"),
    @XmlEnumValue("CD-FED-COUNTRY")
    CD_FED_COUNTRY("CD-FED-COUNTRY");
    private final String value;

    CDCOUNTRYschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDCOUNTRYschemes fromValue(String v) {
        for (CDCOUNTRYschemes c: CDCOUNTRYschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
