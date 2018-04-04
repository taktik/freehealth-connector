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
 * <p>Classe Java pour CD-ADDRESSvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ADDRESSvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="home"/>
 *     &lt;enumeration value="other"/>
 *     &lt;enumeration value="vacation"/>
 *     &lt;enumeration value="work"/>
 *     &lt;enumeration value="careaddress"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-ADDRESSvalues")
@XmlEnum
public enum CDADDRESSvalues {

    @XmlEnumValue("home")
    HOME("home"),
    @XmlEnumValue("other")
    OTHER("other"),
    @XmlEnumValue("vacation")
    VACATION("vacation"),
    @XmlEnumValue("work")
    WORK("work"),
    @XmlEnumValue("careaddress")
    CAREADDRESS("careaddress");
    private final String value;

    CDADDRESSvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDADDRESSvalues fromValue(String v) {
        for (CDADDRESSvalues c: CDADDRESSvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
