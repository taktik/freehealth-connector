package org.w3._2001._04.xmlenc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.Transform;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TransformsType",
   propOrder = {"transforms"}
)
public class TransformsType {
   @XmlElement(
      name = "Transform",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
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
