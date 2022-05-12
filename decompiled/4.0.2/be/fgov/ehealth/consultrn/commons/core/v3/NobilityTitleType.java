package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NobilityTitleType",
   propOrder = {"nobilityTitleCode", "nobilityTitleDescriptions"}
)
public class NobilityTitleType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NobilityTitleCode"
   )
   protected String nobilityTitleCode;
   @XmlElement(
      name = "NobilityTitleDescription"
   )
   protected List<NameType> nobilityTitleDescriptions;

   public NobilityTitleType() {
   }

   public String getNobilityTitleCode() {
      return this.nobilityTitleCode;
   }

   public void setNobilityTitleCode(String value) {
      this.nobilityTitleCode = value;
   }

   public List<NameType> getNobilityTitleDescriptions() {
      if (this.nobilityTitleDescriptions == null) {
         this.nobilityTitleDescriptions = new ArrayList();
      }

      return this.nobilityTitleDescriptions;
   }
}
