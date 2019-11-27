package org.taktik.freehealth.middleware.dto.common

class Acknowledgment(var messageId:String? = null, var recipient: Addressee? = null, var ackType: String? = null, var dateTime: Long? = null)
