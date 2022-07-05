package be.fgov.ehealth.consultrn._1_0.protocol;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HeaderType",
   propOrder = {"applicationId", "date", "sequenceNumber", "environment"}
)
public class HeaderType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationId",
      required = true
   )
   protected String applicationId;
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
      name = "SequenceNumber",
      required = true
   )
   protected BigInteger sequenceNumber;
   @XmlElement(
      name = "Environment",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected EnvironmentType environment;

   public HeaderType() {
   }

   public String getApplicationId() {
      return this.applicationId;
   }

   public void setApplicationId(String value) {
      this.applicationId = value;
   }

   public DateTime getDate() {
      return this.date;
   }

   public void setDate(DateTime value) {
      this.date = value;
   }

   public BigInteger getSequenceNumber() {
      return this.sequenceNumber;
   }

   public void setSequenceNumber(BigInteger value) {
      this.sequenceNumber = value;
   }

   public EnvironmentType getEnvironment() {
      return this.environment;
   }

   public void setEnvironment(EnvironmentType value) {
      this.environment = value;
   }
}
