package be.cin.nip.sync.reg.v1;

import be.cin.mycarenet.esb.common.v2.CareProviderType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "registrantType",
   propOrder = {"careProvider"}
)
public class RegistrantType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CareProvider",
      required = true
   )
   protected CareProviderType careProvider;

   public RegistrantType() {
   }

   public CareProviderType getCareProvider() {
      return this.careProvider;
   }

   public void setCareProvider(CareProviderType value) {
      this.careProvider = value;
   }
}
