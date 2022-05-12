package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MunicipalityType",
   propOrder = {"zipCode", "nsiCode", "descriptions"}
)
@XmlRootElement(
   name = "Municipality"
)
public class Municipality implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ZipCode"
   )
   protected BigInteger zipCode;
   @XmlElement(
      name = "NSICode"
   )
   protected String nsiCode;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected List<Description> descriptions;

   public Municipality() {
   }

   public BigInteger getZipCode() {
      return this.zipCode;
   }

   public void setZipCode(BigInteger value) {
      this.zipCode = value;
   }

   public String getNSICode() {
      return this.nsiCode;
   }

   public void setNSICode(String value) {
      this.nsiCode = value;
   }

   public List<Description> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
