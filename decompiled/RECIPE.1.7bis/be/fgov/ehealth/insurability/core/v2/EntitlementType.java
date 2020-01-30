package be.fgov.ehealth.insurability.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EntitlementType",
   propOrder = {"code1", "code2", "thirdPartyPayerRegime"}
)
public class EntitlementType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code1",
      required = true
   )
   protected String code1;
   @XmlElement(
      name = "Code2",
      required = true
   )
   protected String code2;
   @XmlElement(
      name = "ThirdPartyPayerRegime",
      required = true
   )
   protected ThirdParytPayerRegimeType thirdPartyPayerRegime;

   public String getCode1() {
      return this.code1;
   }

   public void setCode1(String value) {
      this.code1 = value;
   }

   public String getCode2() {
      return this.code2;
   }

   public void setCode2(String value) {
      this.code2 = value;
   }

   public ThirdParytPayerRegimeType getThirdPartyPayerRegime() {
      return this.thirdPartyPayerRegime;
   }

   public void setThirdPartyPayerRegime(ThirdParytPayerRegimeType value) {
      this.thirdPartyPayerRegime = value;
   }
}
