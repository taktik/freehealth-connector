using System;
using System.Runtime.InteropServices;
using be.business.connector.core.exceptions;
using be.business.connector.common;
using be.business.connector.core.utils;
using be.business.connector.session;

namespace RecipePrescriberCom
{
    [InterfaceType(ComInterfaceType.InterfaceIsIDispatch)]
    public interface ICommonModule
    {
        String createSession(String sessionType, String inss, String password, Boolean isMock);
        void init(String config, String configValidation);

        void setOldSystemKeystoreProperties(string systemKeystorePassword, string systemKeystorePath, string systemKeystoreDirectory, string systemKeystoreNIHIIPHARMACYCBE);
        void setProperty(string key, string value);
        void setSystemKeystoreProperties(string systemKeystorePassword, string systemKeystorePath, string systemKeystoreDirectory, string systemKeystoreNIHIIPHARMACYCBE);
        void setSystemProperty(string key, string value);
    }

    [Guid("c9b6474b-7731-4ad9-a56f-e41ae541ef13")]
    [ClassInterface(ClassInterfaceType.None)]
    [ProgId("Common.CommonModule")]
    public class CommonModule : ICommonModule
    {
        public CommonModule()
        {
            //Force the loading of the be-connector-executor.dll, otherwise we will get a eid-not present exception when this module gets called.
            //It's due to a classloading issue.
            //ikvm.runtime.Startup.addBootClassPathAssemby(Assembly.Load("be-business-connector-common"));
            //ikvm.runtime.Startup.addBootClassPathAssemby(Assembly.Load("be-business-connector-core"));
            //ikvm.runtime.Startup.addBootClassPathAssemby(Assembly.Load("be-business-connector-gfddpp"));
            //ikvm.runtime.Startup.addBootClassPathAssemby(Assembly.Load("be-business-connector-mycarenet"));
            //ikvm.runtime.Startup.addBootClassPathAssemby(Assembly.Load("be-business-connector-projects-one"));
            //ikvm.runtime.Startup.addBootClassPathAssemby(Assembly.Load("be-business-connector-recipe"));
        }

        String errorMessage;


        public String getErrorMessage()
        {
            return errorMessage;
        }

        public void init(String config, String configValidation)
        {
            ApplicationConfig.getInstance().initialize(config, configValidation);
        }

        public String createSession(String sessionType, String inss, String password, Boolean isMock)
        {
            be.ehealth.technicalconnector.session.SessionItem sessionItem;
            String resultSession;
            try
            {
                if (isMock)
                {
                    resultSession = "Create session not supported for mock"; ;
                }
                else
                {
                    sessionItem = SessionUtil.createSession(SessionType.valueOf(sessionType), PropertyHandler.getInstance().getPropertiesCopy(), inss, password);
                    resultSession = SAML10Converter.toXMLString(sessionItem.getSAMLToken().getAssertion());
                }
                return resultSession;

            }
            catch (IntegrationModuleException e)
            {
                errorMessage = e.getMessage();
                throw e;
            }
        }

        public void setProperty(String key, String value)
        {
            try
            {
                ApplicationConfig.getInstance().setProperty(key, value);

            }
            catch (IntegrationModuleException e)
            {
                errorMessage = e.getMessage();
                throw e;
            }
        }

        public void setSystemProperty(String key, String value)
        {
            try
            {
                ApplicationConfig.getInstance().setSystemProperty(key, value);

            }
            catch (IntegrationModuleException e)
            {
                errorMessage = e.getMessage();
                throw e;
            }
        }

        public void setSystemKeystoreProperties(String systemKeystorePassword, String systemKeystorePath, String systemKeystoreDirectory, String systemKeystoreNIHIIPHARMACYCBE)
        {
            try
            {
                ApplicationConfig.getInstance().setSystemKeystoreProperties(systemKeystorePassword, systemKeystorePath, systemKeystoreDirectory, systemKeystoreNIHIIPHARMACYCBE);
            }
            catch (IntegrationModuleException e)
            {
                errorMessage = e.getMessage();
                throw e;
            }

        }

        public void setOldSystemKeystoreProperties(String systemKeystorePassword, String systemKeystorePath, String systemKeystoreDirectory, String systemKeystoreNIHIIPHARMACYCBE)
        {
            try
            {
                ApplicationConfig.getInstance().setOldSystemKeystoreProperties(systemKeystorePassword, systemKeystorePath, systemKeystoreDirectory, systemKeystoreNIHIIPHARMACYCBE);
            }
            catch (IntegrationModuleException e)
            {
                errorMessage = e.getMessage();
                throw e;
            }
        }

    }
}