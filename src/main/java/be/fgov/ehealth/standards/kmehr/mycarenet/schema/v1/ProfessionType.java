package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDEMPLOYMENTSITUATION;
import be.fgov.ehealth.standards.kmehr.mycarenet.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDPROFESSION;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "professionType",
   propOrder = {"ids", "cds", "text"}
)
public class ProfessionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id"
   )
   protected List<IDPROFESSION> ids;
   @XmlElement(
      name = "cd"
   )
   protected List<CDEMPLOYMENTSITUATION> cds;
   protected TextType text;

   public List<IDPROFESSION> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<CDEMPLOYMENTSITUATION> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public TextType getText() {
      return this.text;
   }

   public void setText(TextType value) {
      this.text = value;
   }
}
