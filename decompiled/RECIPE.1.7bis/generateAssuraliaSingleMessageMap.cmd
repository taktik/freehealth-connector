@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\gfddpp\assuralia\singlemessagemap"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.gfddpp.assuralia "%~dp0\xsd\assuralia-single-message-map.xsd"
pause