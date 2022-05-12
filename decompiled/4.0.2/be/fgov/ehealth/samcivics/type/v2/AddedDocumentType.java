package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AddedDocumentType",
   propOrder = {"chapterName", "paragraphName", "verseSeq", "documentSeq", "name", "formTypeName", "appendixTypeName", "mimeType", "addressURL"}
)
public class AddedDocumentType extends BaseType implements Serializable {
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
      name = "VerseSeq",
      required = true
   )
   protected BigInteger verseSeq;
   @XmlElement(
      name = "DocumentSeq",
      required = true
   )
   protected BigInteger documentSeq;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlElement(
      name = "FormTypeName",
      required = true
   )
   protected String formTypeName;
   @XmlElement(
      name = "AppendixTypeName",
      required = true
   )
   protected String appendixTypeName;
   @XmlElement(
      name = "MimeType"
   )
   protected String mimeType;
   @XmlElement(
      name = "AddressURL"
   )
   protected String addressURL;

   public AddedDocumentType() {
   }

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

   public BigInteger getVerseSeq() {
      return this.verseSeq;
   }

   public void setVerseSeq(BigInteger value) {
      this.verseSeq = value;
   }

   public BigInteger getDocumentSeq() {
      return this.documentSeq;
   }

   public void setDocumentSeq(BigInteger value) {
      this.documentSeq = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getFormTypeName() {
      return this.formTypeName;
   }

   public void setFormTypeName(String value) {
      this.formTypeName = value;
   }

   public String getAppendixTypeName() {
      return this.appendixTypeName;
   }

   public void setAppendixTypeName(String value) {
      this.appendixTypeName = value;
   }

   public String getMimeType() {
      return this.mimeType;
   }

   public void setMimeType(String value) {
      this.mimeType = value;
   }

   public String getAddressURL() {
      return this.addressURL;
   }

   public void setAddressURL(String value) {
      this.addressURL = value;
   }
}
