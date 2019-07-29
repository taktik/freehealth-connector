package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetAllEhboxesMessagesListRequestType",
   propOrder = {"source", "startIndex", "endIndex"}
)
@XmlRootElement(
   name = "GetAllEhboxesMessagesListRequest"
)
public class GetAllEhboxesMessagesListRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Source",
      required = true,
      defaultValue = "INBOX"
   )
   protected String source;
   @XmlElement(
      name = "StartIndex",
      defaultValue = "1"
   )
   protected Integer startIndex;
   @XmlElement(
      name = "EndIndex",
      defaultValue = "100"
   )
   protected Integer endIndex;

   public String getSource() {
      return this.source;
   }

   public void setSource(String value) {
      this.source = value;
   }

   public Integer getStartIndex() {
      return this.startIndex;
   }

   public void setStartIndex(Integer value) {
      this.startIndex = value;
   }

   public Integer getEndIndex() {
      return this.endIndex;
   }

   public void setEndIndex(Integer value) {
      this.endIndex = value;
   }
}
