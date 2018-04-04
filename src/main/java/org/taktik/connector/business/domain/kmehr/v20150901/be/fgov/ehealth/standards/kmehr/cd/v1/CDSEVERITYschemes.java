//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.11.10 à 11:53:46 AM CET 
//


package org.taktik.connector.business.domain.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-SEVERITYschemes.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-SEVERITYschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-SEVERITY"/>
 *     &lt;enumeration value="CD-NYHA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-SEVERITYschemes")
@XmlEnum
public enum CDSEVERITYschemes {

    @XmlEnumValue("CD-SEVERITY")
    CD_SEVERITY("CD-SEVERITY"),
    @XmlEnumValue("CD-NYHA")
    CD_NYHA("CD-NYHA");
    private final String value;

    CDSEVERITYschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDSEVERITYschemes fromValue(String v) {
        for (CDSEVERITYschemes c: CDSEVERITYschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
