package org.taktik.freehealth.middleware.drugs.civics;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 10/06/13
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
public class ParagraphPreview implements Serializable {
    String chapterName;
    String paragraphName;
    String keyStringNl;
    String keyStringFr;
    Long paragraphVersion;
    private Long id;

    public Long getParagraphVersion() {
        return paragraphVersion;
    }

    public void setParagraphVersion(Long paragraphVersion) {
        this.paragraphVersion = paragraphVersion;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getParagraphName() {
        return paragraphName;
    }

    public void setParagraphName(String paragraphName) {
        this.paragraphName = paragraphName;
    }

    public String getKeyStringNl() {
        return keyStringNl;
    }

    public void setKeyStringNl(String keyStringNl) {
        this.keyStringNl = keyStringNl;
    }

    public String getKeyStringFr() {
        return keyStringFr;
    }

    public void setKeyStringFr(String keyStringFr) {
        this.keyStringFr = keyStringFr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
