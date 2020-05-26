@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\gfddpp\common\binding"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.gfddpp.common.binding "%~dp0\xsd\tipsys-audit.xsd"
pause