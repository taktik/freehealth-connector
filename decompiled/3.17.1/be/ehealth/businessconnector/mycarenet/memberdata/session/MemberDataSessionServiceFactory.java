package be.ehealth.businessconnector.mycarenet.memberdata.session;

import be.ehealth.businessconnector.mycarenet.memberdata.session.impl.MemberDataServiceImpl;
import be.ehealth.businessconnector.mycarenet.memberdata.session.impl.MemberDataServiceImplementationFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class MemberDataSessionServiceFactory extends AbstractSessionServiceFactory {
   private MemberDataSessionServiceFactory() {
   }

   public static MemberDataService getMemberDataSyncService() throws ConnectorException {
      return (MemberDataService)getService(MemberDataServiceImpl.class, new MemberDataServiceImplementationFactory(), new String[0]);
   }
}
