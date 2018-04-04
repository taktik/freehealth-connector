package be.fgov.ehealth.samcivics.type.v2;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VerseType",
   propOrder = {"verseSeq", "verseNum", "verseSeqParent", "verseLevel", "verseType", "checkBoxInd", "minCheckNum", "andClauseNum", "texts", "requestType", "agreementTerm", "maxPackageNumber", "legalReference", "modificationDate", "agreementYearMax", "agreementRenewalMax", "sexRestricted", "minimumAgeAuthorized", "maximumAgeAuthorized", "maximumContentQuantity", "maximumStrengthQuantity", "maximumDurationQuantity", "otherAddedDocumentInd", "purchasingAdvisorQualListId", "addedDocumentInd"}
)
public class VerseType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "VerseSeq",
      required = true
   )
   protected BigInteger verseSeq;
   @XmlElement(
      name = "VerseNum",
      required = true
   )
   protected BigInteger verseNum;
   @XmlElement(
      name = "VerseSeqParent",
      required = true
   )
   protected BigInteger verseSeqParent;
   @XmlElement(
      name = "VerseLevel",
      required = true
   )
   protected BigInteger verseLevel;
   @XmlElement(
      name = "VerseType"
   )
   protected String verseType;
   @XmlElement(
      name = "CheckBoxInd"
   )
   protected boolean checkBoxInd;
   @XmlElement(
      name = "MinCheckNum"
   )
   protected BigInteger minCheckNum;
   @XmlElement(
      name = "AndClauseNum"
   )
   protected BigInteger andClauseNum;
   @XmlElement(
      name = "Text",
      required = true
   )
   protected List<TextType> texts;
   @XmlElement(
      name = "RequestType"
   )
   protected String requestType;
   @XmlElement(
      name = "AgreementTerm"
   )
   protected AgreementTermType agreementTerm;
   @XmlElement(
      name = "MaxPackageNumber"
   )
   protected BigInteger maxPackageNumber;
   @XmlElement(
      name = "LegalReference"
   )
   protected String legalReference;
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
      name = "AgreementYearMax"
   )
   protected BigInteger agreementYearMax;
   @XmlElement(
      name = "AgreementRenewalMax"
   )
   protected BigInteger agreementRenewalMax;
   @XmlElement(
      name = "SexRestricted"
   )
   protected Boolean sexRestricted;
   @XmlElement(
      name = "MinimumAgeAuthorized"
   )
   protected MinimumAgeAuthorizedType minimumAgeAuthorized;
   @XmlElement(
      name = "MaximumAgeAuthorized"
   )
   protected MaximumAgeAuthorizedType maximumAgeAuthorized;
   @XmlElement(
      name = "MaximumContentQuantity"
   )
   protected MaximumContentType maximumContentQuantity;
   @XmlElement(
      name = "MaximumStrengthQuantity"
   )
   protected MaximumStrengthType maximumStrengthQuantity;
   @XmlElement(
      name = "MaximumDurationQuantity"
   )
   protected MaximumDurationType maximumDurationQuantity;
   @XmlElement(
      name = "OtherAddedDocumentInd"
   )
   protected Boolean otherAddedDocumentInd;
   @XmlElement(
      name = "PurchasingAdvisorQualListId"
   )
   protected String purchasingAdvisorQualListId;
   @XmlElement(
      name = "AddedDocumentInd"
   )
   protected boolean addedDocumentInd;

   public BigInteger getVerseSeq() {
      return this.verseSeq;
   }

   public void setVerseSeq(BigInteger value) {
      this.verseSeq = value;
   }

   public BigInteger getVerseNum() {
      return this.verseNum;
   }

   public void setVerseNum(BigInteger value) {
      this.verseNum = value;
   }

   public BigInteger getVerseSeqParent() {
      return this.verseSeqParent;
   }

   public void setVerseSeqParent(BigInteger value) {
      this.verseSeqParent = value;
   }

   public BigInteger getVerseLevel() {
      return this.verseLevel;
   }

   public void setVerseLevel(BigInteger value) {
      this.verseLevel = value;
   }

   public String getVerseType() {
      return this.verseType;
   }

   public void setVerseType(String value) {
      this.verseType = value;
   }

   public boolean isCheckBoxInd() {
      return this.checkBoxInd;
   }

   public void setCheckBoxInd(boolean value) {
      this.checkBoxInd = value;
   }

   public BigInteger getMinCheckNum() {
      return this.minCheckNum;
   }

   public void setMinCheckNum(BigInteger value) {
      this.minCheckNum = value;
   }

   public BigInteger getAndClauseNum() {
      return this.andClauseNum;
   }

   public void setAndClauseNum(BigInteger value) {
      this.andClauseNum = value;
   }

   public List<TextType> getTexts() {
      if (this.texts == null) {
         this.texts = new ArrayList();
      }

      return this.texts;
   }

   public String getRequestType() {
      return this.requestType;
   }

   public void setRequestType(String value) {
      this.requestType = value;
   }

   public AgreementTermType getAgreementTerm() {
      return this.agreementTerm;
   }

   public void setAgreementTerm(AgreementTermType value) {
      this.agreementTerm = value;
   }

   public BigInteger getMaxPackageNumber() {
      return this.maxPackageNumber;
   }

   public void setMaxPackageNumber(BigInteger value) {
      this.maxPackageNumber = value;
   }

   public String getLegalReference() {
      return this.legalReference;
   }

   public void setLegalReference(String value) {
      this.legalReference = value;
   }

   public DateTime getModificationDate() {
      return this.modificationDate;
   }

   public void setModificationDate(DateTime value) {
      this.modificationDate = value;
   }

   public BigInteger getAgreementYearMax() {
      return this.agreementYearMax;
   }

   public void setAgreementYearMax(BigInteger value) {
      this.agreementYearMax = value;
   }

   public BigInteger getAgreementRenewalMax() {
      return this.agreementRenewalMax;
   }

   public void setAgreementRenewalMax(BigInteger value) {
      this.agreementRenewalMax = value;
   }

   public Boolean isSexRestricted() {
      return this.sexRestricted;
   }

   public void setSexRestricted(Boolean value) {
      this.sexRestricted = value;
   }

   public MinimumAgeAuthorizedType getMinimumAgeAuthorized() {
      return this.minimumAgeAuthorized;
   }

   public void setMinimumAgeAuthorized(MinimumAgeAuthorizedType value) {
      this.minimumAgeAuthorized = value;
   }

   public MaximumAgeAuthorizedType getMaximumAgeAuthorized() {
      return this.maximumAgeAuthorized;
   }

   public void setMaximumAgeAuthorized(MaximumAgeAuthorizedType value) {
      this.maximumAgeAuthorized = value;
   }

   public MaximumContentType getMaximumContentQuantity() {
      return this.maximumContentQuantity;
   }

   public void setMaximumContentQuantity(MaximumContentType value) {
      this.maximumContentQuantity = value;
   }

   public MaximumStrengthType getMaximumStrengthQuantity() {
      return this.maximumStrengthQuantity;
   }

   public void setMaximumStrengthQuantity(MaximumStrengthType value) {
      this.maximumStrengthQuantity = value;
   }

   public MaximumDurationType getMaximumDurationQuantity() {
      return this.maximumDurationQuantity;
   }

   public void setMaximumDurationQuantity(MaximumDurationType value) {
      this.maximumDurationQuantity = value;
   }

   public Boolean isOtherAddedDocumentInd() {
      return this.otherAddedDocumentInd;
   }

   public void setOtherAddedDocumentInd(Boolean value) {
      this.otherAddedDocumentInd = value;
   }

   public String getPurchasingAdvisorQualListId() {
      return this.purchasingAdvisorQualListId;
   }

   public void setPurchasingAdvisorQualListId(String value) {
      this.purchasingAdvisorQualListId = value;
   }

   public boolean isAddedDocumentInd() {
      return this.addedDocumentInd;
   }

   public void setAddedDocumentInd(boolean value) {
      this.addedDocumentInd = value;
   }
}
