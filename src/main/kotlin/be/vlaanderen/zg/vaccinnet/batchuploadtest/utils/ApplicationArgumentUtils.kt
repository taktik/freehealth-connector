package be.vlaanderen.zg.vaccinnet.batchuploadtest.utils

import lombok.extern.slf4j.Slf4j
import java.lang.StringBuilder
import java.util.*
import java.util.function.Consumer

@Slf4j
object ApplicationArgumentUtils {
    private const val CONFIG_DIR = "configDir"
    private const val PATIENT = "patient"
    private const val SENDER = "sender"
    private const val SERVICE = "service"
    private const val REQUEST_ID = "requestId"
    fun getConfigDir(applicationArguments: ApplicationArguments): String {
        return applicationArguments.getOptionValues(CONFIG_DIR).get(0)
    }

    fun getPatientFileName(applicationArguments: ApplicationArguments): String {
        return applicationArguments.getOptionValues(PATIENT).get(0)
    }

    fun getSenderFileName(applicationArguments: ApplicationArguments): String {
        return applicationArguments.getOptionValues(SENDER).get(0)
    }

    fun getService(applicationArguments: ApplicationArguments): String {
        return applicationArguments.getOptionValues(SERVICE).get(0)
    }

    fun getRequestId(applicationArguments: ApplicationArguments): String {
        return applicationArguments.getOptionValues(REQUEST_ID).get(0)
    }

    fun validateService(applicationArguments: ApplicationArguments) {
        val service = getService(applicationArguments)
        val possibleServices = Arrays.asList("aanleveren", "resultaat", "aanvullen")
        if (possibleServices.stream().noneMatch { possibleService: String -> possibleService == service }) {
            throw RuntimeException("Service argument is not available")
        }
    }

    fun validateAanleverenArguments(args: Array<String>) {
        validateArguments(java.util.List.of(PATIENT, SENDER, CONFIG_DIR), args)
    }

    fun validateAanvullenArguments(args: Array<String>) {
        validateArguments(java.util.List.of(PATIENT, SENDER, CONFIG_DIR), args)
    }

    fun validateResultaatArguments(args: Array<String>) {
        validateArguments(java.util.List.of(SENDER, CONFIG_DIR, REQUEST_ID), args)
    }

    private fun validateArguments(requiredArguments: List<String>, args: Array<String>) {
        val stringBuilder = StringBuilder()
        requiredArguments.forEach(Consumer { requiredArgument: String ->
            validateArgument(
                requiredArgument,
                args,
                stringBuilder
            )
        })
        if (stringBuilder.length > 0) {
            val message = stringBuilder.toString()
            LOG.error(message)
            throw RuntimeException(message)
        }
    }

    private fun validateArgument(argumentKey: String, args: Array<String>, stringBuilder: StringBuilder) {
        if (Arrays.stream(args)
                .noneMatch { s: String -> s.startsWith("--$argumentKey") }
        ) {
            if (stringBuilder.length > 0) {
                stringBuilder.append(" / ")
            }
            stringBuilder
                .append("No ")
                .append(argumentKey)
                .append(" argument available")
        }
    }
}
