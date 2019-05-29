package be.fgov.ehealth.globalmedicalfile.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CommonOutputType",
   propOrder = {"inputReference", "nipReference", "outputReference"}
)
public class CommonOutputType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InputReference"
   )
   protected String inputReference;
   @XmlElement(
      name = "NIPReference"
   )
   protected String nipReference;
   @XmlElement(
      name = "OutputReference"
   )
   protected String outputReference;

   public String getInputReference() {
      return this.inputReference;
   }

   public void setInputReference(String value) {
      this.inputReference = value;
   }

   public String getNIPReference() {
      return this.nipReference;
   }

   public void setNIPReference(String value) {
      this.nipReference = value;
   }

   public String getOutputReference() {
      return this.outputReference;
   }

   public void setOutputReference(String value) {
      this.outputReference = value;
   }
}
