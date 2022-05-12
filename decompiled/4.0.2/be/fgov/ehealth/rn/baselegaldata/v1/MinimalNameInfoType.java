package be.fgov.ehealth.rn.baselegaldata.v1;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
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
   name = "MinimalNameInfoType",
   propOrder = {"lastName", "givenNames", "inceptionDate"}
)
public class MinimalNameInfoType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "LastName"
   )
   protected String lastName;
   @XmlElement(
      name = "GivenName"
   )
   protected List<GivenNameType> givenNames;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public MinimalNameInfoType() {
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String value) {
      this.lastName = value;
   }

   public List<GivenNameType> getGivenNames() {
      if (this.givenNames == null) {
         this.givenNames = new ArrayList();
      }

      return this.givenNames;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
