package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SPUserNoticeType",
   propOrder = {"noticeRef", "explicitText"}
)
@XmlRootElement(
   name = "SPUserNotice"
)
public class SPUserNotice implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NoticeRef"
   )
   protected NoticeReferenceType noticeRef;
   @XmlElement(
      name = "ExplicitText"
   )
   protected String explicitText;

   public NoticeReferenceType getNoticeRef() {
      return this.noticeRef;
   }

   public void setNoticeRef(NoticeReferenceType value) {
      this.noticeRef = value;
   }

   public String getExplicitText() {
      return this.explicitText;
   }

   public void setExplicitText(String value) {
      this.explicitText = value;
   }
}
