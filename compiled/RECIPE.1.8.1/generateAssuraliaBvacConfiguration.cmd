@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\gfddpp\assuralia\bvac"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.gfddpp.assuralia.bvac "%~dp0\xsd\assuralia-bvac-configuration-v20130923.xsd"
pause