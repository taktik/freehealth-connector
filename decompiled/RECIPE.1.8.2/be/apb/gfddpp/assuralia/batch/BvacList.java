package be.apb.gfddpp.assuralia.batch;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"bvac"}
)
@XmlRootElement(
   name = "BvacList"
)
public class BvacList {
   @XmlElement(
      name = "Bvac",
      required = true
   )
   protected List<Bvac> bvac;

   public List<Bvac> getBvac() {
      if (this.bvac == null) {
         this.bvac = new ArrayList();
      }

      return this.bvac;
   }
}
