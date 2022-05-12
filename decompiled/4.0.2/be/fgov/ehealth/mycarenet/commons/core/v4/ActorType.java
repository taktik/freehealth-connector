package be.fgov.ehealth.mycarenet.commons.core.v4;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ActorType"
)
public class ActorType extends SubjectType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Role"
   )
   protected String role;

   public ActorType() {
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String value) {
      this.role = value;
   }
}
