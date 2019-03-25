package be.ehealth.businessconnector.chapterIV.utils;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMMAAvalues;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.ContentType;
import be.fgov.ehealth.standards.kmehr.schema.v1.FolderType;
import be.fgov.ehealth.standards.kmehr.schema.v1.ItemType;
import be.fgov.ehealth.standards.kmehr.schema.v1.TransactionType;
import java.util.Iterator;
import java.util.List;
import org.joda.time.DateTime;

public final class FolderTypeUtils {
   public static DateTime retrieveConsultationStartDateOrAgreementStartDate(FolderType folder) {
      if (folder != null) {
         List<ItemType> items = ((TransactionType)folder.getTransactions().get(0)).getItem();
         Iterator i$ = items.iterator();

         while(i$.hasNext()) {
            ItemType itemType = (ItemType)i$.next();
            if (CDITEMMAAvalues.CONSULTATIONSTARTDATE.value().equals(((CDITEM)itemType.getCds().get(0)).getValue()) && ((CDITEM)itemType.getCds().get(0)).getS() == CDITEMschemes.CD_ITEM_MAA) {
               return ((ContentType)itemType.getContents().get(0)).getDate();
            }

            if (CDITEMMAAvalues.AGREEMENTSTARTDATE.value().equals(((CDITEM)itemType.getCds().get(0)).getValue()) && ((CDITEM)itemType.getCds().get(0)).getS() == CDITEMschemes.CD_ITEM_MAA) {
               return ((ContentType)itemType.getContents().get(0)).getDate();
            }
         }
      }

      return null;
   }
}
