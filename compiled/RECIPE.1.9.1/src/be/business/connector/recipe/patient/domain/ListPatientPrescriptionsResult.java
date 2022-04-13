package be.business.connector.recipe.patient.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;
import be.recipe.services.patient.GetOpenPrescriptionForPatient;

/**
 * The Class ListAddressedPrescriptionResult.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListPatientPrescriptionsResult")
public class ListPatientPrescriptionsResult extends ResponseType {

	protected List<GetOpenPrescriptionForPatient> prescriptions;
	protected Boolean hasMoreResults;

	public List<GetOpenPrescriptionForPatient> getPrescriptions() {
		if (prescriptions == null) {
			prescriptions = new ArrayList<GetOpenPrescriptionForPatient>();
		}
		return this.prescriptions;
	}

	/**
	 * Gets the value of the hasMoreResults property.
	 *
	 * @return
	 *     possible object is
	 *     {@link Boolean }
	 *
	 */
	public Boolean isHasMoreResults() {
		return hasMoreResults;
	}

	/**
	 * Sets the value of the hasMoreResults property.
	 *
	 * @param value
	 *     allowed object is
	 *     {@link Boolean }
	 *
	 */
	public void setHasMoreResults(Boolean value) {
		this.hasMoreResults = value;
	}

	public Boolean getHasMoreResults() {
		return this.hasMoreResults;
	}


}
