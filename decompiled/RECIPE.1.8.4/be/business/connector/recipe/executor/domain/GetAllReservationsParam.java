package be.business.connector.recipe.executor.domain;

import be.business.connector.recipe.domain.Page;
import java.util.Date;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetAllReservationsParam"
)
@XmlRootElement(
   namespace = "http://services.recipe.be"
)
public class GetAllReservationsParam {
   private byte[] symmKey;
   private Date startDate;
   @Valid
   private Page page;

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public Date getStartDate() {
      return this.startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }
}
