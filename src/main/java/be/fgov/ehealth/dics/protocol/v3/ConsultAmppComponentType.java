package be.fgov.ehealth.dics.protocol.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v3.actual.common.AmppComponentKeyType;
import java.io.Serializable;
import java.math.BigInteger;
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
   propOrder = {"contentType", "contentMultiplier", "packSpecification", "deviceType", "packagingClosures", "packagingMaterials", "packagingType", "amppComponentEquivalents"}
)
public class ConsultAmppComponentType extends AmppComponentKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ContentType",
      required = true
   )
   protected String contentType;
   @XmlElement(
      name = "ContentMultiplier"
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger contentMultiplier;
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
      name = "AmppComponentEquivalent",
      required = true
   )
   protected List<ConsultAmppComponentEquivalentType> amppComponentEquivalents;
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

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String value) {
      this.contentType = value;
   }

   public BigInteger getContentMultiplier() {
      return this.contentMultiplier;
   }

   public void setContentMultiplier(BigInteger value) {
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

   public List<ConsultAmppComponentEquivalentType> getAmppComponentEquivalents() {
      if (this.amppComponentEquivalents == null) {
         this.amppComponentEquivalents = new ArrayList();
      }

      return this.amppComponentEquivalents;
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
