/*
 * Commons eID Project.
 * Copyright (C) 2008-2013 FedICT.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version
 * 3.0 as published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, see
 * http://www.gnu.org/licenses/.
 */
package org.taktik.freehealth.middleware.pkcs11.beid

import java.security.SecureRandomSpi
import javax.smartcardio.CardException
import java.lang.RuntimeException
import org.slf4j.LoggerFactory

/**
 * eID based implementation of a secure random generator. Can be used to seed
 * for example a mutual SSL handshake. This secure random generator does not
 * feature eID auto recovery.
 *
 *
 * Usage:
 *
 * <pre>
 * SecureRandom secureRandom = SecureRandom.getInstance(&quot;BeID&quot;);
</pre> *
 *
 * @author Frank Cornelis
 */
class RemoteBeIDSecureRandom(val beIDCard: RemoteBeIDCard) : SecureRandomSpi() {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun engineSetSeed(seed: ByteArray) {
        log.debug("engineSetSeed")
    }

    override fun engineNextBytes(bytes: ByteArray) {
        log.debug("engineNextBytes: " + bytes.size + " bytes")
        System.arraycopy(
            try {
                beIDCard.getChallenge(bytes.size)
            } catch (e: CardException) {
                throw RuntimeException(e)
            }, 0, bytes, 0, bytes.size
        )
    }

    override fun engineGenerateSeed(numBytes: Int): ByteArray {
        log.debug("engineGenerateSeed: $numBytes bytes")
        return try {
            beIDCard.getChallenge(numBytes)
        } catch (e: CardException) {
            throw RuntimeException(e)
        }
    }

}
