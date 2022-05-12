package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.Transforms;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"transforms", "base64Data"}
)
@XmlRootElement(
   name = "TransformedData"
)
public class TransformedData extends DocumentBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Transforms",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Transforms transforms;
   @XmlElement(
      name = "Base64Data",
      required = true
   )
   protected Base64Data base64Data;
   @XmlAttribute(
      name = "WhichReference"
   )
   protected BigInteger whichReference;

   public TransformedData() {
   }

   public Transforms getTransforms() {
      return this.transforms;
   }

   public void setTransforms(Transforms value) {
      this.transforms = value;
   }

   public Base64Data getBase64Data() {
      return this.base64Data;
   }

   public void setBase64Data(Base64Data value) {
      this.base64Data = value;
   }

   public BigInteger getWhichReference() {
      return this.whichReference;
   }

   public void setWhichReference(BigInteger value) {
      this.whichReference = value;
   }
}
