package org.taktik.freehealth.middleware.dto.dmg

import java.io.Serializable
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
class DmgAcknowledge(var major: String, var minor: String, var message: String, var date: Long? = null) : DmgMessage(), Serializable
