@echo off

RaptorXML xslt --xslt-version=2 --input="../../xsd/smc-v2.0.1/single-message-maximal-dataset-sample.xml" --output="../../xsd/smc-v2.0.1/single-message-minimal-dataset-sample.xml" %* "MappingMapToMinDataSet.xslt"
IF ERRORLEVEL 1 EXIT/B %ERRORLEVEL%
