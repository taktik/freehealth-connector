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
 * <p>Classe Java pour CD-ENCRYPTION-METHODschemes.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ENCRYPTION-METHODschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-ENCRYPTION-METHOD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-ENCRYPTION-METHODschemes")
@XmlEnum
public enum CDENCRYPTIONMETHODschemes {

    @XmlEnumValue("CD-ENCRYPTION-METHOD")
    CD_ENCRYPTION_METHOD("CD-ENCRYPTION-METHOD");
    private final String value;

    CDENCRYPTIONMETHODschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDENCRYPTIONMETHODschemes fromValue(String v) {
        for (CDENCRYPTIONMETHODschemes c: CDENCRYPTIONMETHODschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
