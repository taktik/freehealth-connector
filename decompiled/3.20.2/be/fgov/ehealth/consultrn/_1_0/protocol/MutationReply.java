package be.fgov.ehealth.consultrn._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MutationReplyType",
   propOrder = {"header", "mutationList"}
)
@XmlRootElement(
   name = "MutationReply"
)
public class MutationReply implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Header",
      required = true
   )
   protected HeaderType header;
   @XmlElement(
      name = "MutationList",
      namespace = "urn:be:fgov:ehealth:consultRN:1_0:protocol"
   )
   protected MutationList mutationList;

   public HeaderType getHeader() {
      return this.header;
   }

   public void setHeader(HeaderType value) {
      this.header = value;
   }

   public MutationList getMutationList() {
      return this.mutationList;
   }

   public void setMutationList(MutationList value) {
      this.mutationList = value;
   }
}
