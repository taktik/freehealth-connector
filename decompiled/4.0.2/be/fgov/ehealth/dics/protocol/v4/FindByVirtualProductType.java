package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindByVirtualProductType",
   propOrder = {"anyNamePart", "vmpCode"}
)
public class FindByVirtualProductType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AnyNamePart"
   )
   protected String anyNamePart;
   @XmlElement(
      name = "VmpCode"
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger vmpCode;

   public FindByVirtualProductType() {
   }

   public String getAnyNamePart() {
      return this.anyNamePart;
   }

   public void setAnyNamePart(String value) {
      this.anyNamePart = value;
   }

   public BigInteger getVmpCode() {
      return this.vmpCode;
   }

   public void setVmpCode(BigInteger value) {
      this.vmpCode = value;
   }
}
