package org.taktik.connector.business.therlink.domain


import java.io.Serializable
import java.util.ArrayList

import org.taktik.connector.business.domain.Error
import org.taktik.connector.business.therlink.domain.TherapeuticLink

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 23/06/14
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
class TherapeuticLinkMessage : Serializable {
	var isComplete: Boolean = false
	var errors: List<Error> = ArrayList()
	var therapeuticLink: TherapeuticLink? = null

	constructor()

	constructor(therapeuticLink: TherapeuticLink) {
		this.therapeuticLink = therapeuticLink
		this.isComplete = true
	}
}
