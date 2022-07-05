package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.BirthHistoryType;
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
   name = "PersonHistoryBirthReplyType",
   propOrder = {"ssin", "birthHistories"}
)
@XmlRootElement(
   name = "PersonHistoryBirthReply"
)
public class PersonHistoryBirthReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "BirthHistory"
   )
   protected List<BirthHistoryType> birthHistories;

   public PersonHistoryBirthReply() {
   }

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public List<BirthHistoryType> getBirthHistories() {
      if (this.birthHistories == null) {
         this.birthHistories = new ArrayList();
      }

      return this.birthHistories;
   }
}
