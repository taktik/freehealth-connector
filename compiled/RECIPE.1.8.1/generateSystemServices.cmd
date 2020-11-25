@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\gfddpp\systemservices\v1"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" "%~dp0\xsd\system-services-v20130708.xsd" -b "%~dp0\bindings.xml" -extension
pause