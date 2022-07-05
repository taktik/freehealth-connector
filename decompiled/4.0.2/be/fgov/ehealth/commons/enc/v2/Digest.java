package be.fgov.ehealth.commons.enc.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DigestType"
)
@XmlRootElement(
   name = "Digest"
)
public class Digest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "algorithm",
      required = true
   )
   protected String algorithm;

   public Digest() {
   }

   public String getAlgorithm() {
      return this.algorithm;
   }

   public void setAlgorithm(String value) {
      this.algorithm = value;
   }
}
