@echo off
cd ..\..\..\lib\java
call setEnv
set CLASSPATH="%CLASSPATH%;..\..\examples\command-line\recipe\"
echo Enter the RID:
set /p RID=
java -Dconfig=..\..\conf\connector-client.properties -DconfigValidation=..\..\conf\validation.properties -Dsession=..\..\examples\command-line\session.xml -DsystemKeystore=..\..\examples\command-line\systemKeystoreProperties.xml be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine getPrescription %RID%
pause