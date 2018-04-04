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
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDDAYPERIODvalues;


/**
 * <p>Classe Java pour recipeCD-DAYPERIODvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeCD-DAYPERIODvalues">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-DAYPERIODvalues">
 *     &lt;enumeration value="afterbreakfast"/>
 *     &lt;enumeration value="afterdinner"/>
 *     &lt;enumeration value="afterlunch"/>
 *     &lt;enumeration value="beforebreakfast"/>
 *     &lt;enumeration value="beforedinner"/>
 *     &lt;enumeration value="beforelunch"/>
 *     &lt;enumeration value="betweenbreakfastandlunch"/>
 *     &lt;enumeration value="betweendinnerandsleep"/>
 *     &lt;enumeration value="betweenlunchanddinner"/>
 *     &lt;enumeration value="morning"/>
 *     &lt;enumeration value="thehourofsleep"/>
 *     &lt;enumeration value="duringbreakfast"/>
 *     &lt;enumeration value="duringlunch"/>
 *     &lt;enumeration value="duringdinner"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipeCD-DAYPERIODvalues")
@XmlEnum(CDDAYPERIODvalues.class)
public enum RecipeCDDAYPERIODvalues {

    @XmlEnumValue("afterbreakfast")
    AFTERBREAKFAST(CDDAYPERIODvalues.AFTERBREAKFAST),
    @XmlEnumValue("afterdinner")
    AFTERDINNER(CDDAYPERIODvalues.AFTERDINNER),
    @XmlEnumValue("afterlunch")
    AFTERLUNCH(CDDAYPERIODvalues.AFTERLUNCH),
    @XmlEnumValue("beforebreakfast")
    BEFOREBREAKFAST(CDDAYPERIODvalues.BEFOREBREAKFAST),
    @XmlEnumValue("beforedinner")
    BEFOREDINNER(CDDAYPERIODvalues.BEFOREDINNER),
    @XmlEnumValue("beforelunch")
    BEFORELUNCH(CDDAYPERIODvalues.BEFORELUNCH),
    @XmlEnumValue("betweenbreakfastandlunch")
    BETWEENBREAKFASTANDLUNCH(CDDAYPERIODvalues.BETWEENBREAKFASTANDLUNCH),
    @XmlEnumValue("betweendinnerandsleep")
    BETWEENDINNERANDSLEEP(CDDAYPERIODvalues.BETWEENDINNERANDSLEEP),
    @XmlEnumValue("betweenlunchanddinner")
    BETWEENLUNCHANDDINNER(CDDAYPERIODvalues.BETWEENLUNCHANDDINNER),
    @XmlEnumValue("morning")
    MORNING(CDDAYPERIODvalues.MORNING),
    @XmlEnumValue("thehourofsleep")
    THEHOUROFSLEEP(CDDAYPERIODvalues.THEHOUROFSLEEP),
    @XmlEnumValue("duringbreakfast")
    DURINGBREAKFAST(CDDAYPERIODvalues.DURINGBREAKFAST),
    @XmlEnumValue("duringlunch")
    DURINGLUNCH(CDDAYPERIODvalues.DURINGLUNCH),
    @XmlEnumValue("duringdinner")
    DURINGDINNER(CDDAYPERIODvalues.DURINGDINNER);
    private final CDDAYPERIODvalues value;

    RecipeCDDAYPERIODvalues(CDDAYPERIODvalues v) {
        value = v;
    }

    public CDDAYPERIODvalues value() {
        return value;
    }

    public static RecipeCDDAYPERIODvalues fromValue(CDDAYPERIODvalues v) {
        for (RecipeCDDAYPERIODvalues c: RecipeCDDAYPERIODvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
