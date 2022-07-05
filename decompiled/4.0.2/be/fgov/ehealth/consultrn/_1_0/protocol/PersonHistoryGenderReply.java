package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.GenderHistoryType;
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
   name = "PersonHistoryGenderReplyType",
   propOrder = {"ssin", "genderHistories"}
)
@XmlRootElement(
   name = "PersonHistoryGenderReply"
)
public class PersonHistoryGenderReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "GenderHistory"
   )
   protected List<GenderHistoryType> genderHistories;

   public PersonHistoryGenderReply() {
   }

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public List<GenderHistoryType> getGenderHistories() {
      if (this.genderHistories == null) {
         this.genderHistories = new ArrayList();
      }

      return this.genderHistories;
   }
}
