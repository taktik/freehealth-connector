package be.fgov.ehealth.commons.protocol.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthorPaginationRequestType"
)
public class AuthorPaginationRequestType extends AuthorRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Offset",
      required = true
   )
   protected int offset;
   @XmlAttribute(
      name = "MaxElements",
      required = true
   )
   protected int maxElements;

   public int getOffset() {
      return this.offset;
   }

   public void setOffset(int value) {
      this.offset = value;
   }

   public int getMaxElements() {
      return this.maxElements;
   }

   public void setMaxElements(int value) {
      this.maxElements = value;
   }
}
