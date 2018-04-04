package org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificationType", propOrder = {"text", "kmehrmessage"})
@XmlRootElement(name = "notification")
public class RecipeNotification {
	@XmlSchemaType(name = "text")
	String text;
	@XmlSchemaType(name = "kmehrmessage")
	Kmehrmessage kmehrmessage;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Kmehrmessage getKmehrmessage() {
		return kmehrmessage;
	}

	public void setKmehrmessage(Kmehrmessage kmehrmessage) {
		this.kmehrmessage = kmehrmessage;
	}
}
