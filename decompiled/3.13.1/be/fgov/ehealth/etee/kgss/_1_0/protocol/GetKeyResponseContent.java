package be.fgov.ehealth.etee.kgss._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"key"}
)
@XmlRootElement(
   name = "GetKeyResponseContent"
)
public class GetKeyResponseContent implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Key"
   )
   protected byte[] key;

   public byte[] getKey() {
      return this.key;
   }

   public void setKey(byte[] value) {
      this.key = value;
   }
}
