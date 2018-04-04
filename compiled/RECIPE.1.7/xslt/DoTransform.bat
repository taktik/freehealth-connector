@echo off

RaptorXML xslt --xslt-version=2 --input="single-message-oa-1.0.xml" --output="../xsd/smc-v2/single-message/maindoc/single-message-oa-1.0.xml" %* "MappingMapTosingle-message-oa-1_0.xslt"
IF ERRORLEVEL 1 EXIT/B %ERRORLEVEL%
