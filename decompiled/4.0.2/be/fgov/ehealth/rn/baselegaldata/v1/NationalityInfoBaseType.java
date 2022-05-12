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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalityInfoBaseType",
   propOrder = {"nationalityCode", "nationalityDescriptions", "inceptionDate"}
)
@XmlSeeAlso({NationalityInfoWithSourceType.class})
public class NationalityInfoBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NationalityCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer nationalityCode;
   @XmlElement(
      name = "NationalityDescription"
   )
   protected List<LocalizedDescriptionType> nationalityDescriptions;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public NationalityInfoBaseType() {
   }

   public Integer getNationalityCode() {
      return this.nationalityCode;
   }

   public void setNationalityCode(Integer value) {
      this.nationalityCode = value;
   }

   public List<LocalizedDescriptionType> getNationalityDescriptions() {
      if (this.nationalityDescriptions == null) {
         this.nationalityDescriptions = new ArrayList();
      }

      return this.nationalityDescriptions;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
