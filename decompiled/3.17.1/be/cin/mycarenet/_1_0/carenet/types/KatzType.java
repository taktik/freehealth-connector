package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KatzType",
   propOrder = {"katzScore", "continenceDetails", "dementia", "prescripter"}
)
public class KatzType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlList
   @XmlElement(
      name = "KatzScore",
      type = Integer.class
   )
   protected List<Integer> katzScore;
   @XmlElement(
      name = "ContinenceDetails",
      defaultValue = "false"
   )
   protected boolean continenceDetails;
   @XmlElement(
      name = "Dementia"
   )
   protected DementiaType dementia;
   @XmlElement(
      name = "Prescripter"
   )
   protected String prescripter;

   public List<Integer> getKatzScore() {
      if (this.katzScore == null) {
         this.katzScore = new ArrayList();
      }

      return this.katzScore;
   }

   public boolean isContinenceDetails() {
      return this.continenceDetails;
   }

   public void setContinenceDetails(boolean value) {
      this.continenceDetails = value;
   }

   public DementiaType getDementia() {
      return this.dementia;
   }

   public void setDementia(DementiaType value) {
      this.dementia = value;
   }

   public String getPrescripter() {
      return this.prescripter;
   }

   public void setPrescripter(String value) {
      this.prescripter = value;
   }
}
