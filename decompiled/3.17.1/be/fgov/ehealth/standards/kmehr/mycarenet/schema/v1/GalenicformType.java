package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDGALENICFORM;
import be.fgov.ehealth.standards.kmehr.mycarenet.dt.v1.TextType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "galenicformType",
   propOrder = {"cd", "galenicformtext"}
)
public class GalenicformType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected CDGALENICFORM cd;
   protected TextType galenicformtext;

   public CDGALENICFORM getCd() {
      return this.cd;
   }

   public void setCd(CDGALENICFORM value) {
      this.cd = value;
   }

   public TextType getGalenicformtext() {
      return this.galenicformtext;
   }

   public void setGalenicformtext(TextType value) {
      this.galenicformtext = value;
   }
}
