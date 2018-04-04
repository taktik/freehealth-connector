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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * a transaction is a set of medical information validated by one healthcare professional at one given moment.
 * 
 * <p>Classe Java pour recipetransactionType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="recipetransactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipebasicID-KMEHR"/>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeCD-TRANSACTION"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="author" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeauthorType"/>
 *         &lt;element name="iscomplete" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isvalidated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;choice>
 *           &lt;element name="heading" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipetransactionheadingType"/>
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
@XmlType(name = "recipetransactionType", propOrder = {
    "id",
    "cd",
    "date",
    "time",
    "author",
    "iscomplete",
    "isvalidated",
    "heading"
})
public class RecipetransactionType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(required = true)
    protected RecipebasicIDKMEHR id;
    @XmlElement(required = true)
    protected RecipeCDTRANSACTION cd;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar time;
    @XmlElement(required = true)
    protected RecipeauthorType author;
    protected boolean iscomplete;
    protected boolean isvalidated;
    protected RecipetransactionheadingType heading;

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
     * Obtient la valeur de la propriété cd.
     * 
     * @return
     *     possible object is
     *     {@link RecipeCDTRANSACTION }
     *     
     */
    public RecipeCDTRANSACTION getCd() {
        return cd;
    }

    /**
     * Définit la valeur de la propriété cd.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipeCDTRANSACTION }
     *     
     */
    public void setCd(RecipeCDTRANSACTION value) {
        this.cd = value;
    }

    /**
     * Obtient la valeur de la propriété date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Obtient la valeur de la propriété time.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Définit la valeur de la propriété time.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Obtient la valeur de la propriété author.
     * 
     * @return
     *     possible object is
     *     {@link RecipeauthorType }
     *     
     */
    public RecipeauthorType getAuthor() {
        return author;
    }

    /**
     * Définit la valeur de la propriété author.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipeauthorType }
     *     
     */
    public void setAuthor(RecipeauthorType value) {
        this.author = value;
    }

    /**
     * Obtient la valeur de la propriété iscomplete.
     * 
     */
    public boolean isIscomplete() {
        return iscomplete;
    }

    /**
     * Définit la valeur de la propriété iscomplete.
     * 
     */
    public void setIscomplete(boolean value) {
        this.iscomplete = value;
    }

    /**
     * Obtient la valeur de la propriété isvalidated.
     * 
     */
    public boolean isIsvalidated() {
        return isvalidated;
    }

    /**
     * Définit la valeur de la propriété isvalidated.
     * 
     */
    public void setIsvalidated(boolean value) {
        this.isvalidated = value;
    }

    /**
     * Obtient la valeur de la propriété heading.
     * 
     * @return
     *     possible object is
     *     {@link RecipetransactionheadingType }
     *     
     */
    public RecipetransactionheadingType getHeading() {
        return heading;
    }

    /**
     * Définit la valeur de la propriété heading.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipetransactionheadingType }
     *     
     */
    public void setHeading(RecipetransactionheadingType value) {
        this.heading = value;
    }

}
