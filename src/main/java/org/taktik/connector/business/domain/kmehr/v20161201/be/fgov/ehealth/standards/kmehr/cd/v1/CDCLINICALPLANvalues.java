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
 * <p>Classe Java pour CD-CLINICALPLANvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-CLINICALPLANvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="breastcancerprevention"/>
 *     &lt;enumeration value="cervixutericancer"/>
 *     &lt;enumeration value="colrectalcancerprevention"/>
 *     &lt;enumeration value="primaryprevention"/>
 *     &lt;enumeration value="prostatecancerprevention"/>
 *     &lt;enumeration value="gmdplus"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-CLINICALPLANvalues")
@XmlEnum
public enum CDCLINICALPLANvalues {

    @XmlEnumValue("breastcancerprevention")
    BREASTCANCERPREVENTION("breastcancerprevention"),
    @XmlEnumValue("cervixutericancer")
    CERVIXUTERICANCER("cervixutericancer"),
    @XmlEnumValue("colrectalcancerprevention")
    COLRECTALCANCERPREVENTION("colrectalcancerprevention"),
    @XmlEnumValue("primaryprevention")
    PRIMARYPREVENTION("primaryprevention"),
    @XmlEnumValue("prostatecancerprevention")
    PROSTATECANCERPREVENTION("prostatecancerprevention"),
    @XmlEnumValue("gmdplus")
    GMDPLUS("gmdplus");
    private final String value;

    CDCLINICALPLANvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDCLINICALPLANvalues fromValue(String v) {
        for (CDCLINICALPLANvalues c: CDCLINICALPLANvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
