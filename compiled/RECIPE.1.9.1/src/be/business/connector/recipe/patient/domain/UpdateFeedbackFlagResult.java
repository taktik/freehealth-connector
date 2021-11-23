package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;

@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateFeedbackFlagResult")
public class UpdateFeedbackFlagResult extends ResponseType {

}