package be.fgov.ehealth.etee.ra.aqdr._1_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NaturalPerson",
   propOrder = {"qualities", "aaPersonalAuth"}
)
public class NaturalPerson implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Quality",
      required = true
   )
   protected List<Quality> qualities;
   @XmlElement(
      name = "AAPersonalAuth",
      required = true
   )
   protected byte[] aaPersonalAuth;

   public List<Quality> getQualities() {
      if (this.qualities == null) {
         this.qualities = new ArrayList();
      }

      return this.qualities;
   }

   public byte[] getAAPersonalAuth() {
      return this.aaPersonalAuth;
   }

   public void setAAPersonalAuth(byte[] value) {
      this.aaPersonalAuth = value;
   }
}
