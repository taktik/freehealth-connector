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

package org.taktik.connector.business.genins.exception

enum class GenInsBusinessConnectorExceptionValues(val errorCode: String, val message: String) {
    TARGET_SERVICE_ERROR("targetservice.error", "TargetService error description: {0}"),
    SETTINGS_NOT_FOUND("settings.not.found", "Could not find the settings file : {0}"),
    OBJECTBUILDER_INSTANCIATION_ERROR("objectbuilder.instanciation.error", "Object builder could not be instanciated : {0}"),
    MALFORMED_URL("malformed.url", "Invalid url to {0} file"),
    ERROR_XML_KMEHRVALIDATOR("error.xml.kmehrvalidator", "XML is not correct: {0}"),
    ERROR_XML_GENINSVALIDATOR("error.xml.genins.validator", "XML is not correct: {0}"),
    ERROR_XML_UNDEFINED_XSD_FOR_XML_CLASS_VALIDATOR("error.xml.genins.undefined.class.validator", "no xsd file location is defined for class: {0}"),
    INVALID_ATTRIBUTES_LENGTH("invalid_attributes_length", "Invalid attributes array length : {0}"),
    UNKNOWN_ERROR("unknown.error", "Unknown error"),
    INPUT_PARAM_NULL("input.param.null", "Input parameter null : {0}"),
    ERROR_RESPONSE_XML("error.xml.responsevalidator", "response is not valid : {0}"),
    TIMESTAMP_NOT_CORRECT("error.xml.invalid.timestamp.in.response", "the timestamp in the response was not valid : {0}")
}
