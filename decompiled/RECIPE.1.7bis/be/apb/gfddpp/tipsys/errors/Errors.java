package be.apb.gfddpp.tipsys.errors;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "errors",
   propOrder = {"error"}
)
public class Errors {
   @XmlElement(
      required = true
   )
   protected List<Error> error;

   public List<Error> getError() {
      if (this.error == null) {
         this.error = new ArrayList();
      }

      return this.error;
   }
}
