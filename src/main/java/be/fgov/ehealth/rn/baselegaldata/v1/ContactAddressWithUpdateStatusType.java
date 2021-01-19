package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContactAddressWithUpdateStatusType"
)
public class ContactAddressWithUpdateStatusType extends AbstractOptionalContactAddressType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Status"
   )
   protected UpdateDatagroupStatusType status;

   public UpdateDatagroupStatusType getStatus() {
      return this.status;
   }

   public void setStatus(UpdateDatagroupStatusType value) {
      this.status = value;
   }
}
