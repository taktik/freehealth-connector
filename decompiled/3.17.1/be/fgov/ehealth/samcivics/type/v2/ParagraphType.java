package be.fgov.ehealth.samcivics.type.v2;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ParagraphType",
   propOrder = {"chapterName", "paragraphName", "keies", "agreementType", "agreementTypePro", "processType", "legalReference", "publicationDate", "modificationDate", "processTypeOverrule", "paragraphVersion"}
)
@XmlSeeAlso({ParagraphAndChildrenType.class, ParagraphAndTherapyType.class})
public class ParagraphType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ChapterName",
      required = true
   )
   protected String chapterName;
   @XmlElement(
      name = "ParagraphName",
      required = true
   )
   protected String paragraphName;
   @XmlElement(
      name = "Key"
   )
   protected List<TextType> keies;
   @XmlElement(
      name = "AgreementType"
   )
   protected String agreementType;
   @XmlElement(
      name = "AgreementTypePro"
   )
   protected String agreementTypePro;
   @XmlElement(
      name = "ProcessType"
   )
   protected BigInteger processType;
   @XmlElement(
      name = "LegalReference"
   )
   protected String legalReference;
   @XmlElement(
      name = "PublicationDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime publicationDate;
   @XmlElement(
      name = "ModificationDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime modificationDate;
   @XmlElement(
      name = "ProcessTypeOverrule"
   )
   protected String processTypeOverrule;
   @XmlElement(
      name = "ParagraphVersion",
      required = true
   )
   protected BigInteger paragraphVersion;

   public String getChapterName() {
      return this.chapterName;
   }

   public void setChapterName(String value) {
      this.chapterName = value;
   }

   public String getParagraphName() {
      return this.paragraphName;
   }

   public void setParagraphName(String value) {
      this.paragraphName = value;
   }

   public List<TextType> getKeies() {
      if (this.keies == null) {
         this.keies = new ArrayList();
      }

      return this.keies;
   }

   public String getAgreementType() {
      return this.agreementType;
   }

   public void setAgreementType(String value) {
      this.agreementType = value;
   }

   public String getAgreementTypePro() {
      return this.agreementTypePro;
   }

   public void setAgreementTypePro(String value) {
      this.agreementTypePro = value;
   }

   public BigInteger getProcessType() {
      return this.processType;
   }

   public void setProcessType(BigInteger value) {
      this.processType = value;
   }

   public String getLegalReference() {
      return this.legalReference;
   }

   public void setLegalReference(String value) {
      this.legalReference = value;
   }

   public DateTime getPublicationDate() {
      return this.publicationDate;
   }

   public void setPublicationDate(DateTime value) {
      this.publicationDate = value;
   }

   public DateTime getModificationDate() {
      return this.modificationDate;
   }

   public void setModificationDate(DateTime value) {
      this.modificationDate = value;
   }

   public String getProcessTypeOverrule() {
      return this.processTypeOverrule;
   }

   public void setProcessTypeOverrule(String value) {
      this.processTypeOverrule = value;
   }

   public BigInteger getParagraphVersion() {
      return this.paragraphVersion;
   }

   public void setParagraphVersion(BigInteger value) {
      this.paragraphVersion = value;
   }
}
