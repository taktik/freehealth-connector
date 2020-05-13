package be.fgov.ehealth.seals.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.seals.core.v1.EncodedDataType;
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
   name = "DecodeRequestType",
   propOrder = {"applicationName", "encodedDatas"}
)
@XmlRootElement(
   name = "DecodeRequest"
)
public class DecodeRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationName",
      required = true
   )
   protected String applicationName;
   @XmlElement(
      name = "EncodedData",
      required = true
   )
   protected List<EncodedDataType> encodedDatas;

   public String getApplicationName() {
      return this.applicationName;
   }

   public void setApplicationName(String value) {
      this.applicationName = value;
   }

   public List<EncodedDataType> getEncodedDatas() {
      if (this.encodedDatas == null) {
         this.encodedDatas = new ArrayList();
      }

      return this.encodedDatas;
   }
}
