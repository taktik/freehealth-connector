package be.fgov.ehealth.rn.baselegaldata.v1;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalityInfoDeclarationType",
   propOrder = {"nationalityCode", "inceptionDate", "expiryDate"}
)
public class NationalityInfoDeclarationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NationalityCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer nationalityCode;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;
   @XmlElement(
      name = "ExpiryDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime expiryDate;

   public NationalityInfoDeclarationType() {
   }

   public Integer getNationalityCode() {
      return this.nationalityCode;
   }

   public void setNationalityCode(Integer value) {
      this.nationalityCode = value;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }

   public DateTime getExpiryDate() {
      return this.expiryDate;
   }

   public void setExpiryDate(DateTime value) {
      this.expiryDate = value;
   }
}
