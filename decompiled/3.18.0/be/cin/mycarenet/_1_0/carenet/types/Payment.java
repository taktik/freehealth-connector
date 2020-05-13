package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PaymentType",
   propOrder = {"paymentByIo", "maxInvoiceds", "specialSocialCategory"}
)
@XmlRootElement(
   name = "Payment"
)
public class Payment implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PaymentByIo"
   )
   protected boolean paymentByIo;
   @XmlElement(
      name = "MaxInvoiced"
   )
   @XmlSchemaType(
      name = "gYear"
   )
   protected List<XMLGregorianCalendar> maxInvoiceds;
   @XmlElement(
      name = "SpecialSocialCategory"
   )
   protected Boolean specialSocialCategory;

   public boolean isPaymentByIo() {
      return this.paymentByIo;
   }

   public void setPaymentByIo(boolean value) {
      this.paymentByIo = value;
   }

   public List<XMLGregorianCalendar> getMaxInvoiceds() {
      if (this.maxInvoiceds == null) {
         this.maxInvoiceds = new ArrayList();
      }

      return this.maxInvoiceds;
   }

   public Boolean isSpecialSocialCategory() {
      return this.specialSocialCategory;
   }

   public void setSpecialSocialCategory(Boolean value) {
      this.specialSocialCategory = value;
   }
}
