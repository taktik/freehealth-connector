package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ParamedicType",
   propOrder = {"providerList"}
)
public class ParamedicType extends MedicalCoverCommonInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ProviderList"
   )
   protected ProviderList providerList;

   public ProviderList getProviderList() {
      return this.providerList;
   }

   public void setProviderList(ProviderList value) {
      this.providerList = value;
   }
}
