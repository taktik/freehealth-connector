package be.fgov.ehealth.aa.complextype.v1;

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
   name = "OrganizationInfoType",
   propOrder = {"names", "codification"}
)
@XmlRootElement(
   name = "OrganizationInfo"
)
public class OrganizationInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected List<NameType> names;
   @XmlElement(
      name = "Codification",
      required = true
   )
   protected Codification codification;

   public OrganizationInfo() {
   }

   public List<NameType> getNames() {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      return this.names;
   }

   public Codification getCodification() {
      return this.codification;
   }

   public void setCodification(Codification value) {
      this.codification = value;
   }
}
