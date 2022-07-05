package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.DigestMethod;
import org.w3._2000._09.xmldsig.Transforms;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"transforms", "digestMethod", "digestValue"}
)
@XmlRootElement(
   name = "DocumentHash"
)
public class DocumentHash extends DocumentBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Transforms",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Transforms transforms;
   @XmlElement(
      name = "DigestMethod",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected DigestMethod digestMethod;
   @XmlElement(
      name = "DigestValue",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      required = true
   )
   protected byte[] digestValue;
   @XmlAttribute(
      name = "WhichReference"
   )
   protected BigInteger whichReference;

   public DocumentHash() {
   }

   public Transforms getTransforms() {
      return this.transforms;
   }

   public void setTransforms(Transforms value) {
      this.transforms = value;
   }

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

   public BigInteger getWhichReference() {
      return this.whichReference;
   }

   public void setWhichReference(BigInteger value) {
      this.whichReference = value;
   }
}
