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
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes;


/**
 * <p>Classe Java pour recipeID-HCPARTYschemes.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeID-HCPARTYschemes">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-HCPARTYschemes">
 *     &lt;enumeration value="ID-HCPARTY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipeID-HCPARTYschemes")
@XmlEnum(IDHCPARTYschemes.class)
public enum RecipeIDHCPARTYschemes {

    @XmlEnumValue("ID-HCPARTY")
    ID_HCPARTY(IDHCPARTYschemes.ID_HCPARTY);
    private final IDHCPARTYschemes value;

    RecipeIDHCPARTYschemes(IDHCPARTYschemes v) {
        value = v;
    }

    public IDHCPARTYschemes value() {
        return value;
    }

    public static RecipeIDHCPARTYschemes fromValue(IDHCPARTYschemes v) {
        for (RecipeIDHCPARTYschemes c: RecipeIDHCPARTYschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
