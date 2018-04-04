//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2017.05.11 à 02:53:46 PM CEST 
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * to specify the value of the item
 * 
 * <p>Classe Java pour recipecontentType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="recipecontentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;choice>
 *             &lt;sequence>
 *               &lt;choice>
 *                 &lt;element name="medicinalproduct" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipemedicinalProductType"/>
 *                 &lt;element name="substanceproduct">
 *                   &lt;complexType>
 *                     &lt;complexContent>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                         &lt;sequence>
 *                           &lt;element name="intendedcd" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeCD-INNCLUSTER"/>
 *                           &lt;element name="intendedname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                         &lt;/sequence>
 *                       &lt;/restriction>
 *                     &lt;/complexContent>
 *                   &lt;/complexType>
 *                 &lt;/element>
 *                 &lt;element name="compoundprescription" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipecompoundprescriptionType"/>
 *               &lt;/choice>
 *             &lt;/sequence>
 *           &lt;/choice>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipecontentType", propOrder = {
    "compoundprescription",
    "substanceproduct",
    "medicinalproduct"
})
public class RecipecontentType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    protected RecipecompoundprescriptionType compoundprescription;
    protected RecipecontentType.Substanceproduct substanceproduct;
    protected RecipemedicinalProductType medicinalproduct;

    /**
     * Obtient la valeur de la propriété compoundprescription.
     * 
     * @return
     *     possible object is
     *     {@link RecipecompoundprescriptionType }
     *     
     */
    public RecipecompoundprescriptionType getCompoundprescription() {
        return compoundprescription;
    }

    /**
     * Définit la valeur de la propriété compoundprescription.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipecompoundprescriptionType }
     *     
     */
    public void setCompoundprescription(RecipecompoundprescriptionType value) {
        this.compoundprescription = value;
    }

    /**
     * Obtient la valeur de la propriété substanceproduct.
     * 
     * @return
     *     possible object is
     *     {@link RecipecontentType.Substanceproduct }
     *     
     */
    public RecipecontentType.Substanceproduct getSubstanceproduct() {
        return substanceproduct;
    }

    /**
     * Définit la valeur de la propriété substanceproduct.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipecontentType.Substanceproduct }
     *     
     */
    public void setSubstanceproduct(RecipecontentType.Substanceproduct value) {
        this.substanceproduct = value;
    }

    /**
     * Obtient la valeur de la propriété medicinalproduct.
     * 
     * @return
     *     possible object is
     *     {@link RecipemedicinalProductType }
     *     
     */
    public RecipemedicinalProductType getMedicinalproduct() {
        return medicinalproduct;
    }

    /**
     * Définit la valeur de la propriété medicinalproduct.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipemedicinalProductType }
     *     
     */
    public void setMedicinalproduct(RecipemedicinalProductType value) {
        this.medicinalproduct = value;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="intendedcd" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeCD-INNCLUSTER"/>
     *         &lt;element name="intendedname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "intendedcd",
        "intendedname"
    })
    public static class Substanceproduct
        implements Serializable
    {

        private final static long serialVersionUID = 20161201L;
        @XmlElement(required = true)
        protected RecipeCDINNCLUSTER intendedcd;
        @XmlElement(required = true)
        protected String intendedname;

        /**
         * Obtient la valeur de la propriété intendedcd.
         * 
         * @return
         *     possible object is
         *     {@link RecipeCDINNCLUSTER }
         *     
         */
        public RecipeCDINNCLUSTER getIntendedcd() {
            return intendedcd;
        }

        /**
         * Définit la valeur de la propriété intendedcd.
         * 
         * @param value
         *     allowed object is
         *     {@link RecipeCDINNCLUSTER }
         *     
         */
        public void setIntendedcd(RecipeCDINNCLUSTER value) {
            this.intendedcd = value;
        }

        /**
         * Obtient la valeur de la propriété intendedname.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIntendedname() {
            return intendedname;
        }

        /**
         * Définit la valeur de la propriété intendedname.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIntendedname(String value) {
            this.intendedname = value;
        }

    }

}
