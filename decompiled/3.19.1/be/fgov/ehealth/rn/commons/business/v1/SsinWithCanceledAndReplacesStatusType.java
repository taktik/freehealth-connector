package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SsinWithCanceledAndReplacesStatusType",
   propOrder = {"value"}
)
public class SsinWithCanceledAndReplacesStatusType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Canceled"
   )
   protected Boolean canceled;
   @XmlAttribute(
      name = "Replaces"
   )
   protected String replaces;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public Boolean isCanceled() {
      return this.canceled;
   }

   public void setCanceled(Boolean value) {
      this.canceled = value;
   }

   public String getReplaces() {
      return this.replaces;
   }

   public void setReplaces(String value) {
      this.replaces = value;
   }
}
