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
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTEMPORALITYvalues;


/**
 * <p>Classe Java pour recipeCD-TEMPORALITYvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeCD-TEMPORALITYvalues">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-TEMPORALITYvalues">
 *     &lt;enumeration value="oneshot"/>
 *     &lt;enumeration value="acute"/>
 *     &lt;enumeration value="chronic"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipeCD-TEMPORALITYvalues")
@XmlEnum(CDTEMPORALITYvalues.class)
public enum RecipeCDTEMPORALITYvalues {

    @XmlEnumValue("oneshot")
    ONESHOT(CDTEMPORALITYvalues.ONESHOT),
    @XmlEnumValue("acute")
    ACUTE(CDTEMPORALITYvalues.ACUTE),
    @XmlEnumValue("chronic")
    CHRONIC(CDTEMPORALITYvalues.CHRONIC);
    private final CDTEMPORALITYvalues value;

    RecipeCDTEMPORALITYvalues(CDTEMPORALITYvalues v) {
        value = v;
    }

    public CDTEMPORALITYvalues value() {
        return value;
    }

    public static RecipeCDTEMPORALITYvalues fromValue(CDTEMPORALITYvalues v) {
        for (RecipeCDTEMPORALITYvalues c: RecipeCDTEMPORALITYvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
