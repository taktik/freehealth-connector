
package org.taktik.connector.business.vaccinnet;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.taktik.connector.business.vaccinnet package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RemoveVaccinationRequest_QNAME = new QName("http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", "RemoveVaccinationRequest");
    private final static QName _GetVaccinationsResponse_QNAME = new QName("http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", "GetVaccinationsResponse");
    private final static QName _AddVaccinationsResponse_QNAME = new QName("http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", "AddVaccinationsResponse");
    private final static QName _AddVaccinationsRequest_QNAME = new QName("http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", "AddVaccinationsRequest");
    private final static QName _RemoveVaccinationResponse_QNAME = new QName("http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", "RemoveVaccinationResponse");
    private final static QName _GetVaccinationsRequest_QNAME = new QName("http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", "GetVaccinationsRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.taktik.connector.business.vaccinnet
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddVaccinationsResponseType }
     * 
     */
    public AddVaccinationsResponseType createAddVaccinationsResponseType() {
        return new AddVaccinationsResponseType();
    }

    /**
     * Create an instance of {@link AddVaccinationsResponseType.Results }
     * 
     */
    public AddVaccinationsResponseType.Results createAddVaccinationsResponseTypeResults() {
        return new AddVaccinationsResponseType.Results();
    }

    /**
     * Create an instance of {@link AddVaccinationsRequestType }
     * 
     */
    public AddVaccinationsRequestType createAddVaccinationsRequestType() {
        return new AddVaccinationsRequestType();
    }

    /**
     * Create an instance of {@link GetVaccinationsResponseType }
     * 
     */
    public GetVaccinationsResponseType createGetVaccinationsResponseType() {
        return new GetVaccinationsResponseType();
    }

    /**
     * Create an instance of {@link RemoveVaccinationResponseType }
     * 
     */
    public RemoveVaccinationResponseType createRemoveVaccinationResponseType() {
        return new RemoveVaccinationResponseType();
    }

    /**
     * Create an instance of {@link GetVaccinationsRequestType }
     * 
     */
    public GetVaccinationsRequestType createGetVaccinationsRequestType() {
        return new GetVaccinationsRequestType();
    }

    /**
     * Create an instance of {@link RemoveVaccinationRequestType }
     * 
     */
    public RemoveVaccinationRequestType createRemoveVaccinationRequestType() {
        return new RemoveVaccinationRequestType();
    }

    /**
     * Create an instance of {@link StatusType }
     * 
     */
    public StatusType createStatusType() {
        return new StatusType();
    }

    /**
     * Create an instance of {@link RequestType }
     * 
     */
    public RequestType createRequestType() {
        return new RequestType();
    }

    /**
     * Create an instance of {@link AddVaccinationsResponseType.Results.VaccinationId }
     * 
     */
    public AddVaccinationsResponseType.Results.VaccinationId createAddVaccinationsResponseTypeResultsVaccinationId() {
        return new AddVaccinationsResponseType.Results.VaccinationId();
    }

    /**
     * Create an instance of {@link AddVaccinationsRequestType.Base64EncodedKmehrmessage }
     * 
     */
    public AddVaccinationsRequestType.Base64EncodedKmehrmessage createAddVaccinationsRequestTypeBase64EncodedKmehrmessage() {
        return new AddVaccinationsRequestType.Base64EncodedKmehrmessage();
    }

    /**
     * Create an instance of {@link GetVaccinationsResponseType.Base64EncodedKmehr }
     * 
     */
    public GetVaccinationsResponseType.Base64EncodedKmehr createGetVaccinationsResponseTypeBase64EncodedKmehr() {
        return new GetVaccinationsResponseType.Base64EncodedKmehr();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveVaccinationRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", name = "RemoveVaccinationRequest")
    public JAXBElement<RemoveVaccinationRequestType> createRemoveVaccinationRequest(RemoveVaccinationRequestType value) {
        return new JAXBElement<RemoveVaccinationRequestType>(_RemoveVaccinationRequest_QNAME, RemoveVaccinationRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVaccinationsResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", name = "GetVaccinationsResponse")
    public JAXBElement<GetVaccinationsResponseType> createGetVaccinationsResponse(GetVaccinationsResponseType value) {
        return new JAXBElement<GetVaccinationsResponseType>(_GetVaccinationsResponse_QNAME, GetVaccinationsResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddVaccinationsResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", name = "AddVaccinationsResponse")
    public JAXBElement<AddVaccinationsResponseType> createAddVaccinationsResponse(AddVaccinationsResponseType value) {
        return new JAXBElement<AddVaccinationsResponseType>(_AddVaccinationsResponse_QNAME, AddVaccinationsResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddVaccinationsRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", name = "AddVaccinationsRequest")
    public JAXBElement<AddVaccinationsRequestType> createAddVaccinationsRequest(AddVaccinationsRequestType value) {
        return new JAXBElement<AddVaccinationsRequestType>(_AddVaccinationsRequest_QNAME, AddVaccinationsRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveVaccinationResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", name = "RemoveVaccinationResponse")
    public JAXBElement<RemoveVaccinationResponseType> createRemoveVaccinationResponse(RemoveVaccinationResponseType value) {
        return new JAXBElement<RemoveVaccinationResponseType>(_RemoveVaccinationResponse_QNAME, RemoveVaccinationResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVaccinationsRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService/V21", name = "GetVaccinationsRequest")
    public JAXBElement<GetVaccinationsRequestType> createGetVaccinationsRequest(GetVaccinationsRequestType value) {
        return new JAXBElement<GetVaccinationsRequestType>(_GetVaccinationsRequest_QNAME, GetVaccinationsRequestType.class, null, value);
    }

}
