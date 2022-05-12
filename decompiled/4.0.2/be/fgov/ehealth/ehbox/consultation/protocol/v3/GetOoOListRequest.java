package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetOoOListRequestType",
   propOrder = {"boxId"}
)
@XmlRootElement(
   name = "GetOoOListRequest"
)
public class GetOoOListRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BoxId"
   )
   protected BoxIdType boxId;

   public GetOoOListRequest() {
   }

   public BoxIdType getBoxId() {
      return this.boxId;
   }

   public void setBoxId(BoxIdType value) {
      this.boxId = value;
   }
}
