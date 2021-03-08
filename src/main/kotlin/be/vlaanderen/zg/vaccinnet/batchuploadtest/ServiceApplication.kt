package be.vlaanderen.zg.vaccinnet.batchuploadtest

import lombok.extern.slf4j.Slf4j
import org.springframework.context.ApplicationListener

@SpringBootApplication
@EntityScan(basePackages = ["be.vlaanderen.zg.vaccinnettest"])
@Slf4j
object ServiceApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        val application = SpringApplication(ServiceApplication::class.java)
        addInitHooks(application)
        val applicationContext: ApplicationContext = application.run(*args)
        val applicationArguments: ApplicationArguments = applicationContext.getBean(
            ApplicationArguments::class.java
        )
        ApplicationArgumentUtils.validateService(applicationArguments)
        val serviceArgument: String = ApplicationArgumentUtils.getService(applicationArguments)
        when (serviceArgument) {
            "aanleveren" -> {
                ApplicationArgumentUtils.validateAanleverenArguments(args)
                applicationContext.getBean(AanleverenVaccinatieGegevensService::class.java).run()
            }
            "resultaat" -> {
                ApplicationArgumentUtils.validateResultaatArguments(args)
                applicationContext.getBean(
                    ResultaatVerwerkingVaccinatieGegevensService::class.java
                ).run()
            }
            "aanvullen" -> {
                ApplicationArgumentUtils.validateAanvullenArguments(args)
                applicationContext.getBean(
                    AanleverenAanvullendeVaccinatieGegevensService::class.java
                ).run()
            }
        }
        SpringApplication.exit(applicationContext)
    }

    fun addInitHooks(application: SpringApplication) {
        application.addListeners(ApplicationListener<ApplicationPreparedEvent?> { event: ApplicationPreparedEvent? ->
            LOG.info("ApplicationPreparedEvent: Setting application timezone to UTC")
            TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")))
        } as ApplicationListener<ApplicationPreparedEvent?>)
    }
}
