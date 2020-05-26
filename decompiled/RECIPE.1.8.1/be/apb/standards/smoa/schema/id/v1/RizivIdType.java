package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RizivIdType",
   propOrder = {"riziv"}
)
public class RizivIdType extends AbstractCareProviderIdType {
   @XmlElement(
      required = true
   )
   protected String riziv;

   public String getRiziv() {
      return this.riziv;
   }

   public void setRiziv(String value) {
      this.riziv = value;
   }
}
