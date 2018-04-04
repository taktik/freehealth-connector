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
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes;


/**
 * <p>Classe Java pour recipeID-PATIENTschemes.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeID-PATIENTschemes">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-PATIENTschemes">
 *     &lt;enumeration value="ID-PATIENT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipeID-PATIENTschemes")
@XmlEnum(IDPATIENTschemes.class)
public enum RecipeIDPATIENTschemes {

    @XmlEnumValue("ID-PATIENT")
    ID_PATIENT(IDPATIENTschemes.ID_PATIENT);
    private final IDPATIENTschemes value;

    RecipeIDPATIENTschemes(IDPATIENTschemes v) {
        value = v;
    }

    public IDPATIENTschemes value() {
        return value;
    }

    public static RecipeIDPATIENTschemes fromValue(IDPATIENTschemes v) {
        for (RecipeIDPATIENTschemes c: RecipeIDPATIENTschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
