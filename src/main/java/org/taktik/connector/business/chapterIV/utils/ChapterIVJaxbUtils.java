package org.taktik.connector.business.chapterIV.utils;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.MarshallerHelper;
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrresponse;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import java.io.InputStream;

/** @deprecated */
@Deprecated
public final class ChapterIVJaxbUtils {
   private static MarshallerHelper<FolderType, FolderType> folderTypeMarshaller = new MarshallerHelper(FolderType.class, FolderType.class);
   private static MarshallerHelper<Kmehrresponse, Kmehrresponse> kmehrResponseMarshallerHelper = new MarshallerHelper(Kmehrresponse.class, Kmehrresponse.class);

   /** @deprecated */
   @Deprecated
   public static FolderType parseFolderType(InputStream folderTypeXmlInputStream) throws TechnicalConnectorException {
      return (FolderType)folderTypeMarshaller.toObject(folderTypeXmlInputStream);
   }

   /** @deprecated */
   @Deprecated
   public static Kmehrresponse parseKmehrResponse(byte[] bytes) throws TechnicalConnectorException {
      return bytes != null && bytes.length > 0 ? (Kmehrresponse)kmehrResponseMarshallerHelper.toObject(bytes) : null;
   }

   /** @deprecated */
   @Deprecated
   public static String marshal(Object obj) {
      MarshallerHelper marshallerHelper = new MarshallerHelper(obj.getClass(), obj.getClass());
      return marshallerHelper.toString(obj);
   }
}
