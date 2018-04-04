package be.cin.nip.async.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Responses",
   propOrder = {"msgResponses", "tAckResponses"}
)
public class Responses implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MsgResponse"
   )
   protected List<MsgResponse> msgResponses;
   @XmlElement(
      name = "TAckResponse"
   )
   protected List<TAckResponse> tAckResponses;
   @XmlAttribute(
      name = "MsgCount",
      required = true
   )
   protected int msgCount;
   @XmlAttribute(
      name = "TAckCount",
      required = true
   )
   protected int tAckCount;

   public List<MsgResponse> getMsgResponses() {
      if (this.msgResponses == null) {
         this.msgResponses = new ArrayList();
      }

      return this.msgResponses;
   }

   public List<TAckResponse> getTAckResponses() {
      if (this.tAckResponses == null) {
         this.tAckResponses = new ArrayList();
      }

      return this.tAckResponses;
   }

   public int getMsgCount() {
      return this.msgCount;
   }

   public void setMsgCount(int value) {
      this.msgCount = value;
   }

   public int getTAckCount() {
      return this.tAckCount;
   }

   public void setTAckCount(int value) {
      this.tAckCount = value;
   }
}
