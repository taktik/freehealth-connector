package be.behealth.webservices.tsa;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.SignResponse;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TSBagType",
   propOrder = {"tsBagValue", "signResponse"}
)
public class TSBagType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TSBagValue",
      required = true
   )
   protected byte[] tsBagValue;
   @XmlElement(
      name = "SignResponse",
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      required = true
   )
   protected SignResponse signResponse;

   public byte[] getTSBagValue() {
      return this.tsBagValue;
   }

   public void setTSBagValue(byte[] value) {
      this.tsBagValue = value;
   }

   public SignResponse getSignResponse() {
      return this.signResponse;
   }

   public void setSignResponse(SignResponse value) {
      this.signResponse = value;
   }
}
