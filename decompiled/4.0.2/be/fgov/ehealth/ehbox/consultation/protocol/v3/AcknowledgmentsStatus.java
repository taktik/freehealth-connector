package be.fgov.ehealth.ehbox.consultation.protocol.v3;

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
   propOrder = {"rows"}
)
public class AcknowledgmentsStatus implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Row",
      required = true
   )
   protected List<Row> rows;

   public AcknowledgmentsStatus() {
   }

   public List<Row> getRows() {
      if (this.rows == null) {
         this.rows = new ArrayList();
      }

      return this.rows;
   }
}
