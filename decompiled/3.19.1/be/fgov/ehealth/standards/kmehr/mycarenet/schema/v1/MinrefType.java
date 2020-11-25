package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.dt.v1.TextType;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "minrefType",
   propOrder = {"text", "decimal"}
)
public class MinrefType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected TextType text;
   protected BigDecimal decimal;

   public TextType getText() {
      return this.text;
   }

   public void setText(TextType value) {
      this.text = value;
   }

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }
}
