package be.business.connector.common.module;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.gfddpp.productfilter.ProductFilter;
import be.apb.standards.gfddpp.request.configuration.GetConfigurationRequestParameters;
import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.technical.connector.utils.Crypto;
import org.taktik.connector.business.recipeprojects.core.utils.*;
import org.taktik.connector.business.recipeprojects.common.services.tipsystem.TipSystemServiceImpl;
import org.taktik.connector.business.recipeprojects.common.utils.ProductFilterSingleton;
import org.taktik.connector.business.recipeprojects.common.utils.SystemServicesUtils;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.gfddpp.services.systemservices.v2.SystemServices;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @author Liesje Demuynck.
 */
public class TipConfigModuleImpl implements TipConfigModule {

    private final static Logger LOG = Logger.getLogger(TipConfigModuleImpl.class);


    public TipConfigModuleImpl() throws IntegrationModuleException{
        ApplicationConfig.getInstance().assertInitialized();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Profiled(logFailuresSeparately = true, tag = "TipConfigModuleImpl#getLatestProductFilter", logger = "org.perf4j.TimingLogger_Executor")
    public void getLatestProductFilter() throws IntegrationModuleException {
        ApplicationConfig.getInstance().assertValidSession();

        // Get property data
        LOG.info("Loading latest productFilter");
        final PropertyHandler propertyHandler = PropertyHandler.getInstance();
        String productFilterPath;
        if (propertyHandler.hasProperty("PRODUCT_FILTER_PATH")) {
            productFilterPath = propertyHandler.getProperty("PRODUCT_FILTER_PATH");
        } else {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"));
        }

        String tipSystemId = null;
        if (propertyHandler.hasProperty("tipsystem.id")) {
            tipSystemId = propertyHandler.getProperty("tipsystem.id");
        } else {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.tipsystem.id"));
        }

        // Check what the current local latest version is
        XMLGregorianCalendar latestVersion = ConfigUtils.getLatestProductFilterVersion(productFilterPath);

        // Create request
        GetConfigurationRequestParameters productFilterReq = new GetConfigurationRequestParameters();
        productFilterReq.setVersion(latestVersion);
        productFilterReq.setNihiiPharmacyNumber(StandaloneRequestorProvider.getRequestorIdInformation());
        String productFilterReqXml;
        try {
            productFilterReqXml = JaxContextCentralizer.getInstance().toXml(GetConfigurationRequestParameters.class, productFilterReq);
        } catch (GFDDPPException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"), e);
        }

        LOG.debug("PRODUCTFILTER: " + productFilterReqXml);

        RoutedSealedRequestType productFilterSealedReq = new RoutedSealedRequestType();
        EncryptionToken etk = getETKHelper().getTIPSystem_ETK(tipSystemId).get(0);
        byte[] Request = null;
        try {
            Request = SealedProcessor.createSealedSync(etk, productFilterReqXml, "productFilterReq");
        } catch (UnsupportedEncodingException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"), e);
        }
        productFilterSealedReq.setRequestParametersSealed(Request);


        byte[] xml = null;
        // Perform the call to TIP web service and decrypt response
        RoutedSealedResponseType routedSealedResponseType;
        try {
            LOG.debug("************* Retrieve latest version of the product filter configuration ********************* ");
            routedSealedResponseType = TipSystemServiceImpl.getInstance().getProductFilter(productFilterSealedReq);
            LOG.debug("************* Latest version retrieved of the product filter configuration ********************* ");
            xml = routedSealedResponseType.getSingleMessageSealed();

        } catch (Exception e) {
            LOG.error(I18nHelper.getLabel("error.get.product.filter.failed"), e);
        }

        if (xml != null && xml.length > 0) {
            String xmlDecrypted = new String(Crypto.unseal(xml));

            XMLGregorianCalendar currentVersion;
            File newFile;
            try {
                ProductFilter productFilter = JaxContextCentralizer.getInstance().toObject(ProductFilter.class, xmlDecrypted);
                currentVersion = productFilter.getVersion();
                newFile = new File(productFilterPath + "/productfilter_v" + currentVersion.toXMLFormat().replace(':', '-') + ".xml");
                FileUtils.writeByteArrayToFile(newFile, xmlDecrypted.getBytes());
            } catch (Exception e) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"));
            }

            if (latestVersion != null && latestVersion.toGregorianCalendar().after(currentVersion.toGregorianCalendar())) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.productfilter.version", new Object[]{newFile.getAbsolutePath()}));
            }

        } else {
            LOG.debug("Received null payload from product filter.");
        }

        ProductFilterSingleton pfs = ProductFilterSingleton.getInstance();
        pfs.setProductFilterXmlFile(ConfigUtils.getLatestProductFilterFile(productFilterPath));
        pfs.reloadCache();
        LOG.info("productFilter loaded");
    }


    @Override
    @Profiled(logFailuresSeparately = true, tag = "TipConfigModuleImpl#getLatestTipConfig", logger = "org.perf4j.TimingLogger_Executor")
    public void getLatestTIPConfig() throws IntegrationModuleException {
        getLatestProductFilter();
        getLatestSystemServices();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Profiled(logFailuresSeparately = true, tag = "TipConfigModuleImpl#getLatestSystemServices", logger = "org.perf4j.TimingLogger_Executor")
    public void getLatestSystemServices() throws IntegrationModuleException {
        ApplicationConfig.getInstance().assertValidSession();

        // Get property data
        LOG.info("Loading latest systemServices");
        final PropertyHandler propertyHandler = PropertyHandler.getInstance();
        String systemServicesPath;
        if (propertyHandler.hasProperty("SYSTEM_SERVICES_PATH")) {
            systemServicesPath = propertyHandler.getProperty("SYSTEM_SERVICES_PATH");
        } else {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"));
        }

        String tipSystemId = null;
        if (propertyHandler.hasProperty("tipsystem.id")) {
            tipSystemId = propertyHandler.getProperty("tipsystem.id");
        } else {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.tipsystem.id"));
        }

        // Check what the current local latest version is
        XMLGregorianCalendar latestVersion = ConfigUtils.getLatestSystemServicesVersion(systemServicesPath);
        // Create request
        GetConfigurationRequestParameters systemServicesReq = new GetConfigurationRequestParameters();
        systemServicesReq.setVersion(latestVersion);
        systemServicesReq.setNihiiPharmacyNumber(StandaloneRequestorProvider.getRequestorIdInformation());
        String systemServicesReqXml;
        try {
            systemServicesReqXml = JaxContextCentralizer.getInstance().toXml(GetConfigurationRequestParameters.class, systemServicesReq);
        } catch (GFDDPPException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"), e);
        }

        LOG.debug("CM UNSEALED OUTGOING MESSAGE: " + systemServicesReqXml);

        EncryptionToken etk = getETKHelper().getTIPSystem_ETK(tipSystemId).get(0);
        byte[] Request = null;
        try {
            Request = SealedProcessor.createSealedSync (etk, systemServicesReqXml, "systemServicesReq");
        } catch (UnsupportedEncodingException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"), e);
        }

        RoutedSealedRequestType systemServicesSealedReq = new RoutedSealedRequestType();
        systemServicesSealedReq.setRequestParametersSealed(Request);

        // Perform the call to TIP web service and decrypt response
        RoutedSealedResponseType routedSealedResponseType;
        byte[] xml = null;
        try {
            LOG.debug("************* Retrieve latest version of the system services configuration ********************* ");
            routedSealedResponseType = TipSystemServiceImpl.getInstance().getSystemServices(systemServicesSealedReq);
            LOG.debug("************* Latest version of the system services configuration retrieved ********************* ");
            xml = routedSealedResponseType.getSingleMessageSealed();
        } catch (Exception e) {
            LOG.error(I18nHelper.getLabel("error.get.system.services.failed"), e);
        }

        if (xml != null && xml.length > 0) {
            // String xmlDecrypted = new String(unseal(xml));
            byte[] xmlDecrypted = Crypto.unseal(xml);
            // Get version of result
            XMLGregorianCalendar currentVersion;
            File newFile;
            try {
                SystemServices systemServices = JaxContextCentralizer.getInstance().toObject(SystemServices.class, xmlDecrypted);
                currentVersion = systemServices.getVersion();
                newFile = new File(systemServicesPath + "/systemservices_v" + currentVersion.toXMLFormat().replace(':', '-') + ".xml");
                FileUtils.writeByteArrayToFile(newFile, xmlDecrypted);
            } catch (Exception e) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"));
            }

            if (latestVersion != null && latestVersion.toGregorianCalendar().after(currentVersion.toGregorianCalendar())) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.systemconfiguration.version", new Object[]{newFile.getAbsolutePath()}));
            }

        } else {
            LOG.debug("Received null payload from system services.");
        }

        SystemServicesUtils ssu = SystemServicesUtils.getInstance();
        ssu.setSystemServicesXmlFile(ConfigUtils.getLatestSystemServicesFile(systemServicesPath));
        ssu.reloadCache();
        LOG.info("SystemService loaded");
    }

    private ETKHelper getETKHelper(){
        return new ETKHelper(PropertyHandler.getInstance(), EncryptionUtils.getInstance());
    }

}
