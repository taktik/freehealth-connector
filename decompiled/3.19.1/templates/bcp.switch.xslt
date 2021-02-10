<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:urn="urn:be:fgov:ehealth:errors:soa:v1">
    <xsl:output method="text" version="1.0" encoding="UTF-8" indent="no"/>

    <xsl:variable name="errorCodeToSwitch" select="'|SOA-01001|SOA-01002|'"/>

    <xsl:template match="soapenv:Fault/detail/*/Code">
        <xsl:if test="contains($errorCodeToSwitch, concat('|', text(), '|'))">
            <xsl:text>SWITCH</xsl:text>
        </xsl:if>
    </xsl:template>

    <xsl:template match="*">
        <xsl:apply-templates select="child::*"/>
    </xsl:template>

</xsl:stylesheet>