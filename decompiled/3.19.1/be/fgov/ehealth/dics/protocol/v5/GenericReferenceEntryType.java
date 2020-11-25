package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenericReferenceEntryType",
   propOrder = {"fields"}
)
public class GenericReferenceEntryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Field",
      required = true
   )
   protected List<AdditionalFieldsType> fields;

   public List<AdditionalFieldsType> getFields() {
      if (this.fields == null) {
         this.fields = new ArrayList();
      }

      return this.fields;
   }
}
