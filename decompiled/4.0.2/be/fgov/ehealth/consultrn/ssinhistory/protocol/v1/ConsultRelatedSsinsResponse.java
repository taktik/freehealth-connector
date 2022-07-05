package be.fgov.ehealth.consultrn.ssinhistory.protocol.v1;

import be.fgov.ehealth.consultrn.ssinhistory.core.v1.RelatedSsinsType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultRelatedSsinsResponseType",
   propOrder = {"relatedSsins"}
)
@XmlRootElement(
   name = "ConsultRelatedSsinsResponse"
)
public class ConsultRelatedSsinsResponse extends ConsultCurrentSsinResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RelatedSsins"
   )
   protected RelatedSsinsType relatedSsins;

   public ConsultRelatedSsinsResponse() {
   }

   public RelatedSsinsType getRelatedSsins() {
      return this.relatedSsins;
   }

   public void setRelatedSsins(RelatedSsinsType value) {
      this.relatedSsins = value;
   }
}
