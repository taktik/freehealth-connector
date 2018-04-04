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
 * <p>Classe Java pour CD-THERAPEUTICLINKvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-THERAPEUTICLINKvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="hospitalstay"/>
 *     &lt;enumeration value="hospitalambulatory"/>
 *     &lt;enumeration value="hospitalurgency"/>
 *     &lt;enumeration value="gpconsultation"/>
 *     &lt;enumeration value="specialistconsultation"/>
 *     &lt;enumeration value="gmd"/>
 *     &lt;enumeration value="carepath "/>
 *     &lt;enumeration value="medicalhouse"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-THERAPEUTICLINKvalues")
@XmlEnum
public enum CDTHERAPEUTICLINKvalues {

    @XmlEnumValue("hospitalstay")
    HOSPITALSTAY("hospitalstay"),
    @XmlEnumValue("hospitalambulatory")
    HOSPITALAMBULATORY("hospitalambulatory"),
    @XmlEnumValue("hospitalurgency")
    HOSPITALURGENCY("hospitalurgency"),
    @XmlEnumValue("gpconsultation")
    GPCONSULTATION("gpconsultation"),
    @XmlEnumValue("specialistconsultation")
    SPECIALISTCONSULTATION("specialistconsultation"),
    @XmlEnumValue("gmd")
    GMD("gmd"),
    @XmlEnumValue("carepath ")
    CAREPATH("carepath "),
    @XmlEnumValue("medicalhouse")
    MEDICALHOUSE("medicalhouse");
    private final String value;

    CDTHERAPEUTICLINKvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDTHERAPEUTICLINKvalues fromValue(String v) {
        for (CDTHERAPEUTICLINKvalues c: CDTHERAPEUTICLINKvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
