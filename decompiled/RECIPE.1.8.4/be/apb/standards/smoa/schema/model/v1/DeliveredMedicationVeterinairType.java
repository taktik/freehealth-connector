package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeliveredMedicationVeterinairType",
   propOrder = {"dguid", "deliveryDate", "quantity", "lotNumber", "uniqueBarcode", "abstractCareProvider", "abstractMedicationConsumer", "abstractMedicinalProduct", "magistral", "abstractAttest", "narcotic", "psychotrope", "hypnotic", "abstractVeterinaryPrescription", "authorizedForOtherSpecies", "authorizedForOtherCondition"}
)
public class DeliveredMedicationVeterinairType extends AbstractDeliveredMedicationVeterinairType {
   @XmlElement(
      name = "dGUID",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String dguid;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar deliveryDate;
   protected int quantity;
   protected String lotNumber;
   protected Integer uniqueBarcode;
   @XmlElementRef(
      name = "abstract-CareProvider",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractCareProviderType> abstractCareProvider;
   @XmlElementRef(
      name = "abstract-MedicationConsumer",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractMedicationConsumerType> abstractMedicationConsumer;
   @XmlElementRef(
      name = "abstract-MedicinalProduct",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class,
      required = false
   )
   protected JAXBElement<? extends AbstractMedicinalProductType> abstractMedicinalProduct;
   protected MagistralType magistral;
   @XmlElementRef(
      name = "abstract-Attest",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class,
      required = false
   )
   protected JAXBElement<? extends AbstractAttestType> abstractAttest;
   protected Boolean narcotic;
   protected Boolean psychotrope;
   protected Boolean hypnotic;
   @XmlElementRef(
      name = "abstract-VeterinaryPrescription",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractVeterinaryPrescriptionType> abstractVeterinaryPrescription;
   protected boolean authorizedForOtherSpecies;
   protected boolean authorizedForOtherCondition;

   public String getDGUID() {
      return this.dguid;
   }

   public void setDGUID(String value) {
      this.dguid = value;
   }

   public XMLGregorianCalendar getDeliveryDate() {
      return this.deliveryDate;
   }

   public void setDeliveryDate(XMLGregorianCalendar value) {
      this.deliveryDate = value;
   }

   public int getQuantity() {
      return this.quantity;
   }

   public void setQuantity(int value) {
      this.quantity = value;
   }

   public String getLotNumber() {
      return this.lotNumber;
   }

   public void setLotNumber(String value) {
      this.lotNumber = value;
   }

   public Integer getUniqueBarcode() {
      return this.uniqueBarcode;
   }

   public void setUniqueBarcode(Integer value) {
      this.uniqueBarcode = value;
   }

   public JAXBElement<? extends AbstractCareProviderType> getAbstractCareProvider() {
      return this.abstractCareProvider;
   }

   public void setAbstractCareProvider(JAXBElement<? extends AbstractCareProviderType> value) {
      this.abstractCareProvider = value;
   }

   public JAXBElement<? extends AbstractMedicationConsumerType> getAbstractMedicationConsumer() {
      return this.abstractMedicationConsumer;
   }

   public void setAbstractMedicationConsumer(JAXBElement<? extends AbstractMedicationConsumerType> value) {
      this.abstractMedicationConsumer = value;
   }

   public JAXBElement<? extends AbstractMedicinalProductType> getAbstractMedicinalProduct() {
      return this.abstractMedicinalProduct;
   }

   public void setAbstractMedicinalProduct(JAXBElement<? extends AbstractMedicinalProductType> value) {
      this.abstractMedicinalProduct = value;
   }

   public MagistralType getMagistral() {
      return this.magistral;
   }

   public void setMagistral(MagistralType value) {
      this.magistral = value;
   }

   public JAXBElement<? extends AbstractAttestType> getAbstractAttest() {
      return this.abstractAttest;
   }

   public void setAbstractAttest(JAXBElement<? extends AbstractAttestType> value) {
      this.abstractAttest = value;
   }

   public Boolean isNarcotic() {
      return this.narcotic;
   }

   public void setNarcotic(Boolean value) {
      this.narcotic = value;
   }

   public Boolean isPsychotrope() {
      return this.psychotrope;
   }

   public void setPsychotrope(Boolean value) {
      this.psychotrope = value;
   }

   public Boolean isHypnotic() {
      return this.hypnotic;
   }

   public void setHypnotic(Boolean value) {
      this.hypnotic = value;
   }

   public JAXBElement<? extends AbstractVeterinaryPrescriptionType> getAbstractVeterinaryPrescription() {
      return this.abstractVeterinaryPrescription;
   }

   public void setAbstractVeterinaryPrescription(JAXBElement<? extends AbstractVeterinaryPrescriptionType> value) {
      this.abstractVeterinaryPrescription = value;
   }

   public boolean isAuthorizedForOtherSpecies() {
      return this.authorizedForOtherSpecies;
   }

   public void setAuthorizedForOtherSpecies(boolean value) {
      this.authorizedForOtherSpecies = value;
   }

   public boolean isAuthorizedForOtherCondition() {
      return this.authorizedForOtherCondition;
   }

   public void setAuthorizedForOtherCondition(boolean value) {
      this.authorizedForOtherCondition = value;
   }
}
