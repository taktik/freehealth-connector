package be.fgov.ehealth.certra.protocol.v2;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
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
   name = "SubmitCSRForForeignerResponseType",
   propOrder = {"validationUrl", "expirationDate"}
)
@XmlRootElement(
   name = "SubmitCSRForForeignerResponse"
)
public class SubmitCSRForForeignerResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ValidationUrl"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String validationUrl;
   @XmlElement(
      name = "ExpirationDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime expirationDate;

   public String getValidationUrl() {
      return this.validationUrl;
   }

   public void setValidationUrl(String value) {
      this.validationUrl = value;
   }

   public DateTime getExpirationDate() {
      return this.expirationDate;
   }

   public void setExpirationDate(DateTime value) {
      this.expirationDate = value;
   }
}
