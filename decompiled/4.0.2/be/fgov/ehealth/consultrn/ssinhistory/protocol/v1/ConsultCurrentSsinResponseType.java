package be.fgov.ehealth.consultrn.ssinhistory.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.consultrn.ssinhistory.core.v1.SsinStatusType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultCurrentSsinResponseType",
   propOrder = {"ssin"}
)
@XmlSeeAlso({ConsultRelatedSsinsResponse.class})
public class ConsultCurrentSsinResponseType extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ssin"
   )
   protected SsinStatusType ssin;

   public ConsultCurrentSsinResponseType() {
   }

   public SsinStatusType getSsin() {
      return this.ssin;
   }

   public void setSsin(SsinStatusType value) {
      this.ssin = value;
   }
}
