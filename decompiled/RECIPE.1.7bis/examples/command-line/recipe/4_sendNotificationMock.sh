#!/bin/sh
cd ../../../lib/java
. ./setEnv.sh
export CLASSPATH="$CLASSPATH:../../examples/command-line/recipe/"
java -Dmock=true -Dconfig=../../conf/connector-client.properties be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine sendNotification ../../examples/resources/sample-notification.xml 84071230581 66666120 
