package be.business.connector.recipe.executor.domain;

import be.business.connector.recipe.domain.Page;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http://services.recipe.be/executor"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetAllRidInProcessParam"
)
public class GetAllRidInProcessParam {
   private byte[] symmKey;
   @NotNull
   @Valid
   private Page page;

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }
}
