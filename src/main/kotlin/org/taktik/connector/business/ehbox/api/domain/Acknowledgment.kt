package org.taktik.connector.business.ehbox.api.domain

import org.taktik.freehealth.middleware.mapper.toAddresseeDto

class Acknowledgment(var messageId:String? = null, var recipient: Addressee? = null, var ackType: String? = null, var dateTime: Long? = null)
