package be.ehealth.businessconnector.genericasync.validators.impl;

import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.ConfirmResponse;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.PostResponse;
import be.ehealth.technicalconnector.validator.impl.XMLValidatorImpl;

public class GenericAsyncXmlValidatorImpl extends XMLValidatorImpl {
   private static final String XSD_GENERIC_ASYNC_GENERIC_ASYNC_XSD = "/XSD/genericAsync/GenericAsync.xsd";

   static {
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(Post.class, "/XSD/genericAsync/GenericAsync.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(PostResponse.class, "/XSD/genericAsync/GenericAsync.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(Get.class, "/XSD/genericAsync/GenericAsync.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(GetResponse.class, "/XSD/genericAsync/GenericAsync.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(Confirm.class, "/XSD/genericAsync/GenericAsync.xsd");
      XSD_FILE_LOCATION_FOR_CLASS_MAP.put(ConfirmResponse.class, "/XSD/genericAsync/GenericAsync.xsd");
   }
}
