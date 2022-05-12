package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = ""
)
@XmlRootElement(
   name = "ReturnTransformedDocument"
)
public class ReturnTransformedDocument implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "WhichReference",
      required = true
   )
   protected BigInteger whichReference;

   public ReturnTransformedDocument() {
   }

   public BigInteger getWhichReference() {
      return this.whichReference;
   }

   public void setWhichReference(BigInteger value) {
      this.whichReference = value;
   }
}
