package be.fgov.ehealth.technicalconnector.signature.domain;

public final class XadesOption {
   public static final String FOLLOWNESTEDMANIFEST = "followNestedManifest";
   public static final String DIGESTURI = "digestURI";
   public static final String TRANSFORMERLIST = "transformerList";
   /** @deprecated */
   @Deprecated
   public static final String TRANFORMERLIST = "transformerList";
   public static final String CANONICALIZATIONMETHODURI = "canonicalizationMethodURI";
   public static final String SIGNATUREMETHODURI = "signatureMethodURI";
   public static final String BASEURI = "baseURI";
   public static final String SIGNATURETIMESTAMP_CANONICALIZATIONMETHODURI = "SignatureTimeStampCanonicalizationMethodURI";
   public static final String SIGNATURETIMESTAMP_ALGORITHMURI = "SignatureTimestampAlgorithmURI";
   public static final String SIGNATURETIMESTAMP_PROFILE = "SignatureTimestampProfile";
   public static final String SIGNATURETIMESTAMP_CREDENTIAL = "SignatureTimestampCredential";
   public static final String SIGNATURETIMESTAMP_TSA_URL = "SignatureTimestampEndpointTimestampAuthority";
   public static final String SIGNINGTIME_CLOCK_SKEW_DURATION = "SigningTimeClockSkewDuration";
   public static final String SIGNINGTIME_CLOCK_SKEW_TIMEUNIT = "SigningTimeClockSkewTimeUnit";
   public static final String ENCAPSULATE = "encapsulate";
   public static final String ENCAPSULATE_XPATH = "encapsulate-xpath";
   public static final String ENCAPSULATE_TRANSFORMER = "encapsulate-transformer";
}
