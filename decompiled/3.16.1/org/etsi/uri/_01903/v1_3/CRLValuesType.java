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
   name = "CRLValuesType",
   propOrder = {"encapsulatedCRLValues"}
)
public class CRLValuesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncapsulatedCRLValue",
      required = true
   )
   protected List<EncapsulatedPKIData> encapsulatedCRLValues;

   public List<EncapsulatedPKIData> getEncapsulatedCRLValues() {
      if (this.encapsulatedCRLValues == null) {
         this.encapsulatedCRLValues = new ArrayList();
      }

      return this.encapsulatedCRLValues;
   }
}
