package be.fgov.ehealth.dics.protocol.v5;

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
   name = "FindLegislationTextResponseType",
   propOrder = {"legalbases"}
)
@XmlRootElement(
   name = "FindLegislationTextResponse"
)
public class FindLegislationTextResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "LegalBasis"
   )
   protected List<ConsultLegalBasisType> legalbases;

   public FindLegislationTextResponse() {
   }

   public List<ConsultLegalBasisType> getLegalbases() {
      if (this.legalbases == null) {
         this.legalbases = new ArrayList();
      }

      return this.legalbases;
   }
}
