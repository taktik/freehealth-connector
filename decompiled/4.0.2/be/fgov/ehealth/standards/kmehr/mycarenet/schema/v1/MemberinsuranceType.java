package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDINSURANCE;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "memberinsuranceType",
   propOrder = {"id", "membership"}
)
public class MemberinsuranceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected IDINSURANCE id;
   @XmlElement(
      required = true
   )
   protected String membership;

   public MemberinsuranceType() {
   }

   public IDINSURANCE getId() {
      return this.id;
   }

   public void setId(IDINSURANCE value) {
      this.id = value;
   }

   public String getMembership() {
      return this.membership;
   }

   public void setMembership(String value) {
      this.membership = value;
   }
}
