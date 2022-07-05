package be.fgov.ehealth.genericinsurability.core.v1;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
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
   name = "TransferType",
   propOrder = {"insuranceOrg", "transferDate"}
)
public class TransferType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InsuranceOrg"
   )
   protected String insuranceOrg;
   @XmlElement(
      name = "TransferDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime transferDate;
   @XmlAttribute(
      name = "Direction",
      required = true
   )
   protected TransferDirectionType direction;

   public TransferType() {
   }

   public String getInsuranceOrg() {
      return this.insuranceOrg;
   }

   public void setInsuranceOrg(String value) {
      this.insuranceOrg = value;
   }

   public DateTime getTransferDate() {
      return this.transferDate;
   }

   public void setTransferDate(DateTime value) {
      this.transferDate = value;
   }

   public TransferDirectionType getDirection() {
      return this.direction;
   }

   public void setDirection(TransferDirectionType value) {
      this.direction = value;
   }
}
