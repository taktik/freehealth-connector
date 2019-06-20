package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
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
   name = "GetExistingApplicationIdsResponseType",
   propOrder = {"applicationIds"}
)
@XmlRootElement(
   name = "GetExistingApplicationIdsResponse"
)
public class GetExistingApplicationIdsResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationId",
      nillable = true
   )
   protected List<String> applicationIds;

   public List<String> getApplicationIds() {
      if (this.applicationIds == null) {
         this.applicationIds = new ArrayList();
      }

      return this.applicationIds;
   }
}
