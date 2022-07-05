package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindReferencesRequestType",
   propOrder = {"referenceEntity"}
)
@XmlRootElement(
   name = "FindReferencesRequest"
)
public class FindReferencesRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ReferenceEntity",
      required = true
   )
   protected String referenceEntity;

   public FindReferencesRequest() {
   }

   public String getReferenceEntity() {
      return this.referenceEntity;
   }

   public void setReferenceEntity(String value) {
      this.referenceEntity = value;
   }
}
