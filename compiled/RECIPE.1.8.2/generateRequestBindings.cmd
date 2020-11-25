@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\standards\gfddpp\request"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.standards.gfddpp.request "%~dp0\xsd\request-message-parameters-v20160808.xsd"