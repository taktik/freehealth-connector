package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
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
   name = "InsertOoOResponseType",
   propOrder = {"substitutes"}
)
@XmlRootElement(
   name = "InsertOoOResponse"
)
public class InsertOoOResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Substitute"
   )
   protected List<SubstituteType> substitutes;

   public List<SubstituteType> getSubstitutes() {
      if (this.substitutes == null) {
         this.substitutes = new ArrayList();
      }

      return this.substitutes;
   }
}
