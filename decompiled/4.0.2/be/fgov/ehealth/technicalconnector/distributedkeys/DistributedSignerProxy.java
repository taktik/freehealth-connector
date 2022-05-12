package be.fgov.ehealth.technicalconnector.distributedkeys;

import java.io.Serializable;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Set;

public interface DistributedSignerProxy extends Serializable {
   byte[] sign(byte[] var1, String var2, String var3) throws SignatureException;

   Set<String> getAliases();

   List<X509Certificate> getCertificateChain(String var1);

   String getAlgorithm(String var1);
}
