package be.fgov.ehealth.etee.commons._1_0.etee;

import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponse;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EteeResponseType",
   propOrder = {"status", "errors"}
)
@XmlSeeAlso({GetKeyResponse.class, GetNewKeyResponse.class})
public class EteeResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Status",
      required = true
   )
   protected Status status;
   @XmlElement(
      name = "Error"
   )
   protected List<ErrorType> errors;
   @XmlAttribute(
      name = "Id"
   )
   protected String id;

   public Status getStatus() {
      return this.status;
   }

   public void setStatus(Status value) {
      this.status = value;
   }

   public List<ErrorType> getErrors() {
      if (this.errors == null) {
         this.errors = new ArrayList();
      }

      return this.errors;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
