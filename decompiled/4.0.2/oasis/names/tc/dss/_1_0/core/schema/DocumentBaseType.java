package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DocumentBaseType"
)
@XmlSeeAlso({DocumentType.class, DocumentHash.class, TransformedData.class})
public abstract class DocumentBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "ID"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlAttribute(
      name = "RefURI"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String refURI;
   @XmlAttribute(
      name = "RefType"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String refType;
   @XmlAttribute(
      name = "SchemaRefs"
   )
   @XmlIDREF
   @XmlSchemaType(
      name = "IDREFS"
   )
   protected List<Object> schemaRefs;

   public DocumentBaseType() {
   }

   public String getID() {
      return this.id;
   }

   public void setID(String value) {
      this.id = value;
   }

   public String getRefURI() {
      return this.refURI;
   }

   public void setRefURI(String value) {
      this.refURI = value;
   }

   public String getRefType() {
      return this.refType;
   }

   public void setRefType(String value) {
      this.refType = value;
   }

   public List<Object> getSchemaRefs() {
      if (this.schemaRefs == null) {
         this.schemaRefs = new ArrayList();
      }

      return this.schemaRefs;
   }
}
