package be.fgov.ehealth.consultrn._1_0.core;

import be.fgov.ehealth.commons._1_0.core.LocalisedString;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ObtainementReasonType",
   propOrder = {"code", "descriptions"}
)
public class ObtainementReasonType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected BigInteger code;
   @XmlElement(
      name = "Description"
   )
   protected List<LocalisedString> descriptions;

   public ObtainementReasonType() {
   }

   public BigInteger getCode() {
      return this.code;
   }

   public void setCode(BigInteger value) {
      this.code = value;
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
