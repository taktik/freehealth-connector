#!/bin/sh
cd ../../../lib/java
. ./setEnv.sh
echo User :
read NISS
echo Password :
read PASSWORD
echo System keystore password:
read SYSTEM_PASS
echo System keystore path :
read SYSTEM_PATH
echo System keystore directory:
read SYSTEM_DIRECTORY
echo System keystore riziv :
read SYSTEM_RIZIV
if [ -z "$NISS" ]; then
	NISS=null
fi
if [ -z "$PASSWORD" ]; then
	PASSWORD=null
fi
if [ -z "$SYSTEM_PASS" ]; then
	SYSTEM_PASS=null
fi
if [ -z "$SYSTEM_PATH" ]; then
	SYSTEM_PATH=null
fi
if [ -z "$SYSTEM_DIRECTORY" ]; then
	SYSTEM_DIRECTORY=null
fi
if [ -z "$SYSTEM_RIZIV" ]; then
	SYSTEM_RIZIV=null
fi
export CLASSPATH="$CLASSPATH:../../examples/command-line/general/"
java -Dconfig=../../conf/connector-client.properties -DconfigValidation=../../conf/validation.properties be.business.connector.session.SessionUtilCommandLine createFallbackSession ../../examples/command-line/session.xml ../../examples/command-line/systemKeystoreProperties.xml "$NISS" "$PASSWORD" "$SYSTEM_PASS" "$SYSTEM_PATH" "$SYSTEM_DIRECTORY" "$SYSTEM_RIZIV"
