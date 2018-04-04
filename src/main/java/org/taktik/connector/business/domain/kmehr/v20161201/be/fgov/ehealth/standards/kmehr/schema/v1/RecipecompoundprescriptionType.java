//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2017.05.11 à 02:53:46 PM CEST 
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;


/**
 * 
 *         a magistral preparation can be prescribed as a (coded)
 *         reference to a preparation in a reference book (formularyreference), or as a
 *         (coded) list of individual compounds (compoundlist), or as free text
 *         (magistraltext)
 *       
 * 
 * <p>Classe Java pour recipecompoundprescriptionType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="recipecompoundprescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="compound" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipecompoundType" maxOccurs="unbounded"/>
 *           &lt;element name="formularyreference" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeformularyreferenceType"/>
 *           &lt;element name="magistraltext" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType"/>
 *         &lt;/choice>
 *         &lt;element name="galenicform" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipegalenicformType" minOccurs="0"/>
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
@XmlType(name = "recipecompoundprescriptionType", propOrder = {
    "content"
})
public class RecipecompoundprescriptionType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElementRefs({
        @XmlElementRef(name = "quantity", namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "magistraltext", namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "compound", namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "formularyreference", namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "galenicform", namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Serializable> content;

    /**
     * 
     *         a magistral preparation can be prescribed as a (coded)
     *         reference to a preparation in a reference book (formularyreference), or as a
     *         (coded) list of individual compounds (compoundlist), or as free text
     *         (magistraltext)
     *       Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link RecipecompoundType }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link RecipequantityType }{@code >}
     * {@link JAXBElement }{@code <}{@link RecipeformularyreferenceType }{@code >}
     * {@link JAXBElement }{@code <}{@link RecipegalenicformType }{@code >}
     * {@link JAXBElement }{@code <}{@link TextType }{@code >}
     * 
     * 
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }

}
