package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindLegislationTextRequestType",
   propOrder = {"findByDmpp", "findByLegalReferencePath", "findLegalBases"}
)
@XmlRootElement(
   name = "FindLegislationTextRequest"
)
public class FindLegislationTextRequest extends DicsRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByDmpp"
   )
   protected FindByDmppType findByDmpp;
   @XmlElement(
      name = "FindByLegalReferencePath"
   )
   protected String findByLegalReferencePath;
   @XmlElement(
      name = "FindLegalBases"
   )
   protected FindLegalBasesType findLegalBases;

   public FindByDmppType getFindByDmpp() {
      return this.findByDmpp;
   }

   public void setFindByDmpp(FindByDmppType value) {
      this.findByDmpp = value;
   }

   public String getFindByLegalReferencePath() {
      return this.findByLegalReferencePath;
   }

   public void setFindByLegalReferencePath(String value) {
      this.findByLegalReferencePath = value;
   }

   public FindLegalBasesType getFindLegalBases() {
      return this.findLegalBases;
   }

   public void setFindLegalBases(FindLegalBasesType value) {
      this.findLegalBases = value;
   }
}
