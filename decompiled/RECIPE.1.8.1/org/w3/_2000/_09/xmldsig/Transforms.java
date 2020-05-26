package org.w3._2000._09.xmldsig;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TransformsType",
   propOrder = {"transforms"}
)
@XmlRootElement(
   name = "Transforms"
)
public class Transforms {
   @XmlElement(
      name = "Transform",
      required = true
   )
   protected List<Transform> transforms;

   public List<Transform> getTransforms() {
      if (this.transforms == null) {
         this.transforms = new ArrayList();
      }

      return this.transforms;
   }
}
