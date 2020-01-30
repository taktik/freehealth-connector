package be.apb.gfddpp.common.binding;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _RowSet_QNAME = new QName("", "rowSet");

   public RowSet createRowSet() {
      return new RowSet();
   }

   public TipSystemAudit createTipSystemAudit() {
      return new TipSystemAudit();
   }

   @XmlElementDecl(
      namespace = "",
      name = "rowSet"
   )
   public JAXBElement<RowSet> createRowSet(RowSet value) {
      return new JAXBElement(_RowSet_QNAME, RowSet.class, (Class)null, value);
   }
}
