package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindAmppRequestType",
   propOrder = {"findByPackage", "findByDmpp"}
)
@XmlRootElement(
   name = "FindAmppRequest"
)
public class FindAmppRequest extends DicsRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByPackage"
   )
   protected FindByPackageType findByPackage;
   @XmlElement(
      name = "FindByDmpp"
   )
   protected FindByDmppType findByDmpp;

   public FindAmppRequest() {
   }

   public FindByPackageType getFindByPackage() {
      return this.findByPackage;
   }

   public void setFindByPackage(FindByPackageType value) {
      this.findByPackage = value;
   }

   public FindByDmppType getFindByDmpp() {
      return this.findByDmpp;
   }

   public void setFindByDmpp(FindByDmppType value) {
      this.findByDmpp = value;
   }
}
