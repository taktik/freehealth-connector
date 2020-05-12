package be.business.connector.recipe.patient.domain;

import be.recipe.services.core.PartyIdentification;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/patient"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetVisionParam"
)
public class GetVisionParam extends PartyIdentification {
   private static final long serialVersionUID = 1L;
   @NotNull
   private byte[] symmKey;
   @NotNull
   @Size(
      min = 12,
      max = 12
   )
   private String rid;

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }
}
