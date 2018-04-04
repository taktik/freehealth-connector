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
 * <p>Classe Java pour recipecompoundType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="recipecompoundType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipenumericID-KMEHR"/>
 *         &lt;choice>
 *           &lt;element name="medicinalproduct" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipemedicinalProductType"/>
 *           &lt;element name="substance" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipesubstanceType"/>
 *         &lt;/choice>
 *         &lt;element name="quantityprefix" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeCD-QUANTITYPREFIX"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="quantity" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipequantityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipecompoundType", propOrder = {
    "id",
    "substance",
    "medicinalproduct",
    "quantityprefix",
    "quantity"
})
public class RecipecompoundType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(required = true)
    protected RecipenumericIDKMEHR id;
    protected RecipesubstanceType substance;
    protected RecipemedicinalProductType medicinalproduct;
    protected RecipecompoundType.Quantityprefix quantityprefix;
    protected RecipequantityType quantity;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link RecipenumericIDKMEHR }
     *     
     */
    public RecipenumericIDKMEHR getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipenumericIDKMEHR }
     *     
     */
    public void setId(RecipenumericIDKMEHR value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété substance.
     * 
     * @return
     *     possible object is
     *     {@link RecipesubstanceType }
     *     
     */
    public RecipesubstanceType getSubstance() {
        return substance;
    }

    /**
     * Définit la valeur de la propriété substance.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipesubstanceType }
     *     
     */
    public void setSubstance(RecipesubstanceType value) {
        this.substance = value;
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
     * Obtient la valeur de la propriété quantityprefix.
     * 
     * @return
     *     possible object is
     *     {@link RecipecompoundType.Quantityprefix }
     *     
     */
    public RecipecompoundType.Quantityprefix getQuantityprefix() {
        return quantityprefix;
    }

    /**
     * Définit la valeur de la propriété quantityprefix.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipecompoundType.Quantityprefix }
     *     
     */
    public void setQuantityprefix(RecipecompoundType.Quantityprefix value) {
        this.quantityprefix = value;
    }

    /**
     * Obtient la valeur de la propriété quantity.
     * 
     * @return
     *     possible object is
     *     {@link RecipequantityType }
     *     
     */
    public RecipequantityType getQuantity() {
        return quantity;
    }

    /**
     * Définit la valeur de la propriété quantity.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipequantityType }
     *     
     */
    public void setQuantity(RecipequantityType value) {
        this.quantity = value;
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
     *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeCD-QUANTITYPREFIX"/>
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
        "cd"
    })
    public static class Quantityprefix
        implements Serializable
    {

        private final static long serialVersionUID = 20161201L;
        @XmlElement(required = true)
        protected RecipeCDQUANTITYPREFIX cd;

        /**
         * Obtient la valeur de la propriété cd.
         * 
         * @return
         *     possible object is
         *     {@link RecipeCDQUANTITYPREFIX }
         *     
         */
        public RecipeCDQUANTITYPREFIX getCd() {
            return cd;
        }

        /**
         * Définit la valeur de la propriété cd.
         * 
         * @param value
         *     allowed object is
         *     {@link RecipeCDQUANTITYPREFIX }
         *     
         */
        public void setCd(RecipeCDQUANTITYPREFIX value) {
            this.cd = value;
        }

    }

}
