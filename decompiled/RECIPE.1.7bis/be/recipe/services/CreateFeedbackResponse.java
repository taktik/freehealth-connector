package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "createFeedbackResponse"
)
@XmlRootElement(
   name = "createFeedbackResponse"
)
public class CreateFeedbackResponse implements Serializable {
   private static final long serialVersionUID = 1L;
}
