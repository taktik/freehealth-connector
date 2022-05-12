package be.fgov.ehealth.monitoring.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MonitoringType",
   propOrder = {"schema", "results"}
)
public class MonitoringType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Schema",
      required = true
   )
   protected SchemaType schema;
   @XmlElement(
      name = "Results",
      required = true
   )
   protected List<ResultsType> results;

   public MonitoringType() {
   }

   public SchemaType getSchema() {
      return this.schema;
   }

   public void setSchema(SchemaType value) {
      this.schema = value;
   }

   public List<ResultsType> getResults() {
      if (this.results == null) {
         this.results = new ArrayList();
      }

      return this.results;
   }
}
