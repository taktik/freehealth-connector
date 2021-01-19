package be.fgov.ehealth.mycarenet.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdType",
   propOrder = {"nihii", "ssin", "cbe"}
)
public class IdType extends AbstractIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Nihii"
   )
   protected NihiiType nihii;
   @XmlElement(
      name = "Ssin"
   )
   protected ValueRefString ssin;
   @XmlElement(
      name = "Cbe"
   )
   protected ValueRefString cbe;

   public NihiiType getNihii() {
      return this.nihii;
   }

   public void setNihii(NihiiType value) {
      this.nihii = value;
   }

   public ValueRefString getSsin() {
      return this.ssin;
   }

   public void setSsin(ValueRefString value) {
      this.ssin = value;
   }

   public ValueRefString getCbe() {
      return this.cbe;
   }

   public void setCbe(ValueRefString value) {
      this.cbe = value;
   }
}
