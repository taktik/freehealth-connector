#!/bin/sh
cd ../../../lib/java
. ./setEnv.sh
export CLASSPATH="$CLASSPATH:../../examples/command-line/recipe/"
echo Enter the PatientId:
read PatientId=
echo Enter the page:
read page=

java -Dconfig=../../conf/connector-client.properties -DconfigValidation=../../conf/validation.properties -Dsession=../../examples/command-line/session.xml -DsystemKeystore=../../examples/command-line/systemKeystoreProperties.xml be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine listOpenRids $PatientId $page

