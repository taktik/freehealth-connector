package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.Attribute;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "QualityDescriberType",
   propOrder = {"qualityTypeCodes", "qualityTypeFriendlyNames", "attributes"}
)
@XmlSeeAlso({SupportedQuality.class})
public class QualityDescriberType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "QualityTypeCode",
      required = true
   )
   protected List<TypeCodeType> qualityTypeCodes;
   @XmlElement(
      name = "QualityTypeFriendlyName",
      required = true
   )
   protected List<NameType> qualityTypeFriendlyNames;
   @XmlElement(
      name = "Attribute",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
      required = true
   )
   protected List<Attribute> attributes;

   public List<TypeCodeType> getQualityTypeCodes() {
      if (this.qualityTypeCodes == null) {
         this.qualityTypeCodes = new ArrayList();
      }

      return this.qualityTypeCodes;
   }

   public List<NameType> getQualityTypeFriendlyNames() {
      if (this.qualityTypeFriendlyNames == null) {
         this.qualityTypeFriendlyNames = new ArrayList();
      }

      return this.qualityTypeFriendlyNames;
   }

   public List<Attribute> getAttributes() {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      return this.attributes;
   }
}
