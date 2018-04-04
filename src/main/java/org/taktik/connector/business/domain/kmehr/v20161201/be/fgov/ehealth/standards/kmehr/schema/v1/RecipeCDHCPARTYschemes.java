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
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes;


/**
 * <p>Classe Java pour recipeCD-HCPARTYschemes.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeCD-HCPARTYschemes">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-HCPARTYschemes">
 *     &lt;enumeration value="CD-HCPARTY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipeCD-HCPARTYschemes")
@XmlEnum(CDHCPARTYschemes.class)
public enum RecipeCDHCPARTYschemes {

    @XmlEnumValue("CD-HCPARTY")
    CD_HCPARTY(CDHCPARTYschemes.CD_HCPARTY);
    private final CDHCPARTYschemes value;

    RecipeCDHCPARTYschemes(CDHCPARTYschemes v) {
        value = v;
    }

    public CDHCPARTYschemes value() {
        return value;
    }

    public static RecipeCDHCPARTYschemes fromValue(CDHCPARTYschemes v) {
        for (RecipeCDHCPARTYschemes c: RecipeCDHCPARTYschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
