package be.vlaanderen.zg.vaccinnet.batchuploadtest.model

enum class Gender(val value: String) {
    MALE("male"), FEMALE("female");

    companion object {
        fun fromValue(input: String): Gender {
            for (gender in values()) {
                if (gender.value.equals(input, ignoreCase = true)) {
                    return gender
                }
            }
            throw RuntimeException("Gender is not valid: $input")
        }
    }
}
