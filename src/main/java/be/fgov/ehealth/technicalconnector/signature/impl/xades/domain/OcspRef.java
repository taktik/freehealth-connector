package be.fgov.ehealth.technicalconnector.signature.impl.xades.domain;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.xml.security.utils.RFC2253Parser;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.RespID;
import org.etsi.uri._01903.v1_3.EncapsulatedPKIData;
import org.etsi.uri._01903.v1_3.OCSPIdentifierType;
import org.etsi.uri._01903.v1_3.OCSPRefType;
import org.etsi.uri._01903.v1_3.ResponderIDType;
import org.joda.time.DateTime;

class OcspRef extends Ref {
   private BasicOCSPResp ocsp;
   private byte[] ocspEncoded;

   OcspRef(byte[] inOcspEncoded) {
      this.ocspEncoded = ArrayUtils.clone(inOcspEncoded);

      try {
         this.ocsp = (BasicOCSPResp)(new OCSPResp(this.ocspEncoded)).getResponseObject();
      } catch (Exception var3) {
         throw new IllegalArgumentException(var3);
      }
   }

   public byte[] getEncoded() {
      return ArrayUtils.clone(this.ocspEncoded);
   }

   public List<X509Certificate> getAssociatedCertificates() {
      List<X509Certificate> result = new ArrayList();
      X509CertificateHolder[] arr$ = this.ocsp.getCerts();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         X509CertificateHolder certificateHolder = arr$[i$];

         try {
            result.add((new JcaX509CertificateConverter()).setProvider("BC").getCertificate(certificateHolder));
         } catch (CertificateException var7) {
            throw new IllegalArgumentException(var7);
         }
      }

      return result;
   }

   private DateTime getProducedAt() {
      return new DateTime(this.ocsp.getProducedAt().getTime());
   }

   private String getResponderIdByName() {
      RespID responderId = this.ocsp.getResponderId();
      ResponderID responderIdAsASN1Object = responderId.toASN1Object();
      DERTaggedObject derTaggedObject = (DERTaggedObject)responderIdAsASN1Object.toASN1Primitive();
      if (2 == derTaggedObject.getTagNo()) {
         return null;
      } else {
         ASN1Primitive derObject = derTaggedObject.getObject();
         X500Name name = X500Name.getInstance(derObject);
         return RFC2253Parser.normalize(name.toString());
      }
   }

   private byte[] getResponderIdByKey() {
      ResponderID responderID = this.ocsp.getResponderId().toASN1Object();
      DERTaggedObject derTaggedObject = (DERTaggedObject)responderID.toASN1Primitive();
      if (2 == derTaggedObject.getTagNo()) {
         ASN1OctetString keyHashOctetString = (ASN1OctetString)derTaggedObject.getObject();
         return keyHashOctetString.getOctets();
      } else {
         return new byte[0];
      }
   }

   public OCSPRefType convertToXadesOCSPRef() {
      OCSPRefType refType = new OCSPRefType();
      refType.setDigestAlgAndValue(this.getDigestAlgAndValue());
      OCSPIdentifierType ocspIdentifier = new OCSPIdentifierType();
      refType.setOCSPIdentifier(ocspIdentifier);
      ocspIdentifier.setProducedAt(this.getProducedAt());
      ResponderIDType responderId = new ResponderIDType();
      responderId.setByName(this.getResponderIdByName());
      responderId.setByKey(this.getResponderIdByKey());
      ocspIdentifier.setResponderID(responderId);
      return refType;
   }

   public EncapsulatedPKIData convertToXadesEncapsulatedPKIData() {
      EncapsulatedPKIData data = new EncapsulatedPKIData();
      data.setValue(this.ocspEncoded);
      return data;
   }
}
