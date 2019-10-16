package be.fgov.ehealth.samcivics.schemas.v2;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.samcivics.type.v2.AtmAndChildrenType;
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
   name = "GetParagraphIncludedSpecialtiesResponseType",
   propOrder = {"atmAndChildrens"}
)
@XmlRootElement(
   name = "GetParagraphIncludedSpecialtiesResponse"
)
public class GetParagraphIncludedSpecialtiesResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AtmAndChildren"
   )
   protected List<AtmAndChildrenType> atmAndChildrens;

   public List<AtmAndChildrenType> getAtmAndChildrens() {
      if (this.atmAndChildrens == null) {
         this.atmAndChildrens = new ArrayList();
      }

      return this.atmAndChildrens;
   }
}
