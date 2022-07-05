package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.Signature;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"other", "rfc3161TimeStampToken", "signature"}
)
@XmlRootElement(
   name = "Timestamp"
)
public class Timestamp implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Other"
   )
   protected AnyType other;
   @XmlElement(
      name = "RFC3161TimeStampToken"
   )
   protected byte[] rfc3161TimeStampToken;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Signature signature;

   public Timestamp() {
   }

   public AnyType getOther() {
      return this.other;
   }

   public void setOther(AnyType value) {
      this.other = value;
   }

   public byte[] getRFC3161TimeStampToken() {
      return this.rfc3161TimeStampToken;
   }

   public void setRFC3161TimeStampToken(byte[] value) {
      this.rfc3161TimeStampToken = value;
   }

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }
}
