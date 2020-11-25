package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultVmpType",
   propOrder = {"name", "abbreviation", "wadas", "additionalFields", "commentedClassifications", "vmpGroup", "vtm", "vmpComponents"}
)
public class ConsultVmpType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "Abbreviation",
      required = true
   )
   protected ConsultTextType abbreviation;
   @XmlElement(
      name = "Wada"
   )
   protected List<ConsultWadaType> wadas;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlElement(
      name = "CommentedClassification"
   )
   protected List<ConsultCommentedClassificationType> commentedClassifications;
   @XmlElement(
      name = "VmpGroup",
      required = true
   )
   protected ConsultVmpGroupType vmpGroup;
   @XmlElement(
      name = "Vtm"
   )
   protected ConsultVtmType vtm;
   @XmlElement(
      name = "VmpComponent",
      required = true
   )
   protected List<ConsultVmpComponentType> vmpComponents;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected int code;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public ConsultTextType getAbbreviation() {
      return this.abbreviation;
   }

   public void setAbbreviation(ConsultTextType value) {
      this.abbreviation = value;
   }

   public List<ConsultWadaType> getWadas() {
      if (this.wadas == null) {
         this.wadas = new ArrayList();
      }

      return this.wadas;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public List<ConsultCommentedClassificationType> getCommentedClassifications() {
      if (this.commentedClassifications == null) {
         this.commentedClassifications = new ArrayList();
      }

      return this.commentedClassifications;
   }

   public ConsultVmpGroupType getVmpGroup() {
      return this.vmpGroup;
   }

   public void setVmpGroup(ConsultVmpGroupType value) {
      this.vmpGroup = value;
   }

   public ConsultVtmType getVtm() {
      return this.vtm;
   }

   public void setVtm(ConsultVtmType value) {
      this.vtm = value;
   }

   public List<ConsultVmpComponentType> getVmpComponents() {
      if (this.vmpComponents == null) {
         this.vmpComponents = new ArrayList();
      }

      return this.vmpComponents;
   }

   public int getCode() {
      return this.code;
   }

   public void setCode(int value) {
      this.code = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
