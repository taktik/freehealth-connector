package be.fgov.ehealth.samcivics.schemas.v2;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.samcivics.type.v2.ParagraphAndTherapyType;
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
   name = "FindReimbursementConditionsResponseType",
   propOrder = {"paragraphAndTherapies"}
)
@XmlRootElement(
   name = "FindReimbursementConditionsResponse"
)
public class FindReimbursementConditionsResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ParagraphAndTherapy",
      required = true
   )
   protected List<ParagraphAndTherapyType> paragraphAndTherapies;

   public FindReimbursementConditionsResponse() {
   }

   public List<ParagraphAndTherapyType> getParagraphAndTherapies() {
      if (this.paragraphAndTherapies == null) {
         this.paragraphAndTherapies = new ArrayList();
      }

      return this.paragraphAndTherapies;
   }
}
