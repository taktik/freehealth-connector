package be.fgov.ehealth.samcivics.schemas.v2;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.samcivics.type.v2.FindAmppType;
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
   name = "FindPublicCNKResponseType",
   propOrder = {"ampps"}
)
@XmlRootElement(
   name = "FindPublicCNKResponse"
)
public class FindPublicCNKResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ampp",
      required = true
   )
   protected List<FindAmppType> ampps;

   public List<FindAmppType> getAmpps() {
      if (this.ampps == null) {
         this.ampps = new ArrayList();
      }

      return this.ampps;
   }
}
