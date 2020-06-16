package org.taktik.freehealth.middleware.dto.memberdata

class MemberDataBatchRequestDto(var members: List<MemberInfoDto> = listOf(),
    var facets: List<FacetDto>? = null)
