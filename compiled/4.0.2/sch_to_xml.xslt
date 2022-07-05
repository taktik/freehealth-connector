<?xml version="1.0" encoding="UTF-8"?>
<!-- 
Property : eHealth
Author   : eHealth 
Date     : 01/06/2010
Version 1.0 Written for ehValidator library and tool

This is an XSLT file.
The purpose of this XSLT is to transform the schematron output of a Sumehr Validation in XML format.
(The schematron report is produced using the schematron file "sumehr-validation.sch").

XSLT file: http://www.w3.org/standards/xml/transformation#xslt
schematron: http://www.schematron.com
Kmehr  definition: https://www.ehealth.fgov.be/standards/kmehr/en/home/home/index.xml
Sumehr definition: https://www.ehealth.fgov.be/standards/kmehr/en/transaction_detail/home/transactions/transaction_detail/Sumehr-1-1.xml
eHealth: https://www.ehealth.fgov.be
-->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:svrl="http://purl.oclc.org/dsdl/svrl">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/">
		<report>
			<header>
				<validationResult>
					<errors>
						<xsl:value-of select="count(//svrl:failed-assert[@role='ERROR'])"/>
					</errors>
					<warnings>
						<xsl:value-of select="count(//svrl:failed-assert[@role='WARNING'])"/>
					</warnings>
				</validationResult>
                <!-- not used
                <structureOK>
                    <xsl:choose>
                        <xsl:when test="count(//svrl:failed-assert[@flag='not-viewable']) = 0">
                            <xsl:text>true</xsl:text>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:text>false</xsl:text>
                        </xsl:otherwise>
                    </xsl:choose>
                </structureOK>
                -->
			</header>
			<messages>
				<xsl:apply-templates/>
			</messages>
		</report>
	</xsl:template>
	<xsl:template match="svrl:failed-assert">
		<xsl:variable name="count">
			<xsl:number/>
		</xsl:variable>
		<message>
			<number><xsl:value-of select="$count"/></number>
			<xsl:apply-templates select="@role"/>
			<xsl:apply-templates select="@id"/>
			<xsl:apply-templates select="svrl:text"/>
			<xsl:apply-templates select="@line"/>
			<xsl:apply-templates select="@col"/>
            <xsl:apply-templates select="@location"/>
            <xsl:apply-templates select="@test"/>
		</message>
	</xsl:template>
	<xsl:template match="@role">
		<xsl:variable name="role">
			<xsl:value-of select="."/>
		</xsl:variable>
		<type>
			<xsl:if test="$role = 'ERROR'">
			<en>ERROR</en>
			<fr>ERREUR</fr>
			<nl>FOUT</nl>
			<de>FEHLER</de>
			</xsl:if>
			<xsl:if test="$role = 'WARNING'">
			<en>WARNING</en>
			<fr>AVERTISSEMENT</fr>
			<nl>WAARSCHUWING</nl>
			<de>WARNUNGEN</de>
			</xsl:if>
		</type>
	</xsl:template>
	<xsl:template match="@id">
		<subject>
			<xsl:value-of select="."/>
		</subject>
	</xsl:template>    
    <!-- format: en_||_fr_||_nl_||_de -->
	<xsl:template match="svrl:text">
		<description>
            <xsl:variable name="after-en-messages"><xsl:value-of select="substring-after( . , '_||_' )"/></xsl:variable>
            <xsl:variable name="after-fr-messages"><xsl:value-of select="substring-after( $after-en-messages , '_||_' )"/></xsl:variable>
            <en><xsl:value-of select="substring-before( . , '_||_' )"/></en>
            <fr><xsl:value-of select="substring-before( $after-en-messages, '_||_' )"/></fr>
            <nl><xsl:value-of select="substring-before( $after-fr-messages, '_||_' )"/></nl>
            <de><xsl:value-of select="substring-after( $after-fr-messages, '_||_' )"/></de>
		</description>
	</xsl:template>
	<xsl:template match="@line">
		<line>
			<xsl:value-of select="."/>
		</line>
	</xsl:template>
	<xsl:template match="@col">
		<col>
			<xsl:value-of select="."/>
		</col>
	</xsl:template>
    <xsl:template match="@location">
        <location>
            <xsl:value-of select="."/>
        </location>
    </xsl:template>
    <xsl:template match="@test">
        <test>
            <xsl:value-of select="."/>
        </test>
    </xsl:template>
	<xsl:template match="svrl:successful-report">
	</xsl:template>
</xsl:stylesheet>
