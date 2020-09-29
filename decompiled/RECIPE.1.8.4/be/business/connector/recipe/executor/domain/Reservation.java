package be.business.connector.recipe.executor.domain;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Reservation"
)
public class Reservation {
   private String rid;
   private Date creationDate;
   private Date lastupdate;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public Date getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(Date creationDate) {
      this.creationDate = creationDate;
   }

   public Date getLastupdate() {
      return this.lastupdate;
   }

   public void setLastupdate(Date lastupdate) {
      this.lastupdate = lastupdate;
   }
}
