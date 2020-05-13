@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\gfddpp\systemservices\v2"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.gfddpp.systemservices.v2 "%~dp0\xsd\system-services-v20130927.xsd" -b "%~dp0\bindings.xml" -extension
pause