package be.fgov.ehealth.dics.core.v3.reimbursementlaw.submit;

import be.fgov.ehealth.dics.protocol.v3.ConsultReimbursementConditionType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReimbursementConditionKeyType"
)
@XmlSeeAlso({ConsultReimbursementConditionType.class})
public class ReimbursementConditionKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "LegalTextRelativePath",
      required = true
   )
   protected String legalTextRelativePath;
   @XmlAttribute(
      name = "Key",
      required = true
   )
   protected String key;

   public String getLegalTextRelativePath() {
      return this.legalTextRelativePath;
   }

   public void setLegalTextRelativePath(String value) {
      this.legalTextRelativePath = value;
   }

   public String getKey() {
      return this.key;
   }

   public void setKey(String value) {
      this.key = value;
   }
}
