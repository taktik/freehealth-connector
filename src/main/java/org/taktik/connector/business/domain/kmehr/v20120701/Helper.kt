package org.taktik.connector.business.domain.kmehr.v20120701

import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESS
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESSschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENT
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRY
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRYschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOM
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOMschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDTIMEUNIT
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDTIMEUNITschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes

fun CDITEM.s(scheme: CDITEMschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDTELECOM.s(scheme: CDTELECOMschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDADDRESS.s(scheme: CDADDRESSschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDCOUNTRY.s(scheme: CDCOUNTRYschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDCONTENT.s(scheme: CDCONTENTschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDHCPARTY.s(scheme: CDHCPARTYschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDTRANSACTION.s(scheme: CDTRANSACTIONschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDTIMEUNIT.s(scheme: CDTIMEUNITschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}
