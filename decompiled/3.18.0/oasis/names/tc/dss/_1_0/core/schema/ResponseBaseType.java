package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseBaseType",
   propOrder = {"result", "optionalOutputs"}
)
@XmlSeeAlso({SignResponse.class})
public class ResponseBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Result",
      required = true
   )
   protected Result result;
   @XmlElement(
      name = "OptionalOutputs"
   )
   protected AnyType optionalOutputs;
   @XmlAttribute(
      name = "RequestID"
   )
   protected String requestID;
   @XmlAttribute(
      name = "Profile",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String profile;

   public Result getResult() {
      return this.result;
   }

   public void setResult(Result value) {
      this.result = value;
   }

   public AnyType getOptionalOutputs() {
      return this.optionalOutputs;
   }

   public void setOptionalOutputs(AnyType value) {
      this.optionalOutputs = value;
   }

   public String getRequestID() {
      return this.requestID;
   }

   public void setRequestID(String value) {
      this.requestID = value;
   }

   public String getProfile() {
      return this.profile;
   }

   public void setProfile(String value) {
      this.profile = value;
   }
}
