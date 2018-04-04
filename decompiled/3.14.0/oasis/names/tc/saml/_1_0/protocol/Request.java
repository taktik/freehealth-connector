package oasis.names.tc.saml._1_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   propOrder = {"assertionArtifacts", "assertionIDReferences", "authorizationDecisionQuery", "attributeQuery", "authenticationQuery", "subjectQuery", "query"}
)
@XmlRootElement(
   name = "Request"
)
public class Request extends RequestAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AssertionArtifact"
   )
   protected List<String> assertionArtifacts;
   @XmlElement(
      name = "AssertionIDReference",
      namespace = "urn:oasis:names:tc:SAML:1.0:assertion"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NCName"
   )
   protected List<String> assertionIDReferences;
   @XmlElement(
      name = "AuthorizationDecisionQuery"
   )
   protected AuthorizationDecisionQuery authorizationDecisionQuery;
   @XmlElement(
      name = "AttributeQuery"
   )
   protected AttributeQuery attributeQuery;
   @XmlElement(
      name = "AuthenticationQuery"
   )
   protected AuthenticationQuery authenticationQuery;
   @XmlElement(
      name = "SubjectQuery"
   )
   protected SubjectQueryAbstractType subjectQuery;
   @XmlElement(
      name = "Query"
   )
   protected QueryAbstractType query;

   public List<String> getAssertionArtifacts() {
      if (this.assertionArtifacts == null) {
         this.assertionArtifacts = new ArrayList();
      }

      return this.assertionArtifacts;
   }

   public List<String> getAssertionIDReferences() {
      if (this.assertionIDReferences == null) {
         this.assertionIDReferences = new ArrayList();
      }

      return this.assertionIDReferences;
   }

   public AuthorizationDecisionQuery getAuthorizationDecisionQuery() {
      return this.authorizationDecisionQuery;
   }

   public void setAuthorizationDecisionQuery(AuthorizationDecisionQuery value) {
      this.authorizationDecisionQuery = value;
   }

   public AttributeQuery getAttributeQuery() {
      return this.attributeQuery;
   }

   public void setAttributeQuery(AttributeQuery value) {
      this.attributeQuery = value;
   }

   public AuthenticationQuery getAuthenticationQuery() {
      return this.authenticationQuery;
   }

   public void setAuthenticationQuery(AuthenticationQuery value) {
      this.authenticationQuery = value;
   }

   public SubjectQueryAbstractType getSubjectQuery() {
      return this.subjectQuery;
   }

   public void setSubjectQuery(SubjectQueryAbstractType value) {
      this.subjectQuery = value;
   }

   public QueryAbstractType getQuery() {
      return this.query;
   }

   public void setQuery(QueryAbstractType value) {
      this.query = value;
   }
}
