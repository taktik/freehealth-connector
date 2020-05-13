package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "galenicformType",
   propOrder = {"galenicformcode", "galenicformtext"}
)
public class GalenicformType {
   protected String galenicformcode;
   protected String galenicformtext;

   public String getGalenicformcode() {
      return this.galenicformcode;
   }

   public void setGalenicformcode(String value) {
      this.galenicformcode = value;
   }

   public String getGalenicformtext() {
      return this.galenicformtext;
   }

   public void setGalenicformtext(String value) {
      this.galenicformtext = value;
   }
}
