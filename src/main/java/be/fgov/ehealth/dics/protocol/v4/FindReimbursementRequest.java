package be.fgov.ehealth.dics.protocol.v4;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.dics.core.v4.actual.common.DmppKeyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindReimbursementRequestType",
   propOrder = {"findByGenericPrescriptionGroup", "findByLegalReferencePath", "findByDmpp", "findByPackage"}
)
@XmlRootElement(
   name = "FindReimbursementRequest"
)
public class FindReimbursementRequest extends RequestType implements Serializable {
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
   protected DmppKeyType findByDmpp;
   @XmlElement(
      name = "FindByPackage"
   )
   protected FindByPackageType findByPackage;
   @XmlAttribute(
      name = "lang",
      namespace = "http://www.w3.org/XML/1998/namespace",
      required = true
   )
   protected String lang;
   @XmlAttribute(
      name = "SearchDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime searchDate;

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

   public DmppKeyType getFindByDmpp() {
      return this.findByDmpp;
   }

   public void setFindByDmpp(DmppKeyType value) {
      this.findByDmpp = value;
   }

   public FindByPackageType getFindByPackage() {
      return this.findByPackage;
   }

   public void setFindByPackage(FindByPackageType value) {
      this.findByPackage = value;
   }

   public String getLang() {
      return this.lang;
   }

   public void setLang(String value) {
      this.lang = value;
   }

   public DateTime getSearchDate() {
      return this.searchDate;
   }

   public void setSearchDate(DateTime value) {
      this.searchDate = value;
   }
}
