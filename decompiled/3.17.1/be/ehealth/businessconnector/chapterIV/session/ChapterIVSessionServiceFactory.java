package be.ehealth.businessconnector.chapterIV.session;

import be.ehealth.businessconnector.chapterIV.session.impl.ChapterIVServiceImpl;
import be.ehealth.businessconnector.chapterIV.session.impl.ChapterIVServiceImplementationFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class ChapterIVSessionServiceFactory extends AbstractSessionServiceFactory {
   private ChapterIVSessionServiceFactory() {
   }

   public static ChapterIVService getChapterIVService() throws ConnectorException {
      return (ChapterIVService)getService(ChapterIVServiceImpl.class, new ChapterIVServiceImplementationFactory(), new String[0]);
   }
}
