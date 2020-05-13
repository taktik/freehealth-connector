package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.code.v1.AbstractSpecialPrescriptionCodeType;
import be.apb.standards.smoa.schema.id.v1.AbstractPrescriptionIdType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SpecialPrescriptionType",
   propOrder = {"id", "abstractCareProvider", "type", "abstractAttest", "description"}
)
public class SpecialPrescriptionType extends AbstractSpecialPrescriptionType {
   @XmlElement(
      required = true
   )
   protected AbstractPrescriptionIdType id;
   @XmlElementRef(
      name = "abstract-CareProvider",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractCareProviderType> abstractCareProvider;
   @XmlElement(
      required = true
   )
   protected AbstractSpecialPrescriptionCodeType type;
   @XmlElementRef(
      name = "abstract-Attest",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractAttestType> abstractAttest;
   @XmlElement(
      required = true
   )
   protected String description;

   public AbstractPrescriptionIdType getId() {
      return this.id;
   }

   public void setId(AbstractPrescriptionIdType value) {
      this.id = value;
   }

   public JAXBElement<? extends AbstractCareProviderType> getAbstractCareProvider() {
      return this.abstractCareProvider;
   }

   public void setAbstractCareProvider(JAXBElement<? extends AbstractCareProviderType> value) {
      this.abstractCareProvider = value;
   }

   public AbstractSpecialPrescriptionCodeType getType() {
      return this.type;
   }

   public void setType(AbstractSpecialPrescriptionCodeType value) {
      this.type = value;
   }

   public JAXBElement<? extends AbstractAttestType> getAbstractAttest() {
      return this.abstractAttest;
   }

   public void setAbstractAttest(JAXBElement<? extends AbstractAttestType> value) {
      this.abstractAttest = value;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }
}
