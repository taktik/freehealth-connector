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
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes;


/**
 * <p>Classe Java pour recipeID-KMEHRschemes.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeID-KMEHRschemes">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-KMEHRschemes">
 *     &lt;enumeration value="ID-KMEHR"/>
 *     &lt;enumeration value="LOCAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipeID-KMEHRschemes")
@XmlEnum(IDKMEHRschemes.class)
public enum RecipeIDKMEHRschemes {

    @XmlEnumValue("ID-KMEHR")
    ID_KMEHR(IDKMEHRschemes.ID_KMEHR),
    LOCAL(IDKMEHRschemes.LOCAL);
    private final IDKMEHRschemes value;

    RecipeIDKMEHRschemes(IDKMEHRschemes v) {
        value = v;
    }

    public IDKMEHRschemes value() {
        return value;
    }

    public static RecipeIDKMEHRschemes fromValue(IDKMEHRschemes v) {
        for (RecipeIDKMEHRschemes c: RecipeIDKMEHRschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
