package be.business.connector.gfddpp.utils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class SignatureGenerator {
   public static byte[] sign(String text, PrivateKey prvKey, String sigAlg) throws Exception {
      Signature sig = Signature.getInstance(sigAlg);
      sig.initSign(prvKey);
      sig.update(text.getBytes());
      return sig.sign();
   }

   public static boolean verify(String originalText, PublicKey pubKey, String sigAlg, byte[] sigbytes) throws Exception {
      Signature sig = Signature.getInstance(sigAlg);
      sig.initVerify(pubKey);
      sig.update(originalText.getBytes());
      return sig.verify(sigbytes);
   }
}
