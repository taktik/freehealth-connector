package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class ListPrescriptionHistory.
 *
 * @author bruno.casneuf
 */
@XmlRootElement(namespace = "http:/services.recipe.be/patient")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListPrescriptionHistory")
public class ListPrescriptionHistory {

	/** The get prescription status sealed. */
	@XmlElement(name = "ListPrescriptionHistoryParamSealed")
	protected byte[] listPrescriptionHistoryParamSealed;

	public ListPrescriptionHistory() {
		super();
	}

	public byte[] getListPrescriptionHistoryParamSealed() {
		return listPrescriptionHistoryParamSealed;
	}

	public void setListPrescriptionHistoryParamSealed(byte[] listPrescriptionHistoryParamSealed) {
		this.listPrescriptionHistoryParamSealed = listPrescriptionHistoryParamSealed;
	}
}