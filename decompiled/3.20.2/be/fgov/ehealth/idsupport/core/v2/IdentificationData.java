package be.fgov.ehealth.idsupport.core.v2;

import be.fgov.ehealth.commons.core.v2.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentificationDataType",
   propOrder = {"ids"}
)
@XmlRootElement(
   name = "IdentificationData"
)
public class IdentificationData implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Id",
      required = true
   )
   protected List<Id> ids;

   public List<Id> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }
}
