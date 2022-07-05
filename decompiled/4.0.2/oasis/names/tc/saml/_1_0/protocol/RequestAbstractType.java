package oasis.names.tc.saml._1_0.protocol;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.joda.time.DateTime;
import org.w3._2000._09.xmldsig.Signature;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestAbstractType",
   propOrder = {"respondWiths", "signature"}
)
@XmlSeeAlso({Request.class})
public abstract class RequestAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RespondWith"
   )
   protected List<QName> respondWiths;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Signature signature;
   @XmlAttribute(
      name = "RequestID",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String requestID;
   @XmlAttribute(
      name = "MajorVersion",
      required = true
   )
   protected BigInteger majorVersion;
   @XmlAttribute(
      name = "MinorVersion",
      required = true
   )
   protected BigInteger minorVersion;
   @XmlAttribute(
      name = "IssueInstant",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime issueInstant;

   public RequestAbstractType() {
   }

   public List<QName> getRespondWiths() {
      if (this.respondWiths == null) {
         this.respondWiths = new ArrayList();
      }

      return this.respondWiths;
   }

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }

   public String getRequestID() {
      return this.requestID;
   }

   public void setRequestID(String value) {
      this.requestID = value;
   }

   public BigInteger getMajorVersion() {
      return this.majorVersion;
   }

   public void setMajorVersion(BigInteger value) {
      this.majorVersion = value;
   }

   public BigInteger getMinorVersion() {
      return this.minorVersion;
   }

   public void setMinorVersion(BigInteger value) {
      this.minorVersion = value;
   }

   public DateTime getIssueInstant() {
      return this.issueInstant;
   }

   public void setIssueInstant(DateTime value) {
      this.issueInstant = value;
   }
}
