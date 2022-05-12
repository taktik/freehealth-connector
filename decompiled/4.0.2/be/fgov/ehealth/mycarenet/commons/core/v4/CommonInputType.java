package be.fgov.ehealth.mycarenet.commons.core.v4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CommonInputType",
   propOrder = {"request", "origin", "inputReference", "nipReference", "references", "attributes"}
)
public class CommonInputType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Request",
      required = true
   )
   protected RequestType request;
   @XmlElement(
      name = "Origin",
      required = true
   )
   protected OriginType origin;
   @XmlElement(
      name = "InputReference"
   )
   protected String inputReference;
   @XmlElement(
      name = "NIPReference"
   )
   protected String nipReference;
   @XmlElement(
      name = "Reference"
   )
   protected List<ReferenceType> references;
   @XmlElement(
      name = "Attribute"
   )
   protected List<AttributeType> attributes;

   public CommonInputType() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public OriginType getOrigin() {
      return this.origin;
   }

   public void setOrigin(OriginType value) {
      this.origin = value;
   }

   public String getInputReference() {
      return this.inputReference;
   }

   public void setInputReference(String value) {
      this.inputReference = value;
   }

   public String getNIPReference() {
      return this.nipReference;
   }

   public void setNIPReference(String value) {
      this.nipReference = value;
   }

   public List<ReferenceType> getReferences() {
      if (this.references == null) {
         this.references = new ArrayList();
      }

      return this.references;
   }

   public List<AttributeType> getAttributes() {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      return this.attributes;
   }
}
