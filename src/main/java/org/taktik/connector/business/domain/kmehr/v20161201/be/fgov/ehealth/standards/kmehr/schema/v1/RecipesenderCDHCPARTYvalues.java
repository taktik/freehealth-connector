//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2017.05.11 à 02:53:46 PM CEST 
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYvalues;


/**
 * <p>Classe Java pour recipesenderCD-HCPARTYvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipesenderCD-HCPARTYvalues">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-HCPARTYvalues">
 *     &lt;enumeration value="orghospital"/>
 *     &lt;enumeration value="persdentist"/>
 *     &lt;enumeration value="persphysician"/>
 *     &lt;enumeration value="persmidwife"/>
 *     &lt;enumeration value="application"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipesenderCD-HCPARTYvalues")
@XmlEnum(CDHCPARTYvalues.class)
public enum RecipesenderCDHCPARTYvalues {

    @XmlEnumValue("orghospital")
    ORGHOSPITAL(CDHCPARTYvalues.ORGHOSPITAL),
    @XmlEnumValue("persdentist")
    PERSDENTIST(CDHCPARTYvalues.PERSDENTIST),
    @XmlEnumValue("persphysician")
    PERSPHYSICIAN(CDHCPARTYvalues.PERSPHYSICIAN),
    @XmlEnumValue("persmidwife")
    PERSMIDWIFE(CDHCPARTYvalues.PERSMIDWIFE),
    @XmlEnumValue("application")
    APPLICATION(CDHCPARTYvalues.APPLICATION);
    private final CDHCPARTYvalues value;

    RecipesenderCDHCPARTYvalues(CDHCPARTYvalues v) {
        value = v;
    }

    public CDHCPARTYvalues value() {
        return value;
    }

    public static RecipesenderCDHCPARTYvalues fromValue(CDHCPARTYvalues v) {
        for (RecipesenderCDHCPARTYvalues c: RecipesenderCDHCPARTYvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
