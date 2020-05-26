package be.fgov.ehealth.errors.service.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BusinessErrorType"
)
@XmlRootElement(
   name = "BusinessError"
)
public class BusinessError extends ServiceErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
}
