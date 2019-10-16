package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDTELECOM;
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDKMEHR;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "telecomType",
   propOrder = {"ids", "cds", "telecomnumber"}
)
public class TelecomType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id"
   )
   protected List<IDKMEHR> ids;
   @XmlElement(
      name = "cd",
      required = true
   )
   protected List<CDTELECOM> cds;
   @XmlElement(
      required = true
   )
   protected String telecomnumber;

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<CDTELECOM> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public String getTelecomnumber() {
      return this.telecomnumber;
   }

   public void setTelecomnumber(String value) {
      this.telecomnumber = value;
   }
}
