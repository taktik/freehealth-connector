package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "JudgmentType",
   propOrder = {"judgmentDate", "judgmentLocation"}
)
public class JudgmentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "JudgmentDate"
   )
   protected String judgmentDate;
   @XmlElement(
      name = "JudgmentLocation"
   )
   protected LocationType judgmentLocation;

   public JudgmentType() {
   }

   public String getJudgmentDate() {
      return this.judgmentDate;
   }

   public void setJudgmentDate(String value) {
      this.judgmentDate = value;
   }

   public LocationType getJudgmentLocation() {
      return this.judgmentLocation;
   }

   public void setJudgmentLocation(LocationType value) {
      this.judgmentLocation = value;
   }
}
