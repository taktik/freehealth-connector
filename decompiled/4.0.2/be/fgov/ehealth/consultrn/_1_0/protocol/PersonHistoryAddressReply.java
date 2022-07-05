package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.AddressHistoryType;
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
   name = "PersonHistoryAddressReplyType",
   propOrder = {"ssin", "addressHistories"}
)
@XmlRootElement(
   name = "PersonHistoryAddressReply"
)
public class PersonHistoryAddressReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "AddressHistory"
   )
   protected List<AddressHistoryType> addressHistories;

   public PersonHistoryAddressReply() {
   }

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public List<AddressHistoryType> getAddressHistories() {
      if (this.addressHistories == null) {
         this.addressHistories = new ArrayList();
      }

      return this.addressHistories;
   }
}
