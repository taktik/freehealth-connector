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
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESSschemes;


/**
 * <p>Classe Java pour recipeCD-ADDRESSschemes.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeCD-ADDRESSschemes">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-ADDRESSschemes">
 *     &lt;enumeration value="CD-ADDRESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipeCD-ADDRESSschemes")
@XmlEnum(CDADDRESSschemes.class)
public enum RecipeCDADDRESSschemes {

    @XmlEnumValue("CD-ADDRESS")
    CD_ADDRESS(CDADDRESSschemes.CD_ADDRESS);
    private final CDADDRESSschemes value;

    RecipeCDADDRESSschemes(CDADDRESSschemes v) {
        value = v;
    }

    public CDADDRESSschemes value() {
        return value;
    }

    public static RecipeCDADDRESSschemes fromValue(CDADDRESSschemes v) {
        for (RecipeCDADDRESSschemes c: RecipeCDADDRESSschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
