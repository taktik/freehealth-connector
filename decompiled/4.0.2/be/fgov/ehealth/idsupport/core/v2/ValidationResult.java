package be.fgov.ehealth.idsupport.core.v2;

import be.fgov.ehealth.commons.core.v2.Id;
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
   name = "ValidationResultType",
   propOrder = {"ids", "card", "isValidCombination", "infos"}
)
@XmlRootElement(
   name = "ValidationResult"
)
public class ValidationResult implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Id"
   )
   protected List<Id> ids;
   @XmlElement(
      name = "Card"
   )
   protected String card;
   @XmlElement(
      name = "IsValidCombination"
   )
   protected String isValidCombination;
   @XmlElement(
      name = "Info"
   )
   protected List<ValidationInfoType> infos;

   public ValidationResult() {
   }

   public List<Id> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public String getCard() {
      return this.card;
   }

   public void setCard(String value) {
      this.card = value;
   }

   public String getIsValidCombination() {
      return this.isValidCombination;
   }

   public void setIsValidCombination(String value) {
      this.isValidCombination = value;
   }

   public List<ValidationInfoType> getInfos() {
      if (this.infos == null) {
         this.infos = new ArrayList();
      }

      return this.infos;
   }
}
