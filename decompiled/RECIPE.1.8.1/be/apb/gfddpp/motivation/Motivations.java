package be.apb.gfddpp.motivation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "motivations",
   propOrder = {"sguid", "motivation", "requestTime", "requestorID"}
)
public class Motivations {
   @XmlElement(
      name = "SGUID",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String sguid;
   @XmlElement(
      required = true
   )
   protected List<Motivation> motivation;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar requestTime;
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String requestorID;

   public String getSGUID() {
      return this.sguid;
   }

   public void setSGUID(String value) {
      this.sguid = value;
   }

   public List<Motivation> getMotivation() {
      if (this.motivation == null) {
         this.motivation = new ArrayList();
      }

      return this.motivation;
   }

   public XMLGregorianCalendar getRequestTime() {
      return this.requestTime;
   }

   public void setRequestTime(XMLGregorianCalendar value) {
      this.requestTime = value;
   }

   public String getRequestorID() {
      return this.requestorID;
   }

   public void setRequestorID(String value) {
      this.requestorID = value;
   }
}
