package be.apb.gfddpp.motivation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "motivation",
   propOrder = {"dguid", "motivationtext", "motivationtype"}
)
public class Motivation {
   @XmlElement(
      name = "DGUID",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String dguid;
   @XmlElement(
      required = true
   )
   protected String motivationtext;
   @XmlElement(
      required = true
   )
   protected String motivationtype;

   public String getDGUID() {
      return this.dguid;
   }

   public void setDGUID(String value) {
      this.dguid = value;
   }

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
