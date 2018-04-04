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
 * <p>Classe Java pour CD-PROOFvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-PROOFvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="eidreading"/>
 *     &lt;enumeration value="sisreading"/>
 *     &lt;enumeration value="eidsigning"/>
 *     &lt;enumeration value="authenticsource"/>
 *     &lt;enumeration value="patientmanagement"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-PROOFvalues")
@XmlEnum
public enum CDPROOFvalues {

    @XmlEnumValue("eidreading")
    EIDREADING("eidreading"),
    @XmlEnumValue("sisreading")
    SISREADING("sisreading"),
    @XmlEnumValue("eidsigning")
    EIDSIGNING("eidsigning"),
    @XmlEnumValue("authenticsource")
    AUTHENTICSOURCE("authenticsource"),
    @XmlEnumValue("patientmanagement")
    PATIENTMANAGEMENT("patientmanagement");
    private final String value;

    CDPROOFvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDPROOFvalues fromValue(String v) {
        for (CDPROOFvalues c: CDPROOFvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
