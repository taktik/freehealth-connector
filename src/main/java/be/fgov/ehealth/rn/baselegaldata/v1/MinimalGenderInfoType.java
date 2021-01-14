package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MinimalGenderInfoType",
   propOrder = {"genderCode", "inceptionDate"}
)
public class MinimalGenderInfoType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GenderCode"
   )
   @XmlSchemaType(
      name = "string"
   )
   protected GenderCodeType genderCode;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public GenderCodeType getGenderCode() {
      return this.genderCode;
   }

   public void setGenderCode(GenderCodeType value) {
      this.genderCode = value;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
