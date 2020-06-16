package org.taktik.freehealth.middleware.domain.memberdata

class MemberDataBatchRequest(var members: List<MemberInfo> = listOf(),
    var facets: List<Facet>? = null)
