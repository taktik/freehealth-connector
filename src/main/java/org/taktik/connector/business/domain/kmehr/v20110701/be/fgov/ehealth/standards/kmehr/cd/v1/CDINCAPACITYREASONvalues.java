//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.03.05 à 11:47:59 AM CET 
//


package org.taktik.connector.business.domain.kmehr.v20110701.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-INCAPACITYREASONvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-INCAPACITYREASONvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="sickness"/>
 *     &lt;enumeration value="accident"/>
 *     &lt;enumeration value="family"/>
 *     &lt;enumeration value="other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-INCAPACITYREASONvalues")
@XmlEnum
public enum CDINCAPACITYREASONvalues {

    @XmlEnumValue("sickness")
    SICKNESS("sickness"),
    @XmlEnumValue("accident")
    ACCIDENT("accident"),
    @XmlEnumValue("family")
    FAMILY("family"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    CDINCAPACITYREASONvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDINCAPACITYREASONvalues fromValue(String v) {
        for (CDINCAPACITYREASONvalues c: CDINCAPACITYREASONvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
