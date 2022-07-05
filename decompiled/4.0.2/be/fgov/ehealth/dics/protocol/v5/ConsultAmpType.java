package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultAmpType",
   propOrder = {"name", "blackTriangle", "medicineType", "officialName", "abbreviatedName", "proprietarySuffix", "prescriptionName", "companyActorNr", "additionalFields", "ampComponents", "ampps"}
)
public class ConsultAmpType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "BlackTriangle"
   )
   protected boolean blackTriangle;
   @XmlElement(
      name = "MedicineType",
      required = true
   )
   protected String medicineType;
   @XmlElement(
      name = "OfficialName"
   )
   protected String officialName;
   @XmlElement(
      name = "AbbreviatedName"
   )
   protected ConsultTextType abbreviatedName;
   @XmlElement(
      name = "ProprietarySuffix"
   )
   protected ConsultTextType proprietarySuffix;
   @XmlElement(
      name = "PrescriptionName"
   )
   protected ConsultTextType prescriptionName;
   @XmlElement(
      name = "CompanyActorNr",
      required = true
   )
   protected String companyActorNr;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlElement(
      name = "AmpComponent",
      required = true
   )
   protected List<ConsultAmpComponentType> ampComponents;
   @XmlElement(
      name = "Ampp"
   )
   protected List<ConsultAmppType> ampps;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlAttribute(
      name = "VmpCode"
   )
   protected Integer vmpCode;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public ConsultAmpType() {
   }

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public boolean isBlackTriangle() {
      return this.blackTriangle;
   }

   public void setBlackTriangle(boolean value) {
      this.blackTriangle = value;
   }

   public String getMedicineType() {
      return this.medicineType;
   }

   public void setMedicineType(String value) {
      this.medicineType = value;
   }

   public String getOfficialName() {
      return this.officialName;
   }

   public void setOfficialName(String value) {
      this.officialName = value;
   }

   public ConsultTextType getAbbreviatedName() {
      return this.abbreviatedName;
   }

   public void setAbbreviatedName(ConsultTextType value) {
      this.abbreviatedName = value;
   }

   public ConsultTextType getProprietarySuffix() {
      return this.proprietarySuffix;
   }

   public void setProprietarySuffix(ConsultTextType value) {
      this.proprietarySuffix = value;
   }

   public ConsultTextType getPrescriptionName() {
      return this.prescriptionName;
   }

   public void setPrescriptionName(ConsultTextType value) {
      this.prescriptionName = value;
   }

   public String getCompanyActorNr() {
      return this.companyActorNr;
   }

   public void setCompanyActorNr(String value) {
      this.companyActorNr = value;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public List<ConsultAmpComponentType> getAmpComponents() {
      if (this.ampComponents == null) {
         this.ampComponents = new ArrayList();
      }

      return this.ampComponents;
   }

   public List<ConsultAmppType> getAmpps() {
      if (this.ampps == null) {
         this.ampps = new ArrayList();
      }

      return this.ampps;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public Integer getVmpCode() {
      return this.vmpCode;
   }

   public void setVmpCode(Integer value) {
      this.vmpCode = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
