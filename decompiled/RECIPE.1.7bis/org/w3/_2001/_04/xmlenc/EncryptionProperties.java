package org.w3._2001._04.xmlenc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EncryptionPropertiesType",
   propOrder = {"encryptionProperties"}
)
@XmlRootElement(
   name = "EncryptionProperties"
)
public class EncryptionProperties implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptionProperty",
      required = true
   )
   protected List<EncryptionProperty> encryptionProperties;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public List<EncryptionProperty> getEncryptionProperties() {
      if (this.encryptionProperties == null) {
         this.encryptionProperties = new ArrayList();
      }

      return this.encryptionProperties;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
