package be.fgov.ehealth.samcivics.schemas.v2;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetProfessionalAuthorizationsRequestType",
   propOrder = {"qualificationListId", "inputDate"}
)
@XmlRootElement(
   name = "GetProfessionalAuthorizationsRequest"
)
public class GetProfessionalAuthorizationsRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "QualificationListId",
      required = true
   )
   protected String qualificationListId;
   @XmlElement(
      name = "InputDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inputDate;
   @XmlAttribute(
      name = "lang",
      namespace = "http://www.w3.org/XML/1998/namespace",
      required = true
   )
   protected String lang;

   public String getQualificationListId() {
      return this.qualificationListId;
   }

   public void setQualificationListId(String value) {
      this.qualificationListId = value;
   }

   public DateTime getInputDate() {
      return this.inputDate;
   }

   public void setInputDate(DateTime value) {
      this.inputDate = value;
   }

   public String getLang() {
      return this.lang;
   }

   public void setLang(String value) {
      this.lang = value;
   }
}
