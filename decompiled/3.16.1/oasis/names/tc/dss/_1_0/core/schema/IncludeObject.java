package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = ""
)
@XmlRootElement(
   name = "IncludeObject"
)
public class IncludeObject implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "WhichDocument"
   )
   @XmlIDREF
   @XmlSchemaType(
      name = "IDREF"
   )
   protected Object whichDocument;
   @XmlAttribute(
      name = "hasObjectTagsAndAttributesSet"
   )
   protected Boolean hasObjectTagsAndAttributesSet;
   @XmlAttribute(
      name = "ObjId"
   )
   protected String objId;
   @XmlAttribute(
      name = "createReference"
   )
   protected Boolean createReference;

   public Object getWhichDocument() {
      return this.whichDocument;
   }

   public void setWhichDocument(Object value) {
      this.whichDocument = value;
   }

   public boolean isHasObjectTagsAndAttributesSet() {
      return this.hasObjectTagsAndAttributesSet == null ? false : this.hasObjectTagsAndAttributesSet;
   }

   public void setHasObjectTagsAndAttributesSet(Boolean value) {
      this.hasObjectTagsAndAttributesSet = value;
   }

   public String getObjId() {
      return this.objId;
   }

   public void setObjId(String value) {
      this.objId = value;
   }

   public boolean isCreateReference() {
      return this.createReference == null ? true : this.createReference;
   }

   public void setCreateReference(Boolean value) {
      this.createReference = value;
   }
}
