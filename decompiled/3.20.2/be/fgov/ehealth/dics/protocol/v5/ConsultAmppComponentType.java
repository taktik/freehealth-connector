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
   name = "ConsultAmppComponentType",
   propOrder = {"ampcSequenceNr", "contentType", "contentMultiplier", "packSpecification", "deviceType", "packagingClosures", "packagingMaterials", "packagingType", "additionalFields", "amppComponentEquivalents"}
)
public class ConsultAmppComponentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AmpcSequenceNr"
   )
   protected Integer ampcSequenceNr;
   @XmlElement(
      name = "ContentType",
      required = true
   )
   protected String contentType;
   @XmlElement(
      name = "ContentMultiplier"
   )
   protected Integer contentMultiplier;
   @XmlElement(
      name = "PackSpecification"
   )
   protected String packSpecification;
   @XmlElement(
      name = "DeviceType"
   )
   protected DeviceTypeType deviceType;
   @XmlElement(
      name = "PackagingClosure"
   )
   protected List<PackagingClosureType> packagingClosures;
   @XmlElement(
      name = "PackagingMaterial"
   )
   protected List<PackagingMaterialType> packagingMaterials;
   @XmlElement(
      name = "PackagingType",
      required = true
   )
   protected PackagingTypeType packagingType;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlElement(
      name = "AmppComponentEquivalent",
      required = true
   )
   protected List<ConsultAmppComponentEquivalentType> amppComponentEquivalents;
   @XmlAttribute(
      name = "SequenceNr",
      required = true
   )
   protected int sequenceNr;
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

   public Integer getAmpcSequenceNr() {
      return this.ampcSequenceNr;
   }

   public void setAmpcSequenceNr(Integer value) {
      this.ampcSequenceNr = value;
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String value) {
      this.contentType = value;
   }

   public Integer getContentMultiplier() {
      return this.contentMultiplier;
   }

   public void setContentMultiplier(Integer value) {
      this.contentMultiplier = value;
   }

   public String getPackSpecification() {
      return this.packSpecification;
   }

   public void setPackSpecification(String value) {
      this.packSpecification = value;
   }

   public DeviceTypeType getDeviceType() {
      return this.deviceType;
   }

   public void setDeviceType(DeviceTypeType value) {
      this.deviceType = value;
   }

   public List<PackagingClosureType> getPackagingClosures() {
      if (this.packagingClosures == null) {
         this.packagingClosures = new ArrayList();
      }

      return this.packagingClosures;
   }

   public List<PackagingMaterialType> getPackagingMaterials() {
      if (this.packagingMaterials == null) {
         this.packagingMaterials = new ArrayList();
      }

      return this.packagingMaterials;
   }

   public PackagingTypeType getPackagingType() {
      return this.packagingType;
   }

   public void setPackagingType(PackagingTypeType value) {
      this.packagingType = value;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public List<ConsultAmppComponentEquivalentType> getAmppComponentEquivalents() {
      if (this.amppComponentEquivalents == null) {
         this.amppComponentEquivalents = new ArrayList();
      }

      return this.amppComponentEquivalents;
   }

   public int getSequenceNr() {
      return this.sequenceNr;
   }

   public void setSequenceNr(int value) {
      this.sequenceNr = value;
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
