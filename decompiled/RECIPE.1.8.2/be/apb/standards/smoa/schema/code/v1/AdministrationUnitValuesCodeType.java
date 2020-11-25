package be.apb.standards.smoa.schema.code.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AdministrationUnitValuesCodeType",
   propOrder = {"cd"}
)
public class AdministrationUnitValuesCodeType extends AbstractAdministrationUnitValuesCodeType {
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String cd;

   public String getCd() {
      return this.cd;
   }

   public void setCd(String value) {
      this.cd = value;
   }
}
