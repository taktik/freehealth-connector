package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindCommentedClassificationResponseType",
   propOrder = {"commentedClassifications"}
)
@XmlRootElement(
   name = "FindCommentedClassificationResponse"
)
public class FindCommentedClassificationResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommentedClassification"
   )
   protected List<ConsultCommentedClassificationTreeType> commentedClassifications;

   public FindCommentedClassificationResponse() {
   }

   public List<ConsultCommentedClassificationTreeType> getCommentedClassifications() {
      if (this.commentedClassifications == null) {
         this.commentedClassifications = new ArrayList();
      }

      return this.commentedClassifications;
   }
}
