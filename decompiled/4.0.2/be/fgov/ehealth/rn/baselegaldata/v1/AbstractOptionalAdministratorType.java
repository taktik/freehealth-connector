package be.fgov.ehealth.rn.baselegaldata.v1;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractOptionalAdministratorType",
   propOrder = {"specialNotion", "location", "inceptionDate"}
)
@XmlSeeAlso({AdministratorBaseType.class, AdministratorWithStatusAndSourceType.class})
public abstract class AbstractOptionalAdministratorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SpecialNotion"
   )
   protected SpecialNotionType specialNotion;
   @XmlElement(
      name = "Location"
   )
   protected LocationType location;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public AbstractOptionalAdministratorType() {
   }

   public SpecialNotionType getSpecialNotion() {
      return this.specialNotion;
   }

   public void setSpecialNotion(SpecialNotionType value) {
      this.specialNotion = value;
   }

   public LocationType getLocation() {
      return this.location;
   }

   public void setLocation(LocationType value) {
      this.location = value;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
