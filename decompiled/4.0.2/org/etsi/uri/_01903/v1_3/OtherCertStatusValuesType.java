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
   name = "OtherCertStatusValuesType",
   propOrder = {"otherValues"}
)
public class OtherCertStatusValuesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OtherValue",
      required = true
   )
   protected List<Any> otherValues;

   public OtherCertStatusValuesType() {
   }

   public List<Any> getOtherValues() {
      if (this.otherValues == null) {
         this.otherValues = new ArrayList();
      }

      return this.otherValues;
   }
}
