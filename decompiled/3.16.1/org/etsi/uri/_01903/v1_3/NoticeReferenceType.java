package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NoticeReferenceType",
   propOrder = {"organization", "noticeNumbers"}
)
public class NoticeReferenceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Organization",
      required = true
   )
   protected String organization;
   @XmlElement(
      name = "NoticeNumbers",
      required = true
   )
   protected IntegerListType noticeNumbers;

   public String getOrganization() {
      return this.organization;
   }

   public void setOrganization(String value) {
      this.organization = value;
   }

   public IntegerListType getNoticeNumbers() {
      return this.noticeNumbers;
   }

   public void setNoticeNumbers(IntegerListType value) {
      this.noticeNumbers = value;
   }
}
