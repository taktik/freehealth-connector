package be.apb.gfddpp.rtrn.registerdata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "",
		propOrder = {"pharmaceuticalCareEventType", "archivePrescriptionEventType", "archivePrescriptionCommentEventType", "bvacEventType", "continuedPharmaceuticalCareDossierEvent"}
)
@XmlRootElement(
		name = "events"
)
public class Events {
	protected PharmaceuticalCareEventType pharmaceuticalCareEventType;
	protected ArchivePrescriptionEventType archivePrescriptionEventType;
	protected ArchivePrescriptionCommentEventType archivePrescriptionCommentEventType;
	protected BvacEventType bvacEventType;
	protected ContinuedPharmaceuticalCareDossierEvent continuedPharmaceuticalCareDossierEvent;

	public PharmaceuticalCareEventType getPharmaceuticalCareEventType() {
		return this.pharmaceuticalCareEventType;
	}

	public void setPharmaceuticalCareEventType(PharmaceuticalCareEventType value) {
		this.pharmaceuticalCareEventType = value;
	}

	public ArchivePrescriptionEventType getArchivePrescriptionEventType() {
		return this.archivePrescriptionEventType;
	}

	public void setArchivePrescriptionEventType(ArchivePrescriptionEventType value) {
		this.archivePrescriptionEventType = value;
	}

	public ArchivePrescriptionCommentEventType getArchivePrescriptionCommentEventType() {
		return this.archivePrescriptionCommentEventType;
	}

	public void setArchivePrescriptionCommentEventType(ArchivePrescriptionCommentEventType value) {
		this.archivePrescriptionCommentEventType = value;
	}

	public BvacEventType getBvacEventType() {
		return this.bvacEventType;
	}

	public void setBvacEventType(BvacEventType value) {
		this.bvacEventType = value;
	}

	public ContinuedPharmaceuticalCareDossierEvent getContinuedPharmaceuticalCareDossierEvent() {
		return this.continuedPharmaceuticalCareDossierEvent;
	}

	public void setContinuedPharmaceuticalCareDossierEvent(ContinuedPharmaceuticalCareDossierEvent value) {
		this.continuedPharmaceuticalCareDossierEvent = value;
	}
}
