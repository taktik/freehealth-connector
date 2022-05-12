package be.fgov.ehealth.technicalconnector.signature.impl.xades.domain;

import be.ehealth.technicalconnector.utils.MarshallerHelper;
import org.etsi.uri._01903.v1_3.QualifyingProperties;
import org.w3c.dom.Document;

public class QualifyingPropertiesBuilder {
   private static MarshallerHelper<QualifyingProperties, QualifyingProperties> marshaller = new MarshallerHelper(QualifyingProperties.class, QualifyingProperties.class);
   private SignedPropertiesBuilder signedProps = new SignedPropertiesBuilder();
   private UnsignedPropertiesBuilder unsignedProps = new UnsignedPropertiesBuilder();

   public QualifyingPropertiesBuilder() {
   }

   public SignedPropertiesBuilder getSignedProps() {
      return this.signedProps;
   }

   public UnsignedPropertiesBuilder getUnsignedProps() {
      return this.unsignedProps;
   }

   public Document buildBeforeSigningAsDocument() {
      return marshaller.toDocument(this.buildBeforeSigning());
   }

   private QualifyingProperties buildBeforeSigning() {
      QualifyingProperties qualProps = new QualifyingProperties();
      qualProps.setSignedProperties(this.getSignedProps().build());
      return qualProps;
   }
}
