package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindReimbursementRequestType",
   propOrder = {"findByGenericPrescriptionGroup", "findByLegalReferencePath", "findByDmpp", "findByPackage"}
)
@XmlRootElement(
   name = "FindReimbursementRequest"
)
public class FindReimbursementRequest extends DicsRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByGenericPrescriptionGroup"
   )
   protected FindByGenericPrescriptionGroupType findByGenericPrescriptionGroup;
   @XmlElement(
      name = "FindByLegalReferencePath"
   )
   protected String findByLegalReferencePath;
   @XmlElement(
      name = "FindByDmpp"
   )
   protected FindByDmppType findByDmpp;
   @XmlElement(
      name = "FindByPackage"
   )
   protected FindByPackageType findByPackage;

   public FindByGenericPrescriptionGroupType getFindByGenericPrescriptionGroup() {
      return this.findByGenericPrescriptionGroup;
   }

   public void setFindByGenericPrescriptionGroup(FindByGenericPrescriptionGroupType value) {
      this.findByGenericPrescriptionGroup = value;
   }

   public String getFindByLegalReferencePath() {
      return this.findByLegalReferencePath;
   }

   public void setFindByLegalReferencePath(String value) {
      this.findByLegalReferencePath = value;
   }

   public FindByDmppType getFindByDmpp() {
      return this.findByDmpp;
   }

   public void setFindByDmpp(FindByDmppType value) {
      this.findByDmpp = value;
   }

   public FindByPackageType getFindByPackage() {
      return this.findByPackage;
   }

   public void setFindByPackage(FindByPackageType value) {
      this.findByPackage = value;
   }
}
