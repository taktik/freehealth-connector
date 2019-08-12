package be.ehealth.businessconnector.genericasync.domain;

import java.util.Arrays;
import java.util.List;

public final class GenericAsyncConstants {
   public static final String TACK_SUCCES = "urn:nip:tack:result:major:success";
   public static final String TACK_FAILURE = "urn:nip:tack:result:major:failure";
   public static final String CONFIRM_SOAP_ACTION = "urn:be:cin:nip:async:generic:confirm:hash";
   public static final String GET_SOAP_ACTION = "urn:be:cin:nip:async:generic:get:query";
   public static final String POST_SOAP_ACTION = "urn:be:cin:nip:async:generic:post:msg";
   public static final List<String> XSD_V1_SERVICES = Arrays.asList("invoicing", "dmg", "mediprima.invoicing");

   private GenericAsyncConstants() {
   }
}
