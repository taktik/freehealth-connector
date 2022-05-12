package be.fgov.ehealth.dics.core.v3.virtual.common;

import be.fgov.ehealth.dics.protocol.v3.ConsultVmpGroupType;
import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VmpGroupKeyType"
)
@XmlSeeAlso({VmpGroupType.class, ConsultVmpGroupType.class})
public class VmpGroupKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger code;

   public VmpGroupKeyType() {
   }

   public BigInteger getCode() {
      return this.code;
   }

   public void setCode(BigInteger value) {
      this.code = value;
   }
}
