package be.fgov.ehealth.consultrn.ssinhistory.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RelatedSsinsType",
   propOrder = {"relatedSsins"}
)
public class RelatedSsinsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RelatedSsin",
      required = true
   )
   protected List<String> relatedSsins;

   public List<String> getRelatedSsins() {
      if (this.relatedSsins == null) {
         this.relatedSsins = new ArrayList();
      }

      return this.relatedSsins;
   }
}
