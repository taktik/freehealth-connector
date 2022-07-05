package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDFORMULARY;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "formularyreferenceType",
   propOrder = {"formularyname", "cds"}
)
public class FormularyreferenceType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected String formularyname;
   @XmlElement(
      name = "cd"
   )
   protected List<CDFORMULARY> cds;

   public FormularyreferenceType() {
   }

   public String getFormularyname() {
      return this.formularyname;
   }

   public void setFormularyname(String value) {
      this.formularyname = value;
   }

   public List<CDFORMULARY> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }
}
