package be.fgov.ehealth.dics.core.v3.virtual.common;

import be.fgov.ehealth.dics.core.v3.core.Text255Type;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VmpGroupType",
   propOrder = {"name", "noGenericPrescriptionReasonCode", "noSwitchReasonCode"}
)
public class VmpGroupType extends VmpGroupKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected Text255Type name;
   @XmlElement(
      name = "NoGenericPrescriptionReasonCode"
   )
   protected String noGenericPrescriptionReasonCode;
   @XmlElement(
      name = "NoSwitchReasonCode"
   )
   protected String noSwitchReasonCode;

   public Text255Type getName() {
      return this.name;
   }

   public void setName(Text255Type value) {
      this.name = value;
   }

   public String getNoGenericPrescriptionReasonCode() {
      return this.noGenericPrescriptionReasonCode;
   }

   public void setNoGenericPrescriptionReasonCode(String value) {
      this.noGenericPrescriptionReasonCode = value;
   }

   public String getNoSwitchReasonCode() {
      return this.noSwitchReasonCode;
   }

   public void setNoSwitchReasonCode(String value) {
      this.noSwitchReasonCode = value;
   }
}
