package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindNonMedicinalProductRequestType",
   propOrder = {"findByName", "findByCNK"}
)
@XmlRootElement(
   name = "FindNonMedicinalProductRequest"
)
public class FindNonMedicinalProductRequest extends DicsRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByName"
   )
   protected String findByName;
   @XmlElement(
      name = "FindByCNK"
   )
   protected String findByCNK;

   public FindNonMedicinalProductRequest() {
   }

   public String getFindByName() {
      return this.findByName;
   }

   public void setFindByName(String value) {
      this.findByName = value;
   }

   public String getFindByCNK() {
      return this.findByCNK;
   }

   public void setFindByCNK(String value) {
      this.findByCNK = value;
   }
}
