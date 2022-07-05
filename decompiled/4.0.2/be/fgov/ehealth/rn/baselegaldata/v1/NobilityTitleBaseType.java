package be.fgov.ehealth.rn.baselegaldata.v1;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.rn.commons.business.v1.LocalizedDescriptionType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NobilityTitleBaseType",
   propOrder = {"nobilityTitleCode", "nobilityTitleDescriptions", "inceptionDate"}
)
public class NobilityTitleBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NobilityTitleCode"
   )
   protected String nobilityTitleCode;
   @XmlElement(
      name = "NobilityTitleDescription"
   )
   protected List<LocalizedDescriptionType> nobilityTitleDescriptions;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public NobilityTitleBaseType() {
   }

   public String getNobilityTitleCode() {
      return this.nobilityTitleCode;
   }

   public void setNobilityTitleCode(String value) {
      this.nobilityTitleCode = value;
   }

   public List<LocalizedDescriptionType> getNobilityTitleDescriptions() {
      if (this.nobilityTitleDescriptions == null) {
         this.nobilityTitleDescriptions = new ArrayList();
      }

      return this.nobilityTitleDescriptions;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
