package oasis.names.tc.saml._2_0.assertion;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubjectConfirmationDataType",
   propOrder = {"content"}
)
@XmlSeeAlso({KeyInfoConfirmationDataType.class})
public class SubjectConfirmationDataType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlMixed
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> content;
   @XmlAttribute(
      name = "NotBefore"
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime notBefore;
   @XmlAttribute(
      name = "NotOnOrAfter"
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime notOnOrAfter;
   @XmlAttribute(
      name = "Recipient"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String recipient;
   @XmlAttribute(
      name = "InResponseTo"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NCName"
   )
   protected String inResponseTo;
   @XmlAttribute(
      name = "Address"
   )
   protected String address;
   @XmlAnyAttribute
   private Map<QName, String> otherAttributes = new HashMap();

   public SubjectConfirmationDataType() {
   }

   public List<Object> getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public DateTime getNotBefore() {
      return this.notBefore;
   }

   public void setNotBefore(DateTime value) {
      this.notBefore = value;
   }

   public DateTime getNotOnOrAfter() {
      return this.notOnOrAfter;
   }

   public void setNotOnOrAfter(DateTime value) {
      this.notOnOrAfter = value;
   }

   public String getRecipient() {
      return this.recipient;
   }

   public void setRecipient(String value) {
      this.recipient = value;
   }

   public String getInResponseTo() {
      return this.inResponseTo;
   }

   public void setInResponseTo(String value) {
      this.inResponseTo = value;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String value) {
      this.address = value;
   }

   public Map<QName, String> getOtherAttributes() {
      return this.otherAttributes;
   }
}
