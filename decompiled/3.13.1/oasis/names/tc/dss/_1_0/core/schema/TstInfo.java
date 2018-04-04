package oasis.names.tc.dss._1_0.core.schema;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.Duration;
import oasis.names.tc.saml._1_0.assertion.NameIdentifierType;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"serialNumber", "creationTime", "policy", "errorBound", "ordered", "tsa"}
)
@XmlRootElement(
   name = "TstInfo"
)
public class TstInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SerialNumber",
      required = true
   )
   protected BigInteger serialNumber;
   @XmlElement(
      name = "CreationTime",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime creationTime;
   @XmlElement(
      name = "Policy"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String policy;
   @XmlElement(
      name = "ErrorBound"
   )
   protected Duration errorBound;
   @XmlElement(
      name = "Ordered",
      defaultValue = "false"
   )
   protected Boolean ordered;
   @XmlElement(
      name = "TSA"
   )
   protected NameIdentifierType tsa;

   public BigInteger getSerialNumber() {
      return this.serialNumber;
   }

   public void setSerialNumber(BigInteger value) {
      this.serialNumber = value;
   }

   public DateTime getCreationTime() {
      return this.creationTime;
   }

   public void setCreationTime(DateTime value) {
      this.creationTime = value;
   }

   public String getPolicy() {
      return this.policy;
   }

   public void setPolicy(String value) {
      this.policy = value;
   }

   public Duration getErrorBound() {
      return this.errorBound;
   }

   public void setErrorBound(Duration value) {
      this.errorBound = value;
   }

   public Boolean isOrdered() {
      return this.ordered;
   }

   public void setOrdered(Boolean value) {
      this.ordered = value;
   }

   public NameIdentifierType getTSA() {
      return this.tsa;
   }

   public void setTSA(NameIdentifierType value) {
      this.tsa = value;
   }
}
