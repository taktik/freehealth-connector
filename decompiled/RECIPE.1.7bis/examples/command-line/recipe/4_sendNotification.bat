@echo off
cd ..\..\..\lib\java
call setEnv
set CLASSPATH="%CLASSPATH%;..\..\examples\command-line\recipe\"
java -Dconfig=..\..\conf\connector-client.properties -DconfigValidation=..\..\conf\validation.properties -Dsession=..\..\examples\command-line\session.xml -DsystemKeystore=..\..\examples\command-line\systemKeystoreProperties.xml be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine sendNotification ..\..\examples\resources\sample-notification.xml 84071230581 66666219 
pause