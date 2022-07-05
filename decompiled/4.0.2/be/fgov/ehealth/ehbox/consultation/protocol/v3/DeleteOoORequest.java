package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
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
   name = "DeleteOoORequestType",
   propOrder = {"boxId", "ooOIds"}
)
@XmlRootElement(
   name = "DeleteOoORequest"
)
public class DeleteOoORequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BoxId"
   )
   protected BoxIdType boxId;
   @XmlElement(
      name = "OoOId",
      required = true
   )
   protected List<String> ooOIds;

   public DeleteOoORequest() {
   }

   public BoxIdType getBoxId() {
      return this.boxId;
   }

   public void setBoxId(BoxIdType value) {
      this.boxId = value;
   }

   public List<String> getOoOIds() {
      if (this.ooOIds == null) {
         this.ooOIds = new ArrayList();
      }

      return this.ooOIds;
   }
}
