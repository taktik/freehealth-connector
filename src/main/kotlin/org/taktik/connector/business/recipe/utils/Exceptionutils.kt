package org.taktik.connector.business.recipe.utils

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleEhealthException
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleRuntimeException
import be.apb.gfddpp.validation.exception.SingleMessageValidationException
import org.apache.logging.log4j.LogManager
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper

object Exceptionutils {
    private val LOG = LogManager.getLogger(Exceptionutils::class.java)
    @Throws(
        IntegrationModuleException::class,
        IntegrationModuleEhealthException::class,
        IntegrationModuleRuntimeException::class
    )
    fun errorHandler(t: Throwable): Nothing {
        if (t is IntegrationModuleRuntimeException) {
            LOG.error("", t)
            throw t
        } else if (t is IntegrationModuleEhealthException) {
            LOG.error("", t)
            throw t
        } else if (t.cause is IntegrationModuleEhealthException) {
            LOG.error("", t)
            throw (t.cause as IntegrationModuleEhealthException?)!!
        } else if (t is IntegrationModuleException) {
            LOG.error("", t)
            throw t
        } else if (t is SingleMessageValidationException) {
            LOG.error("", t)
            throw IntegrationModuleException(I18nHelper.getLabel("error.single.message.validation"), t)
        } else {
            LOG.error("", t)
            throw IntegrationModuleException(I18nHelper.getLabel("error.technical"), t)
        }
    }

    @Throws(
        IntegrationModuleException::class,
        IntegrationModuleEhealthException::class,
        IntegrationModuleRuntimeException::class
    )
    fun errorHandler(t: Throwable, errorMsg: String?): Nothing {
        if (t is IntegrationModuleRuntimeException) {
            LOG.error("", t)
            throw t
        } else if (t is IntegrationModuleEhealthException) {
            LOG.error("", t)
            throw t
        } else if (t.cause is IntegrationModuleEhealthException) {
            LOG.error("", t)
            throw (t.cause as IntegrationModuleEhealthException?)!!
        } else if (t is IntegrationModuleException) {
            LOG.error("", t)
            throw t
        } else if (t is SingleMessageValidationException) {
            LOG.error("", t)
            throw IntegrationModuleException(I18nHelper.getLabel("error.single.message.validation"), t)
        } else {
            LOG.error("", t)
            throw IntegrationModuleException(I18nHelper.getLabel(errorMsg), t)
        }
    }
}
