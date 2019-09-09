package org.taktik.connector.business.therlink.domain

import java.io.Serializable
import java.util.ArrayList

import org.taktik.connector.business.domain.Error

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 23/06/14
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
class HasTherapeuticLinkMessage : Serializable {
    var isComplete: Boolean = false
    var errors: List<Error> = ArrayList()
    var result: Boolean = false

    constructor()

    constructor(therapeuticLinks: List<TherapeuticLink>) {
        this.isComplete = true
    }
}
