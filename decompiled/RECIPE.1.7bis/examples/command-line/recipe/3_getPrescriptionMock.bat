@echo off
cd ..\..\..\lib\java
call setEnv
set CLASSPATH="%CLASSPATH%;..\..\examples\command-line\recipe\"
echo Enter the RID:
set /p RID=
java -Dmock=true -Dconfig=..\..\conf\connector-client.properties be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine getPrescription %RID%
pause