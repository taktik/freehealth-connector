package be.fgov.ehealth.etkdepot._1_0.protocol;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentifierType",
   propOrder = {"type", "value", "applicationID"}
)
public class IdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Type",
      required = true
   )
   protected String type;
   @XmlElement(
      name = "Value",
      required = true
   )
   protected String value;
   @XmlElement(
      name = "ApplicationID"
   )
   protected String applicationID;

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getApplicationID() {
      return this.applicationID;
   }

   public void setApplicationID(String value) {
      this.applicationID = value;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      IdentifierType that = (IdentifierType) o;
      return Objects.equals(type, that.type) &&
              Objects.equals(value, that.value) &&
              Objects.equals(applicationID, that.applicationID);
   }

   @Override
   public int hashCode() {
      return Objects.hash(type, value, applicationID);
   }
}
