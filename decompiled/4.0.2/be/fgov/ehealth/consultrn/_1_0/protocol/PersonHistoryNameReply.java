package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.NameHistoryType;
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
   name = "PersonHistoryNameReplyType",
   propOrder = {"ssin", "nameHistories"}
)
@XmlRootElement(
   name = "PersonHistoryNameReply"
)
public class PersonHistoryNameReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "NameHistory"
   )
   protected List<NameHistoryType> nameHistories;

   public PersonHistoryNameReply() {
   }

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public List<NameHistoryType> getNameHistories() {
      if (this.nameHistories == null) {
         this.nameHistories = new ArrayList();
      }

      return this.nameHistories;
   }
}
