package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AnyType",
   propOrder = {"anies"}
)
public class AnyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAnyElement
   protected List<Element> anies;

   public List<Element> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
   }
}
