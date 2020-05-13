package be.fgov.ehealth.commons.enc.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CipherDataType",
   propOrder = {"cipherReference", "cipherValue"}
)
@XmlRootElement(
   name = "CipherData"
)
public class CipherData implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CipherReference"
   )
   protected CipherReference cipherReference;
   @XmlElement(
      name = "CipherValue"
   )
   protected CipherValue cipherValue;

   public CipherReference getCipherReference() {
      return this.cipherReference;
   }

   public void setCipherReference(CipherReference value) {
      this.cipherReference = value;
   }

   public CipherValue getCipherValue() {
      return this.cipherValue;
   }

   public void setCipherValue(CipherValue value) {
      this.cipherValue = value;
   }
}
