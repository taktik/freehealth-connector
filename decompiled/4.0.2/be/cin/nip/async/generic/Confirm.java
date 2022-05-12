package be.cin.nip.async.generic;

import be.cin.mycarenet.esb.common.v2.OrigineType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"origin", "msgHashValues", "msgRefValues", "tAckContents", "tAckReferences"}
)
@XmlRootElement(
   name = "confirm"
)
public class Confirm implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Origin",
      required = true
   )
   protected OrigineType origin;
   @XmlList
   @XmlElement(
      name = "MsgHashValues"
   )
   protected List<byte[]> msgHashValues;
   @XmlList
   @XmlElement(
      name = "MsgRefValues"
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected List<String> msgRefValues;
   @XmlList
   @XmlElement(
      name = "TAckContents"
   )
   protected List<byte[]> tAckContents;
   @XmlList
   @XmlElement(
      name = "TAckReferences"
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected List<String> tAckReferences;

   public Confirm() {
   }

   public OrigineType getOrigin() {
      return this.origin;
   }

   public void setOrigin(OrigineType value) {
      this.origin = value;
   }

   public List<byte[]> getMsgHashValues() {
      if (this.msgHashValues == null) {
         this.msgHashValues = new ArrayList();
      }

      return this.msgHashValues;
   }

   public List<String> getMsgRefValues() {
      if (this.msgRefValues == null) {
         this.msgRefValues = new ArrayList();
      }

      return this.msgRefValues;
   }

   public List<byte[]> getTAckContents() {
      if (this.tAckContents == null) {
         this.tAckContents = new ArrayList();
      }

      return this.tAckContents;
   }

   public List<String> getTAckReferences() {
      if (this.tAckReferences == null) {
         this.tAckReferences = new ArrayList();
      }

      return this.tAckReferences;
   }
}
