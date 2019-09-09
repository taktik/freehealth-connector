package org.taktik.connector.business.recipeprojects.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.Key;

import javax.crypto.Cipher;
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

    public X unsealWithSymmKey(byte[] data, Key symmKey) {
        byte[] result = null;

        try {
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, symmKey);
            result = cipher.doFinal(data);
        } catch (Exception e) {
        }
        data = result;
        return toObject(data);
    }
}
