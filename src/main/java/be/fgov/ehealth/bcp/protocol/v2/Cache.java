package be.fgov.ehealth.bcp.protocol.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CacheType",
   propOrder = {"key", "expiry"}
)
@XmlRootElement(
   name = "Cache"
)
public class Cache implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Key",
      required = true
   )
   protected Key key;
   @XmlElement(
      name = "Expiry",
      required = true
   )
   protected Expiry expiry;
   @XmlAttribute(
      name = "Strategy",
      required = true
   )
   protected String strategy;

   public Key getKey() {
      return this.key;
   }

   public void setKey(Key value) {
      this.key = value;
   }

   public Expiry getExpiry() {
      return this.expiry;
   }

   public void setExpiry(Expiry value) {
      this.expiry = value;
   }

   public String getStrategy() {
      return this.strategy;
   }

   public void setStrategy(String value) {
      this.strategy = value;
   }
}
