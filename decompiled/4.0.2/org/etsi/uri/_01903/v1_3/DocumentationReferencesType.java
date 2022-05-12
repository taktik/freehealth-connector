package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DocumentationReferencesType",
   propOrder = {"documentationReferences"}
)
public class DocumentationReferencesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DocumentationReference",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> documentationReferences;

   public DocumentationReferencesType() {
   }

   public List<String> getDocumentationReferences() {
      if (this.documentationReferences == null) {
         this.documentationReferences = new ArrayList();
      }

      return this.documentationReferences;
   }
}
