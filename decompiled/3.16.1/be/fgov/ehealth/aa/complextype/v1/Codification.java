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
   name = "CodificationType",
   propOrder = {"code", "typeCode", "authenticSource", "descriptions"}
)
@XmlRootElement(
   name = "Codification"
)
public class Codification implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "TypeCode",
      required = true
   )
   protected String typeCode;
   @XmlElement(
      name = "AuthenticSource",
      required = true
   )
   protected String authenticSource;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected List<NameType> descriptions;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getTypeCode() {
      return this.typeCode;
   }

   public void setTypeCode(String value) {
      this.typeCode = value;
   }

   public String getAuthenticSource() {
      return this.authenticSource;
   }

   public void setAuthenticSource(String value) {
      this.authenticSource = value;
   }

   public List<NameType> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
