package be.fgov.ehealth.hubservices.core.v3;

import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
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
   name = "TransactionIdType",
   propOrder = {"ids"}
)
@XmlRootElement(
   name = "TransactionIdType"
)
public class TransactionIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id",
      required = true
   )
   protected List<IDKMEHR> ids;

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }
}
