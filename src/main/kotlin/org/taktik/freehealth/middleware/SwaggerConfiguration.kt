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

import com.fasterxml.classmate.TypeResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion.*
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.BasicAuth
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import javax.xml.datatype.XMLGregorianCalendar

@Configuration
@EnableSwagger2
class SwaggerConfiguration(val typeResolver: TypeResolver) {
    @Bean
    fun api(): Docket =
        Docket(DocumentationType.SWAGGER_2)
            .forCodeGeneration(true)

            // fix model mapping
            .directModelSubstitute(XMLGregorianCalendar::class.java, Long::class.java)

            // add missing models
            .additionalModels(
                typeResolver.resolve(AuthzDecisionStatement::class.java),
                typeResolver.resolve(AuthnStatement::class.java),
                typeResolver.resolve(AttributeStatement::class.java),
                typeResolver.resolve(Attribute::class.java),
                typeResolver.resolve(EncryptedElementType::class.java)
            )

            .securitySchemes(listOf(BasicAuth("basicAuth")))
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("^/(ab|admin|chap4|consent|consultrn|crypto|gmd|eatt.+|efact|ehb.+|genins|hub|mda|mhm|mycarenet|recip.+|sts|tarif|therlink)(/.*)?"))
            .build()
}
