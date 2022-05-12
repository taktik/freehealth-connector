package be.fgov.ehealth.dics.core.v4.actual.common;

import be.fgov.ehealth.dics.protocol.v4.ConsultDmppType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DmppKeyType"
)
@XmlSeeAlso({ConsultDmppType.class})
public class DmppKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "DeliveryEnvironment",
      required = true
   )
   protected String deliveryEnvironment;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlAttribute(
      name = "CodeType",
      required = true
   )
   protected String codeType;

   public DmppKeyType() {
   }

   public String getDeliveryEnvironment() {
      return this.deliveryEnvironment;
   }

   public void setDeliveryEnvironment(String value) {
      this.deliveryEnvironment = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getCodeType() {
      return this.codeType;
   }

   public void setCodeType(String value) {
      this.codeType = value;
   }
}
