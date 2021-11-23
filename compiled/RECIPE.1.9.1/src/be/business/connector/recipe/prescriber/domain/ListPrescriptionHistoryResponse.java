package be.business.connector.recipe.prescriber.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class ListPrescriptionHistoryResponse.
 *
 * @author bruno.casneuf
 */
@XmlRootElement(namespace = "http:/services.recipe.be/prescriber")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListPrescriptionHistoryResponse")
public class ListPrescriptionHistoryResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private final static long serialVersionUID = 1L;

	/** The get prescription status result sealed. */
	@XmlElement(name = "ListPrescriptionHistoryResultSealed")
	protected byte[] listPrescriptionHistoryResultSealed;

	/**
	 * Instantiates a new list prescription history response.
	 */
	public ListPrescriptionHistoryResponse() {
		super();
	}

	public ListPrescriptionHistoryResponse(byte[] listPrescriptionHistoryResultSealed) {
		this.listPrescriptionHistoryResultSealed = listPrescriptionHistoryResultSealed;
	}

	public byte[] getListPrescriptionHistoryResultSealed() {
		return listPrescriptionHistoryResultSealed;
	}

	public void setListPrescriptionHistoryResultSealed(byte[] listPrescriptionHistoryResultSealed) {
		this.listPrescriptionHistoryResultSealed = listPrescriptionHistoryResultSealed;
	}
}