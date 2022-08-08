#!/bin/bash

echo "Let's start the java app"
echo "$FHC_STAGE"

# aws s3 cp "s3://rosa-fhc-licenses-eu-central-1/$FHC_STAGE-licenses.txt" "./$FHC_STAGE-licenses.txt"

# cat "./$FHC_STAGE-licenses.txt" >> "build/resources/main/$FHC_STAGE/org.taktik.connector.technical.properties"

# cat "build/resources/main/$FHC_STAGE/org.taktik.connector.technical.properties"

pwd

java -Dorg.taktik.connector.technical.config.location=/acpt/org.taktik.connector.technical.properties -jar build/libs/freehealth-connector-0.3.0-267-g3ddc8a403a-dirty.jar
