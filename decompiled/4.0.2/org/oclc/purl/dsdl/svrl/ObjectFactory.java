package org.oclc.purl.dsdl.svrl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _Text_QNAME = new QName("http://purl.oclc.org/dsdl/svrl", "text");

   public ObjectFactory() {
   }

   public SchematronOutput createSchematronOutput() {
      return new SchematronOutput();
   }

   public Ns createNs() {
      return new Ns();
   }

   public NsPrefixInAttributeValues createNsPrefixInAttributeValues() {
      return new NsPrefixInAttributeValues();
   }

   public ActivePattern createActivePattern() {
      return new ActivePattern();
   }

   public FiredRule createFiredRule() {
      return new FiredRule();
   }

   public FailedAssert createFailedAssert() {
      return new FailedAssert();
   }

   public DiagnosticReference createDiagnosticReference() {
      return new DiagnosticReference();
   }

   public SuccessfulReport createSuccessfulReport() {
      return new SuccessfulReport();
   }

   @XmlElementDecl(
      namespace = "http://purl.oclc.org/dsdl/svrl",
      name = "text"
   )
   public JAXBElement<String> createText(String value) {
      return new JAXBElement(_Text_QNAME, String.class, (Class)null, value);
   }
}
