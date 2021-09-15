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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * to transfer medical information about one or several patients (using one folder per patient).
 *
 * <p>Classe Java pour recipekmehrmessageType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="recipekmehrmessageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipeheaderType"/>
 *         &lt;element name="folder" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}recipefolderType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipekmehrmessageType", propOrder = {
    "header",
    "folder"
})
@XmlRootElement(name = "kmehrmessage")
public class RecipeKmehrmessageType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(required = true)
    protected RecipeheaderType header;
    @XmlElement(required = true)
    protected RecipefolderType folder;

    /**
     * Obtient la valeur de la propriété header.
     *
     * @return
     *     possible object is
     *     {@link RecipeheaderType }
     *
     */
    public RecipeheaderType getHeader() {
        return header;
    }

    /**
     * Définit la valeur de la propriété header.
     *
     * @param value
     *     allowed object is
     *     {@link RecipeheaderType }
     *
     */
    public void setHeader(RecipeheaderType value) {
        this.header = value;
    }

    /**
     * Obtient la valeur de la propriété folder.
     *
     * @return
     *     possible object is
     *     {@link RecipefolderType }
     *
     */
    public RecipefolderType getFolder() {
        return folder;
    }

    /**
     * Définit la valeur de la propriété folder.
     *
     * @param value
     *     allowed object is
     *     {@link RecipefolderType }
     *
     */
    public void setFolder(RecipefolderType value) {
        this.folder = value;
    }

}
