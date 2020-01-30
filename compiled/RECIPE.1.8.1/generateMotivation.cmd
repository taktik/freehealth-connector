@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\gfddpp\motivation"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.gfddpp.motivation "%~dp0\xsd\motivation.xsd"
pause