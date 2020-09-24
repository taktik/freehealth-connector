package org.w3._2000._09.xmldsig_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TransformsType",
   propOrder = {"transform"}
)
public class TransformsType {
   @XmlElement(
      name = "Transform",
      required = true
   )
   protected List<TransformType> transform;

   public List<TransformType> getTransform() {
      if (this.transform == null) {
         this.transform = new ArrayList();
      }

      return this.transform;
   }
}
