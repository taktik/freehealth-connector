package org.taktik.freehealth.middleware.dto

class EndpointDistributorStatusDto(val mustPoll: Boolean, val isBcpMode: Boolean, val default: Map<String, String>, val active: Map<String, String>)
