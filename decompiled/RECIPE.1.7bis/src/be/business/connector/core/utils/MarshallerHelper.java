package org.taktik.connector.business.recipeprojects.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.Key;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class MarshallerHelper<X, Y> {

    private Unmarshaller unmarshaller;
    private Marshaller marshaller;

    public MarshallerHelper(Class<X> unmarshallClass, Class<Y> marshallClass) {
        try {
            unmarshaller = JAXBContext.newInstance(unmarshallClass).createUnmarshaller();
            marshaller = JAXBContext.newInstance(marshallClass).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new IllegalArgumentException("JAXBException " + e);
        }
    }

    public byte[] toXMLByteArray(Y data) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            marshaller.marshal(data, bos);
            bos.close();
            MessageDumper.getInstance().dump(bos, data.getClass().getSimpleName(), MessageDumper.OUT);
            return bos.toByteArray();
        } catch (JAXBException e) {
            throw new IllegalArgumentException("JAXBException " + e);
        } catch (IOException e) {
            throw new IllegalArgumentException("IOException " + e);
        }

    }

    @SuppressWarnings("unchecked")
    public X toObject(byte[] data) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            X result = (X) unmarshaller.unmarshal(bis);
            bis.close();
            MessageDumper.getInstance().dump(data, result.getClass().getSimpleName(), MessageDumper.IN);
            return result;
        } catch (JAXBException e) {
            throw new IllegalArgumentException("JAXBException " + e);
        } catch (IOException e) {
            throw new IllegalArgumentException("IOException " + e);
        }
    }

    public X toObject(InputStream inputStream) {
        byte[] data = IOUtils.getBytes(inputStream);
        return toObject(data);
    }

    public String marsh(Y data) throws JAXBException {

        // Create a stringWriter to hold the XML
        StringWriter stringWriter = new StringWriter();

        // Marshal the javaObject and write the XML to the stringWriter
        marshaller.marshal(data, stringWriter);

        return stringWriter.toString();
    }

    @SuppressWarnings("unchecked")
    public X unmarsh(String data) throws JAXBException {
        return (X) unmarshaller.unmarshal(new StringReader(data));
    }

    @SuppressWarnings("unchecked")
    public X unmarsh(byte[] data) throws IntegrationModuleException {
        try {
            return (X) unmarshaller.unmarshal(new ByteArrayInputStream(data));
        } catch (JAXBException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.single.message.validation"), e);
        }

    }

    @SuppressWarnings("unchecked")
    public X unmarshSystemConfiguration(byte[] data) throws IntegrationModuleException {
        try {
            return (X) unmarshaller.unmarshal(new ByteArrayInputStream(data));
        } catch (JAXBException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.systemconfiguration.validation"), e);
        }

    }

    @SuppressWarnings("unchecked")
    public X unmarshProductFilter(byte[] data) throws IntegrationModuleException {
        try {
            return (X) unmarshaller.unmarshal(new ByteArrayInputStream(data));
        } catch (JAXBException e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.productFilter.validation"), e);
        }

    }

    public X unsealWithSymmKey(byte[] data, Key symmKey) {
        data = EncryptionUtils.unsealWithSymmKey(symmKey, data);
        return toObject(data);
    }

    public byte[] unsealWithKey(byte[] data, Key symmKey) {
        return EncryptionUtils.unsealWithSymmKey(symmKey, data);
    }

    public void wrtiePrescriptionToFile(byte[] unsealByteWithSymmKeyDecodeAndDecompress, String archivingPath) throws IOException {
        FileUtils.writeByteArrayToFile(new File(archivingPath), unsealByteWithSymmKeyDecodeAndDecompress);
    }
}
