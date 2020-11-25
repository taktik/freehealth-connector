package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "validationPropertiesResult",
   propOrder = {"xsdValidationFiles", "properties", "serverPropertiesVersion"}
)
@XmlRootElement(
   name = "validationPropertiesResult"
)
public class ValidationPropertiesResult extends ResponseType {
   @XmlElement(
      required = true
   )
   protected XsdValidationFiles xsdValidationFiles;
   @XmlElement(
      required = true
   )
   protected Properties properties;
   @XmlElement(
      required = true
   )
   protected String serverPropertiesVersion;

   public XsdValidationFiles getXsdValidationFiles() {
      return this.xsdValidationFiles;
   }

   public void setXsdValidationFiles(XsdValidationFiles value) {
      this.xsdValidationFiles = value;
   }

   public Properties getProperties() {
      return this.properties;
   }

   public void setProperties(Properties value) {
      this.properties = value;
   }

   public String getServerPropertiesVersion() {
      return this.serverPropertiesVersion;
   }

   public void setServerPropertiesVersion(String value) {
      this.serverPropertiesVersion = value;
   }


   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"entries"}
   )
   public static class XsdValidationFiles {
      @XmlElement(
         name = "entry"
      )
      protected List<Entry> entries;

      public List<Entry> getEntries() {
         if (this.entries == null) {
            this.entries = new ArrayList();
         }

         return this.entries;
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"key", "value"}
      )
      public static class Entry {
         protected String key;
         protected byte[] value;

         public String getKey() {
            return this.key;
         }

         public void setKey(String value) {
            this.key = value;
         }

         public byte[] getValue() {
            return this.value;
         }

         public void setValue(byte[] value) {
            this.value = value;
         }
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"entries"}
   )
   public static class Properties {
      @XmlElement(
         name = "entry"
      )
      protected List<Entry> entries;

      public List<Entry> getEntries() {
         if (this.entries == null) {
            this.entries = new ArrayList();
         }

         return this.entries;
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"key", "value"}
      )
      public static class Entry {
         protected String key;
         protected String value;

         public String getKey() {
            return this.key;
         }

         public void setKey(String value) {
            this.key = value;
         }

         public String getValue() {
            return this.value;
         }

         public void setValue(String value) {
            this.value = value;
         }
      }
   }
}
