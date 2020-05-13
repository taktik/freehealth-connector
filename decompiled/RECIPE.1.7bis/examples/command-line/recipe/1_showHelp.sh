#!/bin/sh
cd ../../../lib/java
. ./setEnv.sh
export CLASSPATH="$CLASSPATH:../../examples/command-line/recipe/"
java -Dconfig=../../conf/connector-client.properties -DconfigValidation=../../conf/validation.properties be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine 
