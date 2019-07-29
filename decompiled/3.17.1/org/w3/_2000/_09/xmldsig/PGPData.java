package org.w3._2000._09.xmldsig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PGPDataType",
   propOrder = {"pgpKeyID", "pgpKeyPacket", "anies"}
)
@XmlRootElement(
   name = "PGPData"
)
public class PGPData implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PGPKeyID"
   )
   protected byte[] pgpKeyID;
   @XmlElement(
      name = "PGPKeyPacket"
   )
   protected byte[] pgpKeyPacket;
   @XmlAnyElement
   protected List<Element> anies;

   public byte[] getPGPKeyID() {
      return this.pgpKeyID;
   }

   public void setPGPKeyID(byte[] value) {
      this.pgpKeyID = value;
   }

   public byte[] getPGPKeyPacket() {
      return this.pgpKeyPacket;
   }

   public void setPGPKeyPacket(byte[] value) {
      this.pgpKeyPacket = value;
   }

   public List<Element> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
   }
}
