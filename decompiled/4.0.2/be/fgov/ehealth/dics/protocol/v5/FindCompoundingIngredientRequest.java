package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindCompoundingIngredientRequestType",
   propOrder = {"findByAnyName", "findByCNK"}
)
@XmlRootElement(
   name = "FindCompoundingIngredientRequest"
)
public class FindCompoundingIngredientRequest extends DicsRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByAnyName"
   )
   protected String findByAnyName;
   @XmlElement(
      name = "FindByCNK"
   )
   protected String findByCNK;

   public FindCompoundingIngredientRequest() {
   }

   public String getFindByAnyName() {
      return this.findByAnyName;
   }

   public void setFindByAnyName(String value) {
      this.findByAnyName = value;
   }

   public String getFindByCNK() {
      return this.findByCNK;
   }

   public void setFindByCNK(String value) {
      this.findByCNK = value;
   }
}
