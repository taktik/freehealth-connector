package be.fgov.ehealth.samcivics.type.v2;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AmppType",
   propOrder = {"amppId", "name", "contentQuantity", "treatmentDurationCatCv", "contentMultiplier", "totalPackSizeValue", "socsecReimbCv", "distributorId", "initDate", "closeDate", "cheapest", "inSupply", "availability"}
)
public class AmppType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AmppId"
   )
   protected long amppId;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlElement(
      name = "ContentQuantity",
      required = true
   )
   protected ContentQuantityType contentQuantity;
   @XmlElement(
      name = "TreatmentDurationCatCv",
      required = true
   )
   protected String treatmentDurationCatCv;
   @XmlElement(
      name = "ContentMultiplier",
      required = true
   )
   protected BigInteger contentMultiplier;
   @XmlElement(
      name = "TotalPackSizeValue"
   )
   protected float totalPackSizeValue;
   @XmlElement(
      name = "SocsecReimbCv"
   )
   protected String socsecReimbCv;
   @XmlElement(
      name = "DistributorId"
   )
   protected Long distributorId;
   @XmlElement(
      name = "InitDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime initDate;
   @XmlElement(
      name = "CloseDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime closeDate;
   @XmlElement(
      name = "Cheapest"
   )
   protected String cheapest;
   @XmlElement(
      name = "InSupply",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inSupply;
   @XmlElement(
      name = "Availability",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime availability;

   public long getAmppId() {
      return this.amppId;
   }

   public void setAmppId(long value) {
      this.amppId = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public ContentQuantityType getContentQuantity() {
      return this.contentQuantity;
   }

   public void setContentQuantity(ContentQuantityType value) {
      this.contentQuantity = value;
   }

   public String getTreatmentDurationCatCv() {
      return this.treatmentDurationCatCv;
   }

   public void setTreatmentDurationCatCv(String value) {
      this.treatmentDurationCatCv = value;
   }

   public BigInteger getContentMultiplier() {
      return this.contentMultiplier;
   }

   public void setContentMultiplier(BigInteger value) {
      this.contentMultiplier = value;
   }

   public float getTotalPackSizeValue() {
      return this.totalPackSizeValue;
   }

   public void setTotalPackSizeValue(float value) {
      this.totalPackSizeValue = value;
   }

   public String getSocsecReimbCv() {
      return this.socsecReimbCv;
   }

   public void setSocsecReimbCv(String value) {
      this.socsecReimbCv = value;
   }

   public Long getDistributorId() {
      return this.distributorId;
   }

   public void setDistributorId(Long value) {
      this.distributorId = value;
   }

   public DateTime getInitDate() {
      return this.initDate;
   }

   public void setInitDate(DateTime value) {
      this.initDate = value;
   }

   public DateTime getCloseDate() {
      return this.closeDate;
   }

   public void setCloseDate(DateTime value) {
      this.closeDate = value;
   }

   public String getCheapest() {
      return this.cheapest;
   }

   public void setCheapest(String value) {
      this.cheapest = value;
   }

   public DateTime getInSupply() {
      return this.inSupply;
   }

   public void setInSupply(DateTime value) {
      this.inSupply = value;
   }

   public DateTime getAvailability() {
      return this.availability;
   }

   public void setAvailability(DateTime value) {
      this.availability = value;
   }
}
