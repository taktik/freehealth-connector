@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\standards\gfddpp\patient\signature"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.standards.gfddpp.patient.signature "%~dp0\xsd\patient-signature.xsd"
pause