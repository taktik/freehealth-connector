@echo off
echo System keystore password:
set /p SYSTEM_PASS=
echo System keystore path :
set /p SYSTEM_PATH=
echo System keystore directory:
set /p SYSTEM_DIRECTORY=
echo System keystore riziv :
set /p SYSTEM_RIZIV=
cd ..\..\..\lib\java
call setEnv
set CLASSPATH="%CLASSPATH%;..\..\examples\command-line\general\"
if "%SYSTEM_PASS%"=="" set SYSTEM_PASS=null
if "%SYSTEM_PATH%"=="" set SYSTEM_PATH=null
if "%SYSTEM_DIRECTORY%"=="" set SYSTEM_DIRECTORY=null
if "%SYSTEM_RIZIV%"=="" set SYSTEM_RIZIV=null
java -Dconfig=..\..\conf\connector-client.properties -DconfigValidation=..\..\conf\validation.properties be.business.connector.session.SessionUtilCommandLine createSession ..\..\examples\command-line\session.xml ..\..\examples\command-line\systemKeystoreProperties.xml %SYSTEM_PASS% %SYSTEM_PATH% %SYSTEM_DIRECTORY% %SYSTEM_RIZIV%
pause