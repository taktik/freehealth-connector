package be.fgov.ehealth.samcivics.schemas.v2;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindCNKRequestType",
   propOrder = {"productName", "version", "startDate", "chapterName", "paragraphName"}
)
@XmlRootElement(
   name = "FindCNKRequest"
)
public class FindCNKRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ProductName",
      required = true
   )
   protected String productName;
   @XmlElement(
      name = "Version"
   )
   protected BigInteger version;
   @XmlElement(
      name = "StartDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlElement(
      name = "ChapterName"
   )
   protected String chapterName;
   @XmlElement(
      name = "ParagraphName"
   )
   protected String paragraphName;
   @XmlAttribute(
      name = "lang",
      namespace = "http://www.w3.org/XML/1998/namespace",
      required = true
   )
   protected String lang;

   public FindCNKRequest() {
   }

   public String getProductName() {
      return this.productName;
   }

   public void setProductName(String value) {
      this.productName = value;
   }

   public BigInteger getVersion() {
      return this.version;
   }

   public void setVersion(BigInteger value) {
      this.version = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
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

   public String getLang() {
      return this.lang;
   }

   public void setLang(String value) {
      this.lang = value;
   }
}
