/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware.domain.common.messages

import org.apache.commons.collections4.Predicate
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.SAXException

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import java.io.IOException
import java.io.InputStream
import java.util.ArrayList
import java.util.HashMap

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 11/11/13
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
object ErrorWarningMessages {
    fun parse(inputStream: InputStream, context: String?, p: Predicate<AbstractMessage>?): List<AbstractMessage> {
        val factory = DocumentBuilderFactory.newInstance()
        val builder: DocumentBuilder
        val doc: Document
        try {
            builder = factory.newDocumentBuilder()
            doc = builder.parse(inputStream)
        } catch (e: ParserConfigurationException) {
            throw IllegalStateException(e)
        } catch (e: SAXException) {
            throw IllegalStateException(e)
        } catch (e: IOException) {
            throw IllegalStateException(e)
        }

        val result = ArrayList<AbstractMessage>()

        val errors = doc.getElementsByTagName("error")
        for (i in 0 until errors.length) {
            val e = errors.item(i)
            if (e.nodeType == Node.ELEMENT_NODE) {
                val nl = (e as Element).getElementsByTagName("context")
                val ctx = if (nl.length > 0) nl.item(0).textContent else null

                if (context == null || context == ctx) {
                    val msg = ErrorMessage()
                    fillMessage(e, ctx, msg)

                    if (p == null || p.evaluate(msg)) {
                        result.add(msg)
                    }
                }
            }
        }

        val warnings = doc.getElementsByTagName("warning")
        for (i in 0 until warnings.length) {
            val e = errors.item(i)
            if (e.nodeType == Node.ELEMENT_NODE) {
                val nl = (e as Element).getElementsByTagName("context")
                val ctx = if (nl.length > 0) nl.item(0).textContent else null
                if (context == null || context == ctx) {
                    val msg = WarningMessage()
                    fillMessage(e, ctx, msg)

                    if (p == null || p.evaluate(msg)) {
                        result.add(msg)
                    }
                }
            }
        }

        return result
    }

    private fun fillMessage(e: Element, ctx: String?, msg: AbstractMessage) {
        var nl: NodeList
        nl = e.getElementsByTagName("subcontext")
        val sctx = if (nl.length > 0) nl.item(0).textContent else null
        nl = e.getElementsByTagName("code")
        val code = if (nl.length > 0) nl.item(0).textContent else null
        nl = e.getElementsByTagName("zone")
        val zone = if (nl.length > 0) nl.item(0).textContent else null
        nl = e.getElementsByTagName("pattern")
        val pattern = if (nl.length > 0) nl.item(0).textContent else null
        nl = e.getElementsByTagName("skip")
        val skip = if (nl.length > 0) nl.item(0).textContent else null

        nl = e.getElementsByTagName("message")


        msg.context = ctx
        msg.subContext = sctx
        msg.code = code
        msg.zone = zone
        msg.pattern = pattern
        msg.isSkip = skip != null && skip == "true"

        msg.message = HashMap()

        for (j in 0 until nl.length) {
            val m = nl.item(j)
            if (m.nodeType == Node.ELEMENT_NODE) {
                msg.message.put((m as Element).getAttribute("lang"), m.getTextContent())
            }
        }
    }

    fun parse(inputStream: InputStream, context: String): List<AbstractMessage> {
        return ErrorWarningMessages.parse(inputStream, context, null)
    }

    fun parse(inputStream: InputStream): List<AbstractMessage> {
        return ErrorWarningMessages.parse(inputStream, null, null)
    }
}
