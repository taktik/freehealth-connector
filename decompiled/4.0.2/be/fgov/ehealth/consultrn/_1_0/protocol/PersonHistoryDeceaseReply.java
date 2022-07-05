package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.DeceaseHistoryType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonHistoryDeceaseReplyType",
   propOrder = {"ssin", "deceaseHistories"}
)
@XmlRootElement(
   name = "PersonHistoryDeceaseReply"
)
public class PersonHistoryDeceaseReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "DeceaseHistory"
   )
   protected List<DeceaseHistoryType> deceaseHistories;

   public PersonHistoryDeceaseReply() {
   }

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public List<DeceaseHistoryType> getDeceaseHistories() {
      if (this.deceaseHistories == null) {
         this.deceaseHistories = new ArrayList();
      }

      return this.deceaseHistories;
   }
}
