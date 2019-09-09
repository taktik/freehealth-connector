package be.fgov.ehealth.standards.kmehr.schema.v1;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDEXTERNALSOURCE;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"cds", "datetime", "version", "proof"}
)
public class Source implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "cd",
      required = true
   )
   protected List<CDEXTERNALSOURCE> cds;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime datetime;
   protected String version;
   protected String proof;

   public List<CDEXTERNALSOURCE> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public DateTime getDatetime() {
      return this.datetime;
   }

   public void setDatetime(DateTime value) {
      this.datetime = value;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String value) {
      this.version = value;
   }

   public String getProof() {
      return this.proof;
   }

   public void setProof(String value) {
      this.proof = value;
   }
}
