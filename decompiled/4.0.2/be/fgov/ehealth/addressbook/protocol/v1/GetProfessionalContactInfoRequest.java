package be.fgov.ehealth.addressbook.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetProfessionalContactInfoRequestType",
   propOrder = {"ssin", "nihii"}
)
@XmlRootElement(
   name = "GetProfessionalContactInfoRequest"
)
public class GetProfessionalContactInfoRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "NIHII"
   )
   protected String nihii;

   public GetProfessionalContactInfoRequest() {
   }

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public String getNIHII() {
      return this.nihii;
   }

   public void setNIHII(String value) {
      this.nihii = value;
   }
}
