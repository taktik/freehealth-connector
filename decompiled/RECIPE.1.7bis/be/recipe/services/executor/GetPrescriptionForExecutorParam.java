package be.recipe.services.executor;

import be.recipe.services.PartyIdentification;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/executor"
)
public class GetPrescriptionForExecutorParam extends PartyIdentification {
   @NotNull
   @Size(
      min = 12,
      max = 12
   )
   private String rid;
   private byte[] symmKey;
   private String version;

   @NotNull
   public String getExecutorId() {
      return super.getExecutorId();
   }

   public GetPrescriptionForExecutorParam() {
   }

   public GetPrescriptionForExecutorParam(String rid) {
      this.rid = rid;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String version) {
      this.version = version;
   }
}
