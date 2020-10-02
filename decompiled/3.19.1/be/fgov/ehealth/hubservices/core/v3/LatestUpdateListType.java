package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LatestUpdateListType",
   propOrder = {"latestupdates"}
)
public class LatestUpdateListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "latestupdate"
   )
   protected List<Latestupdate> latestupdates;

   public List<Latestupdate> getLatestupdates() {
      if (this.latestupdates == null) {
         this.latestupdates = new ArrayList();
      }

      return this.latestupdates;
   }
}
