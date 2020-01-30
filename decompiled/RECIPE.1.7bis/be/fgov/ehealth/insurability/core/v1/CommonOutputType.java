package be.fgov.ehealth.insurability.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CommonOutputType",
   propOrder = {"reference", "ioReference"}
)
public class CommonOutputType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Reference"
   )
   protected String reference;
   @XmlElement(
      name = "IoReference"
   )
   protected String ioReference;

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }

   public String getIoReference() {
      return this.ioReference;
   }

   public void setIoReference(String value) {
      this.ioReference = value;
   }
}
