package be.fgov.ehealth.dics.protocol.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v3.virtual.common.CommentedClassificationKeyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultCommentedClassificationType",
   propOrder = {"title", "content", "posologyNote", "url"}
)
@XmlSeeAlso({ConsultCommentedClassificationTreeType.class})
public class ConsultCommentedClassificationType extends CommentedClassificationKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Title"
   )
   protected ConsultTextType title;
   @XmlElement(
      name = "Content"
   )
   protected ConsultTextType content;
   @XmlElement(
      name = "PosologyNote",
      required = true
   )
   protected ConsultTextType posologyNote;
   @XmlElement(
      name = "Url"
   )
   protected String url;
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

   public ConsultCommentedClassificationType() {
   }

   public ConsultTextType getTitle() {
      return this.title;
   }

   public void setTitle(ConsultTextType value) {
      this.title = value;
   }

   public ConsultTextType getContent() {
      return this.content;
   }

   public void setContent(ConsultTextType value) {
      this.content = value;
   }

   public ConsultTextType getPosologyNote() {
      return this.posologyNote;
   }

   public void setPosologyNote(ConsultTextType value) {
      this.posologyNote = value;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String value) {
      this.url = value;
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
