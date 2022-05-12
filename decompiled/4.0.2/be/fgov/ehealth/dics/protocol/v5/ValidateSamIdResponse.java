package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ValidateSamIdResponseType",
   propOrder = {"date", "consultationType"}
)
@XmlRootElement(
   name = "ValidateSamIdResponse"
)
public class ValidateSamIdResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Date",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime date;
   @XmlElement(
      name = "ConsultationType",
      required = true
   )
   protected String consultationType;

   public ValidateSamIdResponse() {
   }

   public DateTime getDate() {
      return this.date;
   }

   public void setDate(DateTime value) {
      this.date = value;
   }

   public String getConsultationType() {
      return this.consultationType;
   }

   public void setConsultationType(String value) {
      this.consultationType = value;
   }
}
