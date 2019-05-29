package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"signatureObject"}
)
@XmlRootElement(
   name = "SignResponse"
)
public class SignResponse extends ResponseBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignatureObject"
   )
   protected SignatureObject signatureObject;

   public SignatureObject getSignatureObject() {
      return this.signatureObject;
   }

   public void setSignatureObject(SignatureObject value) {
      this.signatureObject = value;
   }
}
