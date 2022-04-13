#!/bin/sh
cd ../../../lib/java
. ./setEnv.sh
export CLASSPATH="$CLASSPATH:../../examples/command-line/recipe/"
echo Enter the RID:
read RID=
echo Enter the Vision:
read vision=
java -Dconfig=../../conf/connector-client.properties -DconfigValidation=../../conf/validation.properties -Dsession=../../examples/command-line/session.xml -DsystemKeystore=../../examples/command-line/systemKeystoreProperties.xml be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine putVision $RID $vision
