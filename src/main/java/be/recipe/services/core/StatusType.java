package be.recipe.services.core;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusType",
   propOrder = {"code", "messageCode", "messages", "statusUpdater", "prescriptionStatus"}
)
public class StatusType {
   @XmlElement(
      required = true
   )
   protected String code;
   protected String messageCode;
   protected List<LocalisedString> messages;
   protected String statusUpdater;
   protected String prescriptionStatus;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getMessageCode() {
      return this.messageCode;
   }

   public void setMessageCode(String value) {
      this.messageCode = value;
   }

   public List<LocalisedString> getMessages() {
      if (this.messages == null) {
         this.messages = new ArrayList();
      }

      return this.messages;
   }

   public String getStatusUpdater() {
      return this.statusUpdater;
   }

   public void setStatusUpdater(String value) {
      this.statusUpdater = value;
   }

   public String getPrescriptionStatus() {
      return this.prescriptionStatus;
   }

   public void setPrescriptionStatus(String value) {
      this.prescriptionStatus = value;
   }

}
