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
   name = "FindVmpGroupResponseType",
   propOrder = {"vmpGroups"}
)
@XmlRootElement(
   name = "FindVmpGroupResponse"
)
public class FindVmpGroupResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "VmpGroup"
   )
   protected List<ConsultVmpGroupType> vmpGroups;

   public List<ConsultVmpGroupType> getVmpGroups() {
      if (this.vmpGroups == null) {
         this.vmpGroups = new ArrayList();
      }

      return this.vmpGroups;
   }
}
