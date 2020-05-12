@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\gfddpp\assuralia"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.gfddpp.assuralia "%~dp0\xsd\assuralia-request-parameters-v20130916.xsd"
pause