package be.fgov.ehealth.hubservices.core.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import org.taktik.connector.technical.adapter.XmlTimeAdapter;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseListType",
   propOrder = {"id", "author", "date", "time", "request", "paginationinfo"}
)
public class ResponseListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected IDKMEHR id;
   @XmlElement(
      required = true
   )
   protected AuthorType author;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime date;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlTimeAdapter.class)
   @XmlSchemaType(
      name = "time"
   )
   protected DateTime time;
   @XmlElement(
      required = true
   )
   protected RequestListType request;
   protected Paginationresponseinfo paginationinfo;

   public IDKMEHR getId() {
      return this.id;
   }

   public void setId(IDKMEHR value) {
      this.id = value;
   }

   public AuthorType getAuthor() {
      return this.author;
   }

   public void setAuthor(AuthorType value) {
      this.author = value;
   }

   public DateTime getDate() {
      return this.date;
   }

   public void setDate(DateTime value) {
      this.date = value;
   }

   public DateTime getTime() {
      return this.time;
   }

   public void setTime(DateTime value) {
      this.time = value;
   }

   public RequestListType getRequest() {
      return this.request;
   }

   public void setRequest(RequestListType value) {
      this.request = value;
   }

   public Paginationresponseinfo getPaginationinfo() {
      return this.paginationinfo;
   }

   public void setPaginationinfo(Paginationresponseinfo value) {
      this.paginationinfo = value;
   }
}
