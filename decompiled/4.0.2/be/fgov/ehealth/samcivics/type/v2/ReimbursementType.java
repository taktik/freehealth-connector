package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReimbursementType",
   propOrder = {"deliveryEnvironment", "referenceBaseInd", "packageAgreement"}
)
@XmlSeeAlso({ReimbursementAndChildrenType.class})
public class ReimbursementType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DeliveryEnvironment",
      required = true
   )
   protected String deliveryEnvironment;
   @XmlElement(
      name = "ReferenceBaseInd"
   )
   protected Boolean referenceBaseInd;
   @XmlElement(
      name = "PackageAgreement"
   )
   protected String packageAgreement;

   public ReimbursementType() {
   }

   public String getDeliveryEnvironment() {
      return this.deliveryEnvironment;
   }

   public void setDeliveryEnvironment(String value) {
      this.deliveryEnvironment = value;
   }

   public Boolean isReferenceBaseInd() {
      return this.referenceBaseInd;
   }

   public void setReferenceBaseInd(Boolean value) {
      this.referenceBaseInd = value;
   }

   public String getPackageAgreement() {
      return this.packageAgreement;
   }

   public void setPackageAgreement(String value) {
      this.packageAgreement = value;
   }
}
