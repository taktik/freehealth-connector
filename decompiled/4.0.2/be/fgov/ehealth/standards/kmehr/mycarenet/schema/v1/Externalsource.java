package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"sources"}
)
public class Externalsource implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "source",
      required = true
   )
   protected List<Source> sources;

   public Externalsource() {
   }

   public List<Source> getSources() {
      if (this.sources == null) {
         this.sources = new ArrayList();
      }

      return this.sources;
   }
}
