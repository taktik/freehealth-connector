package be.apb.gfddpp.common.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "rowSet",
   propOrder = {"row"}
)
public class RowSet {
   @XmlElement(
      required = true
   )
   protected List<TipSystemAudit> row;

   public List<TipSystemAudit> getRow() {
      if (this.row == null) {
         this.row = new ArrayList();
      }

      return this.row;
   }
}
