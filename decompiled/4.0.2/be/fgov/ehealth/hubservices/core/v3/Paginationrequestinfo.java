package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Paginationrequestinfo",
   propOrder = {"index"}
)
public class Paginationrequestinfo implements Serializable {
   private static final long serialVersionUID = 1L;
   protected int index;

   public Paginationrequestinfo() {
   }

   public int getIndex() {
      return this.index;
   }

   public void setIndex(int value) {
      this.index = value;
   }
}
