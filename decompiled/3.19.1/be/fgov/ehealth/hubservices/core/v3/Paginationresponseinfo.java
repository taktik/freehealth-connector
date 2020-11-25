package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Paginationresponseinfo",
   propOrder = {"pagesize", "totalrecordcount"}
)
public class Paginationresponseinfo implements Serializable {
   private static final long serialVersionUID = 1L;
   protected int pagesize;
   protected int totalrecordcount;

   public int getPagesize() {
      return this.pagesize;
   }

   public void setPagesize(int value) {
      this.pagesize = value;
   }

   public int getTotalrecordcount() {
      return this.totalrecordcount;
   }

   public void setTotalrecordcount(int value) {
      this.totalrecordcount = value;
   }
}
