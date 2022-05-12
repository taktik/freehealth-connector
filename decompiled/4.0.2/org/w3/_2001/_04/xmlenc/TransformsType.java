package org.w3._2001._04.xmlenc;

import java.io.Serializable;
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
public class TransformsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Transform",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      required = true
   )
   protected List<Transform> transforms;

   public TransformsType() {
   }

   public List<Transform> getTransforms() {
      if (this.transforms == null) {
         this.transforms = new ArrayList();
      }

      return this.transforms;
   }
}
