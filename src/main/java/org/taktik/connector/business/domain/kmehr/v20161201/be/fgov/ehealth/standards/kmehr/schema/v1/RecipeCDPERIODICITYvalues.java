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


/**
 * <p>Classe Java pour recipeCD-PERIODICITYvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeCD-PERIODICITYvalues">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-PERIODICITYvalues">
 *     &lt;enumeration value="UH"/>
 *     &lt;enumeration value="U"/>
 *     &lt;enumeration value="UT"/>
 *     &lt;enumeration value="UD"/>
 *     &lt;enumeration value="UV"/>
 *     &lt;enumeration value="UZ"/>
 *     &lt;enumeration value="UA"/>
 *     &lt;enumeration value="UW"/>
 *     &lt;enumeration value="D"/>
 *     &lt;enumeration value="DT"/>
 *     &lt;enumeration value="O1"/>
 *     &lt;enumeration value="DD"/>
 *     &lt;enumeration value="DV"/>
 *     &lt;enumeration value="DQ"/>
 *     &lt;enumeration value="DZ"/>
 *     &lt;enumeration value="W"/>
 *     &lt;enumeration value="DA"/>
 *     &lt;enumeration value="DN"/>
 *     &lt;enumeration value="DX"/>
 *     &lt;enumeration value="DE"/>
 *     &lt;enumeration value="DW"/>
 *     &lt;enumeration value="WT"/>
 *     &lt;enumeration value="WD"/>
 *     &lt;enumeration value="WV"/>
 *     &lt;enumeration value="M"/>
 *     &lt;enumeration value="WQ"/>
 *     &lt;enumeration value="WZ"/>
 *     &lt;enumeration value="WS"/>
 *     &lt;enumeration value="WA"/>
 *     &lt;enumeration value="MT"/>
 *     &lt;enumeration value="WN"/>
 *     &lt;enumeration value="WX"/>
 *     &lt;enumeration value="WE"/>
 *     &lt;enumeration value="WW"/>
 *     &lt;enumeration value="MD"/>
 *     &lt;enumeration value="MV"/>
 *     &lt;enumeration value="MQ"/>
 *     &lt;enumeration value="WP"/>
 *     &lt;enumeration value="JH2"/>
 *     &lt;enumeration value="MZ2"/>
 *     &lt;enumeration value="MS"/>
 *     &lt;enumeration value="MA"/>
 *     &lt;enumeration value="MN"/>
 *     &lt;enumeration value="MX"/>
 *     &lt;enumeration value="ME"/>
 *     &lt;enumeration value="J"/>
 *     &lt;enumeration value="MC"/>
 *     &lt;enumeration value="JT"/>
 *     &lt;enumeration value="JD"/>
 *     &lt;enumeration value="JV"/>
 *     &lt;enumeration value="JQ"/>
 *     &lt;enumeration value="JZ"/>
 *     &lt;enumeration value="ondemand"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recipeCD-PERIODICITYvalues")
@XmlEnum
public enum RecipeCDPERIODICITYvalues {

    UH("UH"),
    U("U"),
    UT("UT"),
    UD("UD"),
    UV("UV"),
    UZ("UZ"),
    UA("UA"),
    UW("UW"),
    D("D"),
    DT("DT"),
    @XmlEnumValue("O1")
    O_1("O1"),
    DD("DD"),
    DV("DV"),
    DQ("DQ"),
    DZ("DZ"),
    W("W"),
    DA("DA"),
    DN("DN"),
    DX("DX"),
    DE("DE"),
    DW("DW"),
    WT("WT"),
    WD("WD"),
    WV("WV"),
    M("M"),
    WQ("WQ"),
    WZ("WZ"),
    WS("WS"),
    WA("WA"),
    MT("MT"),
    WN("WN"),
    WX("WX"),
    WE("WE"),
    WW("WW"),
    MD("MD"),
    MV("MV"),
    MQ("MQ"),
    WP("WP"),
    @XmlEnumValue("JH2")
    JH_2("JH2"),
    @XmlEnumValue("MZ2")
    MZ_2("MZ2"),
    MS("MS"),
    MA("MA"),
    MN("MN"),
    MX("MX"),
    ME("ME"),
    J("J"),
    MC("MC"),
    JT("JT"),
    JD("JD"),
    JV("JV"),
    JQ("JQ"),
    JZ("JZ"),
    @XmlEnumValue("ondemand")
    ONDEMAND("ondemand");
    private final String value;

    RecipeCDPERIODICITYvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RecipeCDPERIODICITYvalues fromValue(String v) {
        for (RecipeCDPERIODICITYvalues c: RecipeCDPERIODICITYvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
