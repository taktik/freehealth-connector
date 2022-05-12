package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDERRORMYCARENET;
import be.fgov.ehealth.standards.kmehr.mycarenet.dt.v1.TextType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "errorMyCarenetType",
   propOrder = {"cds", "description", "url"}
)
public class ErrorMyCarenetType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "cd",
      required = true
   )
   protected List<CDERRORMYCARENET> cds;
   @XmlElement(
      required = true
   )
   protected TextType description;
   protected String url;

   public ErrorMyCarenetType() {
   }

   public List<CDERRORMYCARENET> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public TextType getDescription() {
      return this.description;
   }

   public void setDescription(TextType value) {
      this.description = value;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String value) {
      this.url = value;
   }
}
