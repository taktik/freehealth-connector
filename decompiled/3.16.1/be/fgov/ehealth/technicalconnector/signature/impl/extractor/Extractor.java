package be.fgov.ehealth.technicalconnector.signature.impl.extractor;

import java.security.cert.X509Certificate;
import java.util.List;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;

public interface Extractor {
   boolean canExtract(KeyInfo var1);

   List<X509Certificate> extract(KeyInfo var1) throws XMLSecurityException;
}
