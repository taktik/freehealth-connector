/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.io.File

@SpringBootApplication
class MiddlewareApplication

fun main(args: Array<String>) {
    //Extract important files
    val root = File("/opt/ehealth").apply { mkdirs() }
    listOf("acpt","prod").forEach { dir ->
        val sdir = File(root, dir).apply { mkdirs() }
        listOf("caCertificateKeystore.jks","truststore.jks","tsacertificate.jks","tslostore.jks").forEach { file ->
        val os = File(sdir, file).outputStream()
        MiddlewareApplication::class.java.getResourceAsStream("/$dir/$file").copyTo(os)
        os.close()
    } }

    SpringApplication.run(MiddlewareApplication::class.java, *args)
}
