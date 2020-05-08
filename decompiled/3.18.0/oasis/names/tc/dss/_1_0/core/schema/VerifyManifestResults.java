package oasis.names.tc.dss._1_0.core.schema;

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
   name = "VerifyManifestResultsType",
   propOrder = {"manifestResults"}
)
@XmlRootElement(
   name = "VerifyManifestResults"
)
public class VerifyManifestResults implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ManifestResult",
      required = true
   )
   protected List<ManifestResult> manifestResults;

   public List<ManifestResult> getManifestResults() {
      if (this.manifestResults == null) {
         this.manifestResults = new ArrayList();
      }

      return this.manifestResults;
   }
}
