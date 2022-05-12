package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDSUBSTANCE;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "substanceType",
   propOrder = {"substancename", "cd"}
)
public class SubstanceType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected String substancename;
   protected CDSUBSTANCE cd;

   public SubstanceType() {
   }

   public String getSubstancename() {
      return this.substancename;
   }

   public void setSubstancename(String value) {
      this.substancename = value;
   }

   public CDSUBSTANCE getCd() {
      return this.cd;
   }

   public void setCd(CDSUBSTANCE value) {
      this.cd = value;
   }
}
