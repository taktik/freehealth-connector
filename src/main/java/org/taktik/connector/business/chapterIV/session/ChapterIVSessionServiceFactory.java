package org.taktik.connector.business.chapterIV.session;

import org.taktik.connector.business.chapterIV.session.impl.ChapterIVServiceImpl;
import org.taktik.connector.business.chapterIV.session.impl.ChapterIVServiceImplementationFactory;
import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.session.AbstractSessionServiceFactory;

public final class ChapterIVSessionServiceFactory extends AbstractSessionServiceFactory {
   public static ChapterIVService getChapterIVService() throws ConnectorException {
      return (ChapterIVService)getService(ChapterIVServiceImpl.class, new ChapterIVServiceImplementationFactory(), new String[0]);
   }
}
