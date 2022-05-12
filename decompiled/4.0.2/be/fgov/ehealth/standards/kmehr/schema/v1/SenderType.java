package be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "senderType",
   propOrder = {"hcparties"}
)
public class SenderType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "hcparty",
      required = true
   )
   protected List<HcpartyType> hcparties;

   public SenderType() {
   }

   public List<HcpartyType> getHcparties() {
      if (this.hcparties == null) {
         this.hcparties = new ArrayList();
      }

      return this.hcparties;
   }
}
