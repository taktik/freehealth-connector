@echo off
cd ..\..\..\lib\java
call setEnv
set CLASSPATH="%CLASSPATH%;..\..\examples\command-line\general\"
java -Dconfig=..\..\conf\recipe-connector.properties be.business.connector.session.SessionUtilCommandLine
pause