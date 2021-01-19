package be.fgov.ehealth.rn.baselegaldata.v1;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
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
   protected int nationalityCode;
   @XmlElement(
      name = "InceptionDate",
      required = true,
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

   public int getNationalityCode() {
      return this.nationalityCode;
   }

   public void setNationalityCode(int value) {
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
