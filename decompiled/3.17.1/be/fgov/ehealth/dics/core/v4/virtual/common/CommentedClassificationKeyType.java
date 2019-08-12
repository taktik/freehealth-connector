package be.fgov.ehealth.dics.core.v4.virtual.common;

import be.fgov.ehealth.dics.protocol.v4.ConsultCommentedClassificationType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CommentedClassificationKeyType"
)
@XmlSeeAlso({ConsultCommentedClassificationType.class})
public class CommentedClassificationKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }
}
