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
   name = "FindAmppResponseType",
   propOrder = {"ampps"}
)
@XmlRootElement(
   name = "FindAmppResponse"
)
public class FindAmppResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ampp"
   )
   protected List<ConsultAmppType> ampps;

   public List<ConsultAmppType> getAmpps() {
      if (this.ampps == null) {
         this.ampps = new ArrayList();
      }

      return this.ampps;
   }
}
