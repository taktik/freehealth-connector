package be.cin.nip.async.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenericResponseListType",
   propOrder = {"genericResponses"}
)
@XmlRootElement(
   name = "GenericResponseList"
)
public class GenericResponseList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GenericResponse",
      required = true
   )
   protected List<GenericResponse> genericResponses;

   public GenericResponseList() {
   }

   public List<GenericResponse> getGenericResponses() {
      if (this.genericResponses == null) {
         this.genericResponses = new ArrayList();
      }

      return this.genericResponses;
   }
}
