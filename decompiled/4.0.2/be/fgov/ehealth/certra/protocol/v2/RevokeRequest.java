package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.RevocationContractType;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2000._09.xmldsig.Signature;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokeRequestType",
   propOrder = {"publicKeyIdentifier", "contract", "signature"}
)
@XmlRootElement(
   name = "RevokeRequest"
)
public class RevokeRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PublicKeyIdentifier",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(HexBinaryAdapter.class)
   @XmlSchemaType(
      name = "hexBinary"
   )
   protected byte[] publicKeyIdentifier;
   @XmlElement(
      name = "Contract",
      required = true
   )
   protected RevocationContractType contract;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      required = true
   )
   protected Signature signature;

   public RevokeRequest() {
   }

   public byte[] getPublicKeyIdentifier() {
      return this.publicKeyIdentifier;
   }

   public void setPublicKeyIdentifier(byte[] value) {
      this.publicKeyIdentifier = value;
   }

   public RevocationContractType getContract() {
      return this.contract;
   }

   public void setContract(RevocationContractType value) {
      this.contract = value;
   }

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }
}
