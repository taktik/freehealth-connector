@echo off
del /Q /S /A:-R "%~dp0\..\java\be\apb\gfddpp\productfilter"
"%java_home%\bin\xjc.exe" -d "%~dp0\..\java" -p be.apb.gfddpp.productfilter "%~dp0\xsd\productFilter-v2013087.xsd"
pause