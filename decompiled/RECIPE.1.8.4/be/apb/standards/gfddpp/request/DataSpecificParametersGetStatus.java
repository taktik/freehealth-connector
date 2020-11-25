package be.apb.standards.gfddpp.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "dataSpecificParametersGetStatus",
   propOrder = {"sguid", "dguid"}
)
public class DataSpecificParametersGetStatus {
   @XmlElement(
      name = "SGUID"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String sguid;
   @XmlElement(
      name = "DGUID"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String dguid;

   public String getSGUID() {
      return this.sguid;
   }

   public void setSGUID(String value) {
      this.sguid = value;
   }

   public String getDGUID() {
      return this.dguid;
   }

   public void setDGUID(String value) {
      this.dguid = value;
   }
}
