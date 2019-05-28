package org.w3._2000._09.xmldsig;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DSAKeyValueType",
   propOrder = {"p", "q", "g", "y", "j", "seed", "pgenCounter"}
)
@XmlRootElement(
   name = "DSAKeyValue"
)
public class DSAKeyValue implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "P"
   )
   protected byte[] p;
   @XmlElement(
      name = "Q"
   )
   protected byte[] q;
   @XmlElement(
      name = "G"
   )
   protected byte[] g;
   @XmlElement(
      name = "Y",
      required = true
   )
   protected byte[] y;
   @XmlElement(
      name = "J"
   )
   protected byte[] j;
   @XmlElement(
      name = "Seed"
   )
   protected byte[] seed;
   @XmlElement(
      name = "PgenCounter"
   )
   protected byte[] pgenCounter;

   public byte[] getP() {
      return this.p;
   }

   public void setP(byte[] value) {
      this.p = value;
   }

   public byte[] getQ() {
      return this.q;
   }

   public void setQ(byte[] value) {
      this.q = value;
   }

   public byte[] getG() {
      return this.g;
   }

   public void setG(byte[] value) {
      this.g = value;
   }

   public byte[] getY() {
      return this.y;
   }

   public void setY(byte[] value) {
      this.y = value;
   }

   public byte[] getJ() {
      return this.j;
   }

   public void setJ(byte[] value) {
      this.j = value;
   }

   public byte[] getSeed() {
      return this.seed;
   }

   public void setSeed(byte[] value) {
      this.seed = value;
   }

   public byte[] getPgenCounter() {
      return this.pgenCounter;
   }

   public void setPgenCounter(byte[] value) {
      this.pgenCounter = value;
   }
}
