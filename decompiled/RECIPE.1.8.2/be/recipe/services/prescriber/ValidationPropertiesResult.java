package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "validationPropertiesResult",
   propOrder = {"xsdValidationFiles", "properties", "serverPropertiesVersion"}
)
@XmlRootElement(
   name = "validationPropertiesResult"
)
public class ValidationPropertiesResult extends ResponseType implements Equals, HashCode, ToString {
   @XmlElement(
      required = true
   )
   protected ValidationPropertiesResult.XsdValidationFiles xsdValidationFiles;
   @XmlElement(
      required = true
   )
   protected ValidationPropertiesResult.Properties properties;
   @XmlElement(
      required = true
   )
   protected String serverPropertiesVersion;

   public ValidationPropertiesResult.XsdValidationFiles getXsdValidationFiles() {
      return this.xsdValidationFiles;
   }

   public void setXsdValidationFiles(ValidationPropertiesResult.XsdValidationFiles value) {
      this.xsdValidationFiles = value;
   }

   public ValidationPropertiesResult.Properties getProperties() {
      return this.properties;
   }

   public void setProperties(ValidationPropertiesResult.Properties value) {
      this.properties = value;
   }

   public String getServerPropertiesVersion() {
      return this.serverPropertiesVersion;
   }

   public void setServerPropertiesVersion(String value) {
      this.serverPropertiesVersion = value;
   }

   public String toString() {
      ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
      StringBuilder buffer = new StringBuilder();
      this.append((ObjectLocator)null, buffer, strategy);
      return buffer.toString();
   }

   public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
      strategy.appendStart(locator, this, buffer);
      this.appendFields(locator, buffer, strategy);
      strategy.appendEnd(locator, this, buffer);
      return buffer;
   }

   public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
      super.appendFields(locator, buffer, strategy);
      ValidationPropertiesResult.XsdValidationFiles theXsdValidationFiles = this.getXsdValidationFiles();
      strategy.appendField(locator, this, "xsdValidationFiles", buffer, theXsdValidationFiles);
      ValidationPropertiesResult.Properties theProperties = this.getProperties();
      strategy.appendField(locator, this, "properties", buffer, theProperties);
      String theServerPropertiesVersion = this.getServerPropertiesVersion();
      strategy.appendField(locator, this, "serverPropertiesVersion", buffer, theServerPropertiesVersion);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof ValidationPropertiesResult)) {
         return false;
      } else if (this == object) {
         return true;
      } else if (!super.equals(thisLocator, thatLocator, object, strategy)) {
         return false;
      } else {
         ValidationPropertiesResult that = (ValidationPropertiesResult)object;
         ValidationPropertiesResult.XsdValidationFiles lhsXsdValidationFiles = this.getXsdValidationFiles();
         ValidationPropertiesResult.XsdValidationFiles rhsXsdValidationFiles = that.getXsdValidationFiles();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "xsdValidationFiles", lhsXsdValidationFiles), LocatorUtils.property(thatLocator, "xsdValidationFiles", rhsXsdValidationFiles), lhsXsdValidationFiles, rhsXsdValidationFiles)) {
            return false;
         } else {
            ValidationPropertiesResult.Properties lhsProperties = this.getProperties();
            ValidationPropertiesResult.Properties rhsProperties = that.getProperties();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "properties", lhsProperties), LocatorUtils.property(thatLocator, "properties", rhsProperties), lhsProperties, rhsProperties)) {
               return false;
            } else {
               String lhsServerPropertiesVersion = this.getServerPropertiesVersion();
               String rhsServerPropertiesVersion = that.getServerPropertiesVersion();
               return strategy.equals(LocatorUtils.property(thisLocator, "serverPropertiesVersion", lhsServerPropertiesVersion), LocatorUtils.property(thatLocator, "serverPropertiesVersion", rhsServerPropertiesVersion), lhsServerPropertiesVersion, rhsServerPropertiesVersion);
            }
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = super.hashCode(locator, strategy);
      ValidationPropertiesResult.XsdValidationFiles theXsdValidationFiles = this.getXsdValidationFiles();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "xsdValidationFiles", theXsdValidationFiles), currentHashCode, theXsdValidationFiles);
      ValidationPropertiesResult.Properties theProperties = this.getProperties();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "properties", theProperties), currentHashCode, theProperties);
      String theServerPropertiesVersion = this.getServerPropertiesVersion();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serverPropertiesVersion", theServerPropertiesVersion), currentHashCode, theServerPropertiesVersion);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"entries"}
   )
   public static class XsdValidationFiles implements Equals, HashCode, ToString {
      @XmlElement(
         name = "entry"
      )
      protected List<ValidationPropertiesResult.XsdValidationFiles.Entry> entries;

      public List<ValidationPropertiesResult.XsdValidationFiles.Entry> getEntries() {
         if (this.entries == null) {
            this.entries = new ArrayList();
         }

         return this.entries;
      }

      public String toString() {
         ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
         StringBuilder buffer = new StringBuilder();
         this.append((ObjectLocator)null, buffer, strategy);
         return buffer.toString();
      }

      public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
         strategy.appendStart(locator, this, buffer);
         this.appendFields(locator, buffer, strategy);
         strategy.appendEnd(locator, this, buffer);
         return buffer;
      }

      public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
         List<ValidationPropertiesResult.XsdValidationFiles.Entry> theEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
         strategy.appendField(locator, this, "entries", buffer, theEntries);
         return buffer;
      }

      public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
         if (!(object instanceof ValidationPropertiesResult.XsdValidationFiles)) {
            return false;
         } else if (this == object) {
            return true;
         } else {
            ValidationPropertiesResult.XsdValidationFiles that = (ValidationPropertiesResult.XsdValidationFiles)object;
            List<ValidationPropertiesResult.XsdValidationFiles.Entry> lhsEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
            List<ValidationPropertiesResult.XsdValidationFiles.Entry> rhsEntries = that.entries != null && !that.entries.isEmpty() ? that.getEntries() : null;
            return strategy.equals(LocatorUtils.property(thisLocator, "entries", lhsEntries), LocatorUtils.property(thatLocator, "entries", rhsEntries), lhsEntries, rhsEntries);
         }
      }

      public boolean equals(Object object) {
         EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
         return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
      }

      public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
         int currentHashCode = 1;
         List<ValidationPropertiesResult.XsdValidationFiles.Entry> theEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
         int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "entries", theEntries), currentHashCode, theEntries);
         return currentHashCode;
      }

      public int hashCode() {
         HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
         return this.hashCode((ObjectLocator)null, strategy);
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"key", "value"}
      )
      public static class Entry implements Equals, HashCode, ToString {
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

         public String toString() {
            ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            StringBuilder buffer = new StringBuilder();
            this.append((ObjectLocator)null, buffer, strategy);
            return buffer.toString();
         }

         public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            this.appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
         }

         public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            String theKey = this.getKey();
            strategy.appendField(locator, this, "key", buffer, theKey);
            byte[] theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue);
            return buffer;
         }

         public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ValidationPropertiesResult.XsdValidationFiles.Entry)) {
               return false;
            } else if (this == object) {
               return true;
            } else {
               ValidationPropertiesResult.XsdValidationFiles.Entry that = (ValidationPropertiesResult.XsdValidationFiles.Entry)object;
               String lhsKey = this.getKey();
               String rhsKey = that.getKey();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "key", lhsKey), LocatorUtils.property(thatLocator, "key", rhsKey), lhsKey, rhsKey)) {
                  return false;
               } else {
                  byte[] lhsValue = this.getValue();
                  byte[] rhsValue = that.getValue();
                  return strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue);
               }
            }
         }

         public boolean equals(Object object) {
            EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
         }

         public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            String theKey = this.getKey();
            int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "key", theKey), currentHashCode, theKey);
            byte[] theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue);
            return currentHashCode;
         }

         public int hashCode() {
            HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode((ObjectLocator)null, strategy);
         }
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"entries"}
   )
   public static class Properties implements Equals, HashCode, ToString {
      @XmlElement(
         name = "entry"
      )
      protected List<ValidationPropertiesResult.Properties.Entry> entries;

      public List<ValidationPropertiesResult.Properties.Entry> getEntries() {
         if (this.entries == null) {
            this.entries = new ArrayList();
         }

         return this.entries;
      }

      public String toString() {
         ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
         StringBuilder buffer = new StringBuilder();
         this.append((ObjectLocator)null, buffer, strategy);
         return buffer.toString();
      }

      public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
         strategy.appendStart(locator, this, buffer);
         this.appendFields(locator, buffer, strategy);
         strategy.appendEnd(locator, this, buffer);
         return buffer;
      }

      public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
         List<ValidationPropertiesResult.Properties.Entry> theEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
         strategy.appendField(locator, this, "entries", buffer, theEntries);
         return buffer;
      }

      public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
         if (!(object instanceof ValidationPropertiesResult.Properties)) {
            return false;
         } else if (this == object) {
            return true;
         } else {
            ValidationPropertiesResult.Properties that = (ValidationPropertiesResult.Properties)object;
            List<ValidationPropertiesResult.Properties.Entry> lhsEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
            List<ValidationPropertiesResult.Properties.Entry> rhsEntries = that.entries != null && !that.entries.isEmpty() ? that.getEntries() : null;
            return strategy.equals(LocatorUtils.property(thisLocator, "entries", lhsEntries), LocatorUtils.property(thatLocator, "entries", rhsEntries), lhsEntries, rhsEntries);
         }
      }

      public boolean equals(Object object) {
         EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
         return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
      }

      public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
         int currentHashCode = 1;
         List<ValidationPropertiesResult.Properties.Entry> theEntries = this.entries != null && !this.entries.isEmpty() ? this.getEntries() : null;
         int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "entries", theEntries), currentHashCode, theEntries);
         return currentHashCode;
      }

      public int hashCode() {
         HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
         return this.hashCode((ObjectLocator)null, strategy);
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"key", "value"}
      )
      public static class Entry implements Equals, HashCode, ToString {
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

         public String toString() {
            ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            StringBuilder buffer = new StringBuilder();
            this.append((ObjectLocator)null, buffer, strategy);
            return buffer.toString();
         }

         public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            this.appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
         }

         public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            String theValue = this.getKey();
            strategy.appendField(locator, this, "key", buffer, theValue);
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue);
            return buffer;
         }

         public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ValidationPropertiesResult.Properties.Entry)) {
               return false;
            } else if (this == object) {
               return true;
            } else {
               ValidationPropertiesResult.Properties.Entry that = (ValidationPropertiesResult.Properties.Entry)object;
               String lhsValue = this.getKey();
               String rhsValue = that.getKey();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "key", lhsValue), LocatorUtils.property(thatLocator, "key", rhsValue), lhsValue, rhsValue)) {
                  return false;
               } else {
                  lhsValue = this.getValue();
                  rhsValue = that.getValue();
                  return strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue);
               }
            }
         }

         public boolean equals(Object object) {
            EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
         }

         public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            String theValue = this.getKey();
            int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "key", theValue), currentHashCode, theValue);
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue);
            return currentHashCode;
         }

         public int hashCode() {
            HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode((ObjectLocator)null, strategy);
         }
      }
   }
}
