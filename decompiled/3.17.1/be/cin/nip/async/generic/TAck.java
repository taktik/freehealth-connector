package be.cin.nip.async.generic;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TAck",
   propOrder = {"value"}
)
public class TAck implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected byte[] value;
   @XmlAttribute(
      name = "Issuer",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String issuer;
   @XmlAttribute(
      name = "AppliesTo",
      required = true
   )
   protected String appliesTo;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NCName"
   )
   protected String id;
   @XmlAttribute(
      name = "Reference"
   )
   protected String reference;
   @XmlAttribute(
      name = "ResultMajor",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String resultMajor;
   @XmlAttribute(
      name = "ResultMinor"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String resultMinor;
   @XmlAttribute(
      name = "ResultMessage"
   )
   protected String resultMessage;

   public byte[] getValue() {
      return this.value;
   }

   public void setValue(byte[] value) {
      this.value = value;
   }

   public String getIssuer() {
      return this.issuer;
   }

   public void setIssuer(String value) {
      this.issuer = value;
   }

   public String getAppliesTo() {
      return this.appliesTo;
   }

   public void setAppliesTo(String value) {
      this.appliesTo = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }

   public String getResultMajor() {
      return this.resultMajor;
   }

   public void setResultMajor(String value) {
      this.resultMajor = value;
   }

   public String getResultMinor() {
      return this.resultMinor;
   }

   public void setResultMinor(String value) {
      this.resultMinor = value;
   }

   public String getResultMessage() {
      return this.resultMessage;
   }

   public void setResultMessage(String value) {
      this.resultMessage = value;
   }
}
