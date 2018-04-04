cd ..\..\..\lib\java
call setEnv
set CLASSPATH="%CLASSPATH%;..\..\examples\command-line\recipe\"
java -Dmock=true -Dconfig=..\..\conf\connector-client.properties be.business.connector.recipe.prescriber.PrescriberIntegrationModuleCommandLine createPrescription true 84071230581 ..\..\examples\resources\sample-prescription.xml P0
pause