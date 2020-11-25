package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.model.v1.AbstractIncomingMedicationType;
import be.apb.standards.smoa.schema.model.v1.AbstractPharmacyType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegisterExportEventType",
   propOrder = {"abstractPharmacy", "beginDate", "endDate", "deliveries", "incoming"}
)
public class RegisterExportEventType extends AbstractEventType {
   @XmlElementRef(
      name = "abstract-Pharmacy",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractPharmacyType> abstractPharmacy;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar beginDate;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar endDate;
   protected DeliveriesType deliveries;
   protected RegisterExportEventType.Incoming incoming;

   public JAXBElement<? extends AbstractPharmacyType> getAbstractPharmacy() {
      return this.abstractPharmacy;
   }

   public void setAbstractPharmacy(JAXBElement<? extends AbstractPharmacyType> value) {
      this.abstractPharmacy = value;
   }

   public XMLGregorianCalendar getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(XMLGregorianCalendar value) {
      this.beginDate = value;
   }

   public XMLGregorianCalendar getEndDate() {
      return this.endDate;
   }

   public void setEndDate(XMLGregorianCalendar value) {
      this.endDate = value;
   }

   public DeliveriesType getDeliveries() {
      return this.deliveries;
   }

   public void setDeliveries(DeliveriesType value) {
      this.deliveries = value;
   }

   public RegisterExportEventType.Incoming getIncoming() {
      return this.incoming;
   }

   public void setIncoming(RegisterExportEventType.Incoming value) {
      this.incoming = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"abstractIncomingMedication"}
   )
   public static class Incoming {
      @XmlElementRef(
         name = "abstract-IncomingMedication",
         namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
         type = JAXBElement.class,
         required = false
      )
      protected List<JAXBElement<? extends AbstractIncomingMedicationType>> abstractIncomingMedication;

      public List<JAXBElement<? extends AbstractIncomingMedicationType>> getAbstractIncomingMedication() {
         if (this.abstractIncomingMedication == null) {
            this.abstractIncomingMedication = new ArrayList();
         }

         return this.abstractIncomingMedication;
      }
   }
}
