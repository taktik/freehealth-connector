package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultCompoundingFormulaType",
   propOrder = {"synonyms", "additionalFields"}
)
public class ConsultCompoundingFormulaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Synonym",
      required = true
   )
   protected List<SynonymType> synonyms;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlAttribute(
      name = "CodeType"
   )
   protected String codeType;
   @XmlAttribute(
      name = "ProductId",
      required = true
   )
   protected String productId;

   public ConsultCompoundingFormulaType() {
   }

   public List<SynonymType> getSynonyms() {
      if (this.synonyms == null) {
         this.synonyms = new ArrayList();
      }

      return this.synonyms;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getCodeType() {
      return this.codeType == null ? "CNK" : this.codeType;
   }

   public void setCodeType(String value) {
      this.codeType = value;
   }

   public String getProductId() {
      return this.productId;
   }

   public void setProductId(String value) {
      this.productId = value;
   }
}
