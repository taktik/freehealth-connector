#!/bin/sh
cd ../../../lib/java
. ./setEnv.sh

export CLASSPATH="$CLASSPATH:../../examples/command-line/general/:../../examples/command-line/mycarenet/"

java -Dconfig=../../conf/connector-client.properties -DconfigValidation=../../conf/validation.properties be.business.connector.session.SessionUtilCommandLine
