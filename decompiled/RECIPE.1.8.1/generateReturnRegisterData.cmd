@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\gfddpp\rtrn\registedata"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.gfddpp.rtrn.registerdata "%~dp0\xsd\return-registerdata-to-softwarevendors.xsd"
pause