package org.taktik.connector.business.domain.kmehr.v20161201.be.ehealth.logic.recipe.xsd.v20160906;

import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeKmehrmessageType;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificationType", propOrder = {"text", "kmehrmessage"})
@XmlRootElement(name = "notification")
public class RecipeNotification {
    @XmlSchemaType(name = "text")
    String text;
    @XmlSchemaType(name = "kmehrmessage")
    RecipeKmehrmessageType kmehrmessage;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RecipeKmehrmessageType getKmehrmessage() {
        return kmehrmessage;
    }

    public void setKmehrmessage(RecipeKmehrmessageType kmehrmessage) {
        this.kmehrmessage = kmehrmessage;
    }
}
