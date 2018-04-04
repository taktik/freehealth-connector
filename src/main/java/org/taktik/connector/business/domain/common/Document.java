package org.taktik.connector.business.domain.common;

import org.taktik.freehealth.middleware.domain.SigningValue;

import java.io.Serializable;

/**
 * Created by aduchate on 8/11/13, 15:53
 */
public class Document implements Serializable {
    private String title;

    private byte[] content;
    private String textContent;

    private String filename;
    private String mimeType;
    private SigningValue signing;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public SigningValue getSigning() {
        return signing;
    }

    public void setSigning(SigningValue signing) {
        this.signing = signing;
    }
}
