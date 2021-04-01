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
   name = "DeleteOoOResponseType",
   propOrder = {"ooOIds"}
)
@XmlRootElement(
   name = "DeleteOoOResponse"
)
public class DeleteOoOResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OoOId"
   )
   protected List<String> ooOIds;

   public List<String> getOoOIds() {
      if (this.ooOIds == null) {
         this.ooOIds = new ArrayList();
      }

      return this.ooOIds;
   }
}
