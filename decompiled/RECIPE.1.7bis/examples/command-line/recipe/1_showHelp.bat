@echo off
cd ..\..\..\lib\java
call setEnv
set CLASSPATH="%CLASSPATH%;..\..\examples\command-line\recipe\"
java -Dconfig=..\..\conf\connector-client.properties -DconfigValidation=..\..\conf\validation.properties be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine
pause