package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultTextType",
   propOrder = {"texts"}
)
public class ConsultTextType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Text",
      required = true
   )
   protected List<Text> texts;

   public ConsultTextType() {
   }

   public List<Text> getTexts() {
      if (this.texts == null) {
         this.texts = new ArrayList();
      }

      return this.texts;
   }
}
