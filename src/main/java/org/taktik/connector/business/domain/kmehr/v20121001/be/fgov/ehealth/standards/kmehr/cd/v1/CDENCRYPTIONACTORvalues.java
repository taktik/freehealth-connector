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
 * <p>Classe Java pour CD-ENCRYPTION-ACTORvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ENCRYPTION-ACTORvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NIHII"/>
 *     &lt;enumeration value="NIHII-HOSPITAL"/>
 *     &lt;enumeration value="NIHII-PHARMACY"/>
 *     &lt;enumeration value="CBE"/>
 *     &lt;enumeration value="INSS"/>
 *     &lt;enumeration value="EHP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-ENCRYPTION-ACTORvalues")
@XmlEnum
public enum CDENCRYPTIONACTORvalues {

    NIHII("NIHII"),
    @XmlEnumValue("NIHII-HOSPITAL")
    NIHII_HOSPITAL("NIHII-HOSPITAL"),
    @XmlEnumValue("NIHII-PHARMACY")
    NIHII_PHARMACY("NIHII-PHARMACY"),
    CBE("CBE"),
    INSS("INSS"),
    EHP("EHP");
    private final String value;

    CDENCRYPTIONACTORvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDENCRYPTIONACTORvalues fromValue(String v) {
        for (CDENCRYPTIONACTORvalues c: CDENCRYPTIONACTORvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
