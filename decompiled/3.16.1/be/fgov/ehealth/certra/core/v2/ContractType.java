package be.fgov.ehealth.certra.core.v2;

import be.fgov.ehealth.commons.core.v2.ActorType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContractType",
   propOrder = {"dn", "signer", "text"}
)
public class ContractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DN",
      required = true
   )
   protected String dn;
   @XmlElement(
      name = "Signer",
      required = true
   )
   protected ActorType signer;
   @XmlElement(
      name = "Text",
      required = true
   )
   protected TextType text;

   public String getDN() {
      return this.dn;
   }

   public void setDN(String value) {
      this.dn = value;
   }

   public ActorType getSigner() {
      return this.signer;
   }

   public void setSigner(ActorType value) {
      this.signer = value;
   }

   public TextType getText() {
      return this.text;
   }

   public void setText(TextType value) {
      this.text = value;
   }
}
