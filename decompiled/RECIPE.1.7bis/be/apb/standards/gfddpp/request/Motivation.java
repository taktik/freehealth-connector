package be.apb.standards.gfddpp.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "motivation",
   propOrder = {"motivationtext", "motivationtype"}
)
public class Motivation {
   @XmlElement(
      required = true
   )
   protected String motivationtext;
   @XmlElement(
      required = true
   )
   protected String motivationtype;

   public String getMotivationtext() {
      return this.motivationtext;
   }

   public void setMotivationtext(String value) {
      this.motivationtext = value;
   }

   public String getMotivationtype() {
      return this.motivationtype;
   }

   public void setMotivationtype(String value) {
      this.motivationtype = value;
   }
}
