package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.DigestMethod;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DigestAlgAndValueType",
   propOrder = {"digestMethod", "digestValue"}
)
public class DigestAlgAndValueType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DigestMethod",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      required = true
   )
   protected DigestMethod digestMethod;
   @XmlElement(
      name = "DigestValue",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      required = true
   )
   protected byte[] digestValue;

   public DigestMethod getDigestMethod() {
      return this.digestMethod;
   }

   public void setDigestMethod(DigestMethod value) {
      this.digestMethod = value;
   }

   public byte[] getDigestValue() {
      return this.digestValue;
   }

   public void setDigestValue(byte[] value) {
      this.digestValue = value;
   }
}
