package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OCSPValuesType",
   propOrder = {"encapsulatedOCSPValues"}
)
public class OCSPValuesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncapsulatedOCSPValue",
      required = true
   )
   protected List<EncapsulatedPKIData> encapsulatedOCSPValues;

   public List<EncapsulatedPKIData> getEncapsulatedOCSPValues() {
      if (this.encapsulatedOCSPValues == null) {
         this.encapsulatedOCSPValues = new ArrayList();
      }

      return this.encapsulatedOCSPValues;
   }
}
