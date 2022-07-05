package be.fgov.ehealth.errors.service.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TraceType",
   propOrder = {"traceElements"}
)
public class TraceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TraceElement",
      required = true
   )
   protected List<String> traceElements;

   public TraceType() {
   }

   public List<String> getTraceElements() {
      if (this.traceElements == null) {
         this.traceElements = new ArrayList();
      }

      return this.traceElements;
   }
}
