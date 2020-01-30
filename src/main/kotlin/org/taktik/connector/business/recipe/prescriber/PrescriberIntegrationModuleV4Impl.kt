package org.taktik.connector.business.recipe.prescriber


import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.freehealth.middleware.service.STSService

class PrescriberIntegrationModuleV4Impl(stsService: STSService, keyDepotService: KeyDepotService) : PrescriberIntegrationModuleImpl(stsService, keyDepotService), PrescriberIntegrationModuleV4 {

}
