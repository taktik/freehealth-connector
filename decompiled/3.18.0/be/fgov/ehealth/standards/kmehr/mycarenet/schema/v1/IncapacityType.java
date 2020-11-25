package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDINCAPACITY;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "incapacityType",
   propOrder = {"cds", "incapacityreason", "percentage", "outofhomeallowed"}
)
public class IncapacityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "cd",
      required = true
   )
   protected List<CDINCAPACITY> cds;
   protected IncapacityreasonType incapacityreason;
   protected BigDecimal percentage;
   protected Boolean outofhomeallowed;

   public List<CDINCAPACITY> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public IncapacityreasonType getIncapacityreason() {
      return this.incapacityreason;
   }

   public void setIncapacityreason(IncapacityreasonType value) {
      this.incapacityreason = value;
   }

   public BigDecimal getPercentage() {
      return this.percentage;
   }

   public void setPercentage(BigDecimal value) {
      this.percentage = value;
   }

   public Boolean isOutofhomeallowed() {
      return this.outofhomeallowed;
   }

   public void setOutofhomeallowed(Boolean value) {
      this.outofhomeallowed = value;
   }
}
