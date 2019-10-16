package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2000._09.xmldsig.CanonicalizationMethod;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenericTimeStampType",
   propOrder = {"referenceInfos", "includes", "canonicalizationMethod", "encapsulatedTimeStampsAndXMLTimeStamps"}
)
@XmlSeeAlso({XAdESTimeStampType.class, OtherTimeStamp.class})
public abstract class GenericTimeStampType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ReferenceInfo"
   )
   protected List<ReferenceInfo> referenceInfos;
   @XmlElement(
      name = "Include"
   )
   protected List<Include> includes;
   @XmlElement(
      name = "CanonicalizationMethod",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected CanonicalizationMethod canonicalizationMethod;
   @XmlElements({@XmlElement(
   name = "EncapsulatedTimeStamp",
   type = EncapsulatedPKIData.class
), @XmlElement(
   name = "XMLTimeStamp",
   type = Any.class
)})
   protected List<Serializable> encapsulatedTimeStampsAndXMLTimeStamps;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public List<ReferenceInfo> getReferenceInfos() {
      if (this.referenceInfos == null) {
         this.referenceInfos = new ArrayList();
      }

      return this.referenceInfos;
   }

   public List<Include> getIncludes() {
      if (this.includes == null) {
         this.includes = new ArrayList();
      }

      return this.includes;
   }

   public CanonicalizationMethod getCanonicalizationMethod() {
      return this.canonicalizationMethod;
   }

   public void setCanonicalizationMethod(CanonicalizationMethod value) {
      this.canonicalizationMethod = value;
   }

   public List<Serializable> getEncapsulatedTimeStampsAndXMLTimeStamps() {
      if (this.encapsulatedTimeStampsAndXMLTimeStamps == null) {
         this.encapsulatedTimeStampsAndXMLTimeStamps = new ArrayList();
      }

      return this.encapsulatedTimeStampsAndXMLTimeStamps;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
