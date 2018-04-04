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
 * contains the clinical information related to one patient
 * 
 * <p>Classe Java pour recipefolderType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="recipefolderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipebasicID-KMEHR"/>
 *         &lt;element name="patient" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipepatientpersonType"/>
 *         &lt;element name="transaction" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipetransactionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipefolderType", propOrder = {
    "id",
    "patient",
    "transaction"
})
public class RecipefolderType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(required = true)
    protected RecipebasicIDKMEHR id;
    @XmlElement(required = true)
    protected RecipepatientpersonType patient;
    @XmlElement(required = true)
    protected RecipetransactionType transaction;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link RecipebasicIDKMEHR }
     *     
     */
    public RecipebasicIDKMEHR getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipebasicIDKMEHR }
     *     
     */
    public void setId(RecipebasicIDKMEHR value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété patient.
     * 
     * @return
     *     possible object is
     *     {@link RecipepatientpersonType }
     *     
     */
    public RecipepatientpersonType getPatient() {
        return patient;
    }

    /**
     * Définit la valeur de la propriété patient.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipepatientpersonType }
     *     
     */
    public void setPatient(RecipepatientpersonType value) {
        this.patient = value;
    }

    /**
     * Obtient la valeur de la propriété transaction.
     * 
     * @return
     *     possible object is
     *     {@link RecipetransactionType }
     *     
     */
    public RecipetransactionType getTransaction() {
        return transaction;
    }

    /**
     * Définit la valeur de la propriété transaction.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipetransactionType }
     *     
     */
    public void setTransaction(RecipetransactionType value) {
        this.transaction = value;
    }

}
