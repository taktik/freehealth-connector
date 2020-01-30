package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractRawMaterialIdType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RawMaterialType",
   propOrder = {"id", "name", "firm", "analyticalReference", "packaging", "authorizationNumber"}
)
public class RawMaterialType extends AbstractRawMaterialType {
   @XmlElement(
      required = true
   )
   protected AbstractRawMaterialIdType id;
   @XmlElement(
      required = true
   )
   protected String name;
   @XmlElement(
      required = true
   )
   protected String firm;
   @XmlElement(
      required = true
   )
   protected String analyticalReference;
   @XmlElement(
      required = true
   )
   protected String packaging;
   @XmlElement(
      required = true
   )
   protected String authorizationNumber;

   public AbstractRawMaterialIdType getId() {
      return this.id;
   }

   public void setId(AbstractRawMaterialIdType value) {
      this.id = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getFirm() {
      return this.firm;
   }

   public void setFirm(String value) {
      this.firm = value;
   }

   public String getAnalyticalReference() {
      return this.analyticalReference;
   }

   public void setAnalyticalReference(String value) {
      this.analyticalReference = value;
   }

   public String getPackaging() {
      return this.packaging;
   }

   public void setPackaging(String value) {
      this.packaging = value;
   }

   public String getAuthorizationNumber() {
      return this.authorizationNumber;
   }

   public void setAuthorizationNumber(String value) {
      this.authorizationNumber = value;
   }
}
