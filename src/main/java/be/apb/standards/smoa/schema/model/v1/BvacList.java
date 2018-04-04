package be.apb.standards.smoa.schema.model.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BvacList",
   propOrder = {"bvacDocument"}
)
public class BvacList {
   @XmlElement(
      required = true
   )
   protected List<BvacDocument> bvacDocument;

   public List<BvacDocument> getBvacDocument() {
      if (this.bvacDocument == null) {
         this.bvacDocument = new ArrayList();
      }

      return this.bvacDocument;
   }
}
