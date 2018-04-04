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
 * <p>Classe Java pour CD-ENCOUNTERSAFETYISSUEvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ENCOUNTERSAFETYISSUEvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="verbal"/>
 *     &lt;enumeration value="fysical"/>
 *     &lt;enumeration value="material"/>
 *     &lt;enumeration value="notificationtopolice"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-ENCOUNTERSAFETYISSUEvalues")
@XmlEnum
public enum CDENCOUNTERSAFETYISSUEvalues {

    @XmlEnumValue("verbal")
    VERBAL("verbal"),
    @XmlEnumValue("fysical")
    FYSICAL("fysical"),
    @XmlEnumValue("material")
    MATERIAL("material"),
    @XmlEnumValue("notificationtopolice")
    NOTIFICATIONTOPOLICE("notificationtopolice");
    private final String value;

    CDENCOUNTERSAFETYISSUEvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDENCOUNTERSAFETYISSUEvalues fromValue(String v) {
        for (CDENCOUNTERSAFETYISSUEvalues c: CDENCOUNTERSAFETYISSUEvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
