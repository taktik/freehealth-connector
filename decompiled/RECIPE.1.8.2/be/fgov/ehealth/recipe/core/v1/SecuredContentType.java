package be.fgov.ehealth.recipe.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SecuredContentType",
   propOrder = {"securedContent"}
)
public class SecuredContentType {
   @XmlElement(
      name = "SecuredContent",
      required = true
   )
   protected byte[] securedContent;

   public byte[] getSecuredContent() {
      return this.securedContent;
   }

   public void setSecuredContent(byte[] value) {
      this.securedContent = (byte[])value;
   }
}
