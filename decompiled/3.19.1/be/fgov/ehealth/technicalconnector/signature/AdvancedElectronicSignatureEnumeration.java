package be.fgov.ehealth.technicalconnector.signature;

public enum AdvancedElectronicSignatureEnumeration {
   XML("xmldsig", "XML digital signature"),
   XAdES("XAdES", "Basic Electronic Signature"),
   XAdES_T("XAdES-T", "Electronic Signature with Time"),
   XAdES_C("XAdES-C", "Electronic Signature with Complete Validation Data References"),
   XAdES_X("XAdES-X", "Extended Signatures with Time Forms"),
   XAdES_X_L("XAdES-X-L", "Extended Long Electronic Signatures with Time"),
   XAdES_A("XAdES-A", "Archival Electronic Signatures"),
   CMS("CMS", "PKCS#7 Cryptographic Message Syntax"),
   CAdES("CAdES", "Basic Electronic Signature"),
   CAdES_T("CAdES-T", "Electronic Signature with Time"),
   CAdES_C("CAdES-C", "Electronic Signature with Complete Validation Data References"),
   CAdES_X("CAdES-X", "Extended Signatures with Time Forms"),
   CAdES_X_L("CAdES-X-L", "Extended Long Electronic Signatures with Time"),
   CAdES_A("CAdES-A", "Archival Electronic Signatures");

   private String nickname;
   private String title;

   private AdvancedElectronicSignatureEnumeration(String nickname, String title) {
      this.nickname = nickname;
      this.title = title;
   }

   public String getNickname() {
      return this.nickname;
   }

   public String getTitle() {
      return this.title;
   }
}
