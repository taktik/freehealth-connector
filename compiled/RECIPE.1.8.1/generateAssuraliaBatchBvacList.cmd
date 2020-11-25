@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\gfddpp\assuralia\batch"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.gfddpp.assuralia.batch "%~dp0\xsd\assuralia-batches-bvac-list-v20130926.xsd"
pause