package org.etsi.uri._01903.v1_3;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CRLIdentifierType",
   propOrder = {"issuer", "issueTime", "number"}
)
public class CRLIdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Issuer",
      required = true
   )
   protected String issuer;
   @XmlElement(
      name = "IssueTime",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime issueTime;
   @XmlElement(
      name = "Number"
   )
   protected BigInteger number;
   @XmlAttribute(
      name = "URI"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String uri;

   public String getIssuer() {
      return this.issuer;
   }

   public void setIssuer(String value) {
      this.issuer = value;
   }

   public DateTime getIssueTime() {
      return this.issueTime;
   }

   public void setIssueTime(DateTime value) {
      this.issueTime = value;
   }

   public BigInteger getNumber() {
      return this.number;
   }

   public void setNumber(BigInteger value) {
      this.number = value;
   }

   public String getURI() {
      return this.uri;
   }

   public void setURI(String value) {
      this.uri = value;
   }
}
