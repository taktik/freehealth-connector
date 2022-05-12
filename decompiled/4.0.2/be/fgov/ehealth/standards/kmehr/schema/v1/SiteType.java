package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDSITE;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "siteType",
   propOrder = {"text", "cd"}
)
public class SiteType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected TextType text;
   protected CDSITE cd;

   public SiteType() {
   }

   public TextType getText() {
      return this.text;
   }

   public void setText(TextType value) {
      this.text = value;
   }

   public CDSITE getCd() {
      return this.cd;
   }

   public void setCd(CDSITE value) {
      this.cd = value;
   }
}
