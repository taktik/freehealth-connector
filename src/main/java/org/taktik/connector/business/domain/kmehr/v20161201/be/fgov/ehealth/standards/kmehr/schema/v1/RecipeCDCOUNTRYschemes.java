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
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRYschemes;


/**
 * <p>Classe Java pour recipeCD-COUNTRYschemes.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeCD-COUNTRYschemes">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-COUNTRYschemes">
 *     &lt;enumeration value="CD-FED-COUNTRY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipeCD-COUNTRYschemes")
@XmlEnum(CDCOUNTRYschemes.class)
public enum RecipeCDCOUNTRYschemes {

    @XmlEnumValue("CD-FED-COUNTRY")
    CD_FED_COUNTRY(CDCOUNTRYschemes.CD_FED_COUNTRY);
    private final CDCOUNTRYschemes value;

    RecipeCDCOUNTRYschemes(CDCOUNTRYschemes v) {
        value = v;
    }

    public CDCOUNTRYschemes value() {
        return value;
    }

    public static RecipeCDCOUNTRYschemes fromValue(CDCOUNTRYschemes v) {
        for (RecipeCDCOUNTRYschemes c: RecipeCDCOUNTRYschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
