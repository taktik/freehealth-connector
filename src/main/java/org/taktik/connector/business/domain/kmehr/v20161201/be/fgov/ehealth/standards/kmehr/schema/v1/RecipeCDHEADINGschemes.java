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
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHEADINGschemes;


/**
 * <p>Classe Java pour recipeCD-HEADINGschemes.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeCD-HEADINGschemes">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-HEADINGschemes">
 *     &lt;enumeration value="CD-HEADING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipeCD-HEADINGschemes")
@XmlEnum(CDHEADINGschemes.class)
public enum RecipeCDHEADINGschemes {

    @XmlEnumValue("CD-HEADING")
    CD_HEADING(CDHEADINGschemes.CD_HEADING);
    private final CDHEADINGschemes value;

    RecipeCDHEADINGschemes(CDHEADINGschemes v) {
        value = v;
    }

    public CDHEADINGschemes value() {
        return value;
    }

    public static RecipeCDHEADINGschemes fromValue(CDHEADINGschemes v) {
        for (RecipeCDHEADINGschemes c: RecipeCDHEADINGschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
