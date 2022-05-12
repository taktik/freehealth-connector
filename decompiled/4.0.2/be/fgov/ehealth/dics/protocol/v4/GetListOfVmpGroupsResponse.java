package be.fgov.ehealth.dics.protocol.v4;

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
   name = "GetListOfVmpGroupsResponseType",
   propOrder = {"vmpGroups"}
)
@XmlRootElement(
   name = "GetListOfVmpGroupsResponse"
)
public class GetListOfVmpGroupsResponse extends ListConsultationResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "VmpGroup"
   )
   protected List<VmpGroupListType> vmpGroups;

   public GetListOfVmpGroupsResponse() {
   }

   public List<VmpGroupListType> getVmpGroups() {
      if (this.vmpGroups == null) {
         this.vmpGroups = new ArrayList();
      }

      return this.vmpGroups;
   }
}
