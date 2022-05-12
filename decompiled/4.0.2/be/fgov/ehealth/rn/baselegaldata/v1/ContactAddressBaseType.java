package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContactAddressBaseType"
)
public class ContactAddressBaseType extends AbstractOptionalContactAddressType implements Serializable {
   private static final long serialVersionUID = 1L;

   public ContactAddressBaseType() {
   }
}
