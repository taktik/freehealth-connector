package org.w3._2001._04.xmlenc;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EncryptedKeyType",
   propOrder = {"referenceList", "carriedKeyName"}
)
@XmlRootElement(
   name = "EncryptedKey"
)
public class EncryptedKey extends EncryptedType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ReferenceList"
   )
   protected ReferenceList referenceList;
   @XmlElement(
      name = "CarriedKeyName"
   )
   protected String carriedKeyName;
   @XmlAttribute(
      name = "Recipient"
   )
   protected String recipient;

   public ReferenceList getReferenceList() {
      return this.referenceList;
   }

   public void setReferenceList(ReferenceList value) {
      this.referenceList = value;
   }

   public String getCarriedKeyName() {
      return this.carriedKeyName;
   }

   public void setCarriedKeyName(String value) {
      this.carriedKeyName = value;
   }

   public String getRecipient() {
      return this.recipient;
   }

   public void setRecipient(String value) {
      this.recipient = value;
   }
}
