package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"resultMajor", "resultMinor", "resultMessage"}
)
@XmlRootElement(
   name = "Result"
)
public class Result implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ResultMajor",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String resultMajor;
   @XmlElement(
      name = "ResultMinor"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String resultMinor;
   @XmlElement(
      name = "ResultMessage"
   )
   protected InternationalStringType resultMessage;

   public Result() {
   }

   public String getResultMajor() {
      return this.resultMajor;
   }

   public void setResultMajor(String value) {
      this.resultMajor = value;
   }

   public String getResultMinor() {
      return this.resultMinor;
   }

   public void setResultMinor(String value) {
      this.resultMinor = value;
   }

   public InternationalStringType getResultMessage() {
      return this.resultMessage;
   }

   public void setResultMessage(InternationalStringType value) {
      this.resultMessage = value;
   }
}
