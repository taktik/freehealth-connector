package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.CivilstateHistoryType;
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
   name = "PersonHistoryCivilStateReplyType",
   propOrder = {"ssin", "civilstateHistories"}
)
@XmlRootElement(
   name = "PersonHistoryCivilStateReply"
)
public class PersonHistoryCivilStateReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "CivilstateHistory"
   )
   protected List<CivilstateHistoryType> civilstateHistories;

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public List<CivilstateHistoryType> getCivilstateHistories() {
      if (this.civilstateHistories == null) {
         this.civilstateHistories = new ArrayList();
      }

      return this.civilstateHistories;
   }
}
