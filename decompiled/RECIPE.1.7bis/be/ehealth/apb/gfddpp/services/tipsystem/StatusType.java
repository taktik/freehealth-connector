package be.ehealth.apb.gfddpp.services.tipsystem;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusType",
   namespace = "urn:be:fgov:ehealth:commons:core:v1",
   propOrder = {"code", "message"}
)
public class StatusType {
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "Message",
      required = true
   )
   protected List<LocalisedString> message;

   public String getCode() {
      return this.code;
   }

   public void setCode(String var1) {
      this.code = var1;
   }

   public List<LocalisedString> getMessage() {
      if (this.message == null) {
         this.message = new ArrayList();
      }

      return this.message;
   }
}
