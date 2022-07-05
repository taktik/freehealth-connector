package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MiscellaneousType"
)
public class MiscellaneousType extends MedicalCoverCommonInformationType implements Serializable {
   private static final long serialVersionUID = 1L;

   public MiscellaneousType() {
   }
}
