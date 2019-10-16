package be.fgov.ehealth.etee.ra.csr._1_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"usageTypes"}
)
public class UsagesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "UsageType"
   )
   @XmlSchemaType(
      name = "string"
   )
   protected List<UsageType> usageTypes;

   public List<UsageType> getUsageTypes() {
      if (this.usageTypes == null) {
         this.usageTypes = new ArrayList();
      }

      return this.usageTypes;
   }
}
