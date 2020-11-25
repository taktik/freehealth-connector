package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AccessRightListType",
   propOrder = {"accessrights"}
)
public class AccessRightListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "accessright"
   )
   protected List<AccessRightType> accessrights;

   public List<AccessRightType> getAccessrights() {
      if (this.accessrights == null) {
         this.accessrights = new ArrayList();
      }

      return this.accessrights;
   }
}
