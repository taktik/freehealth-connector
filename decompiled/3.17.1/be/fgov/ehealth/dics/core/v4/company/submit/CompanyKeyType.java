package be.fgov.ehealth.dics.core.v4.company.submit;

import be.fgov.ehealth.dics.protocol.v4.ConsultCompanyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CompanyKeyType"
)
@XmlSeeAlso({ConsultCompanyType.class})
public class CompanyKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "ActorNr",
      required = true
   )
   protected String actorNr;

   public String getActorNr() {
      return this.actorNr;
   }

   public void setActorNr(String value) {
      this.actorNr = value;
   }
}
