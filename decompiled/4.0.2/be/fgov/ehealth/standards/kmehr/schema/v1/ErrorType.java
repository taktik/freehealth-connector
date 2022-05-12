package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDERROR;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "errorType",
   propOrder = {"ids", "cds", "description", "url"}
)
public class ErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id"
   )
   protected List<IDKMEHR> ids;
   @XmlElement(
      name = "cd",
      required = true
   )
   protected List<CDERROR> cds;
   @XmlElement(
      required = true
   )
   protected TextType description;
   protected String url;

   public ErrorType() {
   }

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<CDERROR> getCds() {
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
