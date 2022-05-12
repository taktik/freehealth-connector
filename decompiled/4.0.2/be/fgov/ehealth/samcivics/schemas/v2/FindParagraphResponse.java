package be.fgov.ehealth.samcivics.schemas.v2;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.samcivics.type.v2.FindParagraphType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindParagraphResponseType",
   propOrder = {"amppId", "startDate", "endDate", "findParagraphs"}
)
@XmlRootElement(
   name = "FindParagraphResponse"
)
public class FindParagraphResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AmppId"
   )
   protected long amppId;
   @XmlElement(
      name = "StartDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlElement(
      name = "EndDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;
   @XmlElement(
      name = "FindParagraph",
      required = true
   )
   protected List<FindParagraphType> findParagraphs;

   public FindParagraphResponse() {
   }

   public long getAmppId() {
      return this.amppId;
   }

   public void setAmppId(long value) {
      this.amppId = value;
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

   public List<FindParagraphType> getFindParagraphs() {
      if (this.findParagraphs == null) {
         this.findParagraphs = new ArrayList();
      }

      return this.findParagraphs;
   }
}
