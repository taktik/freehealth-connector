package be.fgov.ehealth.dics.core.v3.reimbursementlaw.submit;

import be.fgov.ehealth.dics.protocol.v3.ConsultFormalInterpretationType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FormalInterpretationKeyType"
)
@XmlSeeAlso({ConsultFormalInterpretationType.class})
public class FormalInterpretationKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Key",
      required = true
   )
   protected String key;

   public String getKey() {
      return this.key;
   }

   public void setKey(String value) {
      this.key = value;
   }
}
