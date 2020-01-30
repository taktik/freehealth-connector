cd ..\..\..\lib\java
call setEnv
set CLASSPATH="%CLASSPATH%;..\..\examples\command-line\recipe\"
java -Dmock=true -Dconfig=..\..\conf\connector-client.properties be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine listOpenPrescription 37041926666
pause