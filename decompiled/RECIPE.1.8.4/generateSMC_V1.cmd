@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\standards\smoa"
del /Q /S /A:-R "%~dp0\..\java\be\fgov"
del /Q /S /A:-R "%~dp0\..\java\org"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" "%~dp0\xsd\smc-v1\single-message\maindoc\single-message-oa-1.0.xsd" -b "%~dp0\bindings.xml" -extension
pause