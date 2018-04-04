#!/bin/sh
cd ../../../lib/java
. ./setEnv.sh
export CLASSPATH="$CLASSPATH:../../examples/command-line/recipe/"
echo Enter the RID:
read RID
java -Dmock=true -Dconfig=../../conf/connector-client.properties be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine getPrescription $RID
