@echo off

RaptorXML xslt --xslt-version=2 --input="../../../../../../be-apb-gfddpp-esb-registerdata-flow2/src/test/resources/bvac-single-message-25bvacid.xml" --output="../../../../../../../gfd-dpp/be-apb-gfddpp-services-smc/src/main/resources/xsd/smc-v2.0.3/single-message/maindoc/single-message-oa-1.0.xml" %* "MappingMapTosingle-message-oa-1_0.xslt"
IF ERRORLEVEL 1 EXIT/B %ERRORLEVEL%
