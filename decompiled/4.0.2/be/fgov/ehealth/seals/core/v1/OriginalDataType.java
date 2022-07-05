package be.fgov.ehealth.seals.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OriginalDataType",
   propOrder = {"id", "algorithmCode", "inputData"}
)
public class OriginalDataType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Id",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlElement(
      name = "AlgorithmCode",
      required = true
   )
   protected String algorithmCode;
   @XmlElement(
      name = "InputData",
      required = true
   )
   protected String inputData;

   public OriginalDataType() {
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getAlgorithmCode() {
      return this.algorithmCode;
   }

   public void setAlgorithmCode(String value) {
      this.algorithmCode = value;
   }

   public String getInputData() {
      return this.inputData;
   }

   public void setInputData(String value) {
      this.inputData = value;
   }
}
