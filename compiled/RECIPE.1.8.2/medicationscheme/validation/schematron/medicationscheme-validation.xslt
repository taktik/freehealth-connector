<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet xmlns:iso="http://purl.oclc.org/dsdl/schematron"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xhtml="http://www.w3.org/1999/xhtml"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:saxon="http://saxon.sf.net/"
                xmlns:schold="http://www.ascc.net/xml/schematron"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:kmehr="http://www.ehealth.fgov.be/standards/kmehr/schema/v1"
                version="2.0"><!--Implementers: please note that overriding process-prolog or process-root is
    the preferred method for meta-stylesheets to use where possible. -->
    <xsl:param name="archiveDirParameter"/>
    <xsl:param name="archiveNameParameter"/>
    <xsl:param name="fileNameParameter"/>
    <xsl:param name="fileDirParameter"/>
    <xsl:variable name="document-uri">
        <xsl:value-of select="document-uri(/)"/>
    </xsl:variable>

    <!--PHASES-->


    <!--PROLOG-->
    <xsl:output xmlns:svrl="http://purl.oclc.org/dsdl/svrl" method="xml"
                omit-xml-declaration="no"
                standalone="yes"
                indent="yes"/>

    <!--XSD TYPES FOR XSLT2-->


    <!--KEYS AND FUNCTIONS-->


    <!--DEFAULT RULES-->


    <!--MODE: SCHEMATRON-SELECT-FULL-PATH-->
    <!--This mode can be used to generate an ugly though full XPath for locators-->
    <xsl:template match="*" mode="schematron-select-full-path">
        <xsl:apply-templates select="." mode="schematron-get-full-path"/>
    </xsl:template>

    <!--MODE: SCHEMATRON-FULL-PATH-->
    <!--This mode can be used to generate an ugly though full XPath for locators-->
    <xsl:template match="*" mode="schematron-get-full-path">
        <xsl:apply-templates select="parent::*" mode="schematron-get-full-path"/>
        <xsl:text>/</xsl:text>
        <xsl:choose>
            <xsl:when test="namespace-uri()=''">
                <xsl:value-of select="name()"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>*:</xsl:text>
                <xsl:value-of select="local-name()"/>
                <xsl:text>[namespace-uri()='</xsl:text>
                <xsl:value-of select="namespace-uri()"/>
                <xsl:text>']</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:variable name="preceding"
                      select="count(preceding-sibling::*[local-name()=local-name(current())                                   and namespace-uri() = namespace-uri(current())])"/>
        <xsl:text>[</xsl:text>
        <xsl:value-of select="1+ $preceding"/>
        <xsl:text>]</xsl:text>
    </xsl:template>
    <xsl:template match="@*" mode="schematron-get-full-path">
        <xsl:apply-templates select="parent::*" mode="schematron-get-full-path"/>
        <xsl:text>/</xsl:text>
        <xsl:choose>
            <xsl:when test="namespace-uri()=''">@<xsl:value-of select="name()"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>@*[local-name()='</xsl:text>
                <xsl:value-of select="local-name()"/>
                <xsl:text>' and namespace-uri()='</xsl:text>
                <xsl:value-of select="namespace-uri()"/>
                <xsl:text>']</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <!--MODE: SCHEMATRON-FULL-PATH-2-->
    <!--This mode can be used to generate prefixed XPath for humans-->
    <xsl:template match="node() | @*" mode="schematron-get-full-path-2">
        <xsl:for-each select="ancestor-or-self::*">
            <xsl:text>/</xsl:text>
            <xsl:value-of select="name(.)"/>
            <xsl:if test="preceding-sibling::*[name(.)=name(current())]">
                <xsl:text>[</xsl:text>
                <xsl:value-of select="count(preceding-sibling::*[name(.)=name(current())])+1"/>
                <xsl:text>]</xsl:text>
            </xsl:if>
        </xsl:for-each>
        <xsl:if test="not(self::*)">
            <xsl:text/>/@<xsl:value-of select="name(.)"/>
        </xsl:if>
    </xsl:template>
    <!--MODE: SCHEMATRON-FULL-PATH-3-->
    <!--This mode can be used to generate prefixed XPath for humans
        (Top-level element has index)-->
    <xsl:template match="node() | @*" mode="schematron-get-full-path-3">
        <xsl:for-each select="ancestor-or-self::*">
            <xsl:text>/</xsl:text>
            <xsl:value-of select="name(.)"/>
            <xsl:if test="parent::*">
                <xsl:text>[</xsl:text>
                <xsl:value-of select="count(preceding-sibling::*[name(.)=name(current())])+1"/>
                <xsl:text>]</xsl:text>
            </xsl:if>
        </xsl:for-each>
        <xsl:if test="not(self::*)">
            <xsl:text/>/@<xsl:value-of select="name(.)"/>
        </xsl:if>
    </xsl:template>

    <!--MODE: GENERATE-ID-FROM-PATH -->
    <xsl:template match="/" mode="generate-id-from-path"/>
    <xsl:template match="text()" mode="generate-id-from-path">
        <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
        <xsl:value-of select="concat('.text-', 1+count(preceding-sibling::text()), '-')"/>
    </xsl:template>
    <xsl:template match="comment()" mode="generate-id-from-path">
        <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
        <xsl:value-of select="concat('.comment-', 1+count(preceding-sibling::comment()), '-')"/>
    </xsl:template>
    <xsl:template match="processing-instruction()" mode="generate-id-from-path">
        <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
        <xsl:value-of select="concat('.processing-instruction-', 1+count(preceding-sibling::processing-instruction()), '-')"/>
    </xsl:template>
    <xsl:template match="@*" mode="generate-id-from-path">
        <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
        <xsl:value-of select="concat('.@', name())"/>
    </xsl:template>
    <xsl:template match="*" mode="generate-id-from-path" priority="-0.5">
        <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
        <xsl:text>.</xsl:text>
        <xsl:value-of select="concat('.',name(),'-',1+count(preceding-sibling::*[name()=name(current())]),'-')"/>
    </xsl:template>

    <!--MODE: GENERATE-ID-2 -->
    <xsl:template match="/" mode="generate-id-2">U</xsl:template>
    <xsl:template match="*" mode="generate-id-2" priority="2">
        <xsl:text>U</xsl:text>
        <xsl:number level="multiple" count="*"/>
    </xsl:template>
    <xsl:template match="node()" mode="generate-id-2">
        <xsl:text>U.</xsl:text>
        <xsl:number level="multiple" count="*"/>
        <xsl:text>n</xsl:text>
        <xsl:number count="node()"/>
    </xsl:template>
    <xsl:template match="@*" mode="generate-id-2">
        <xsl:text>U.</xsl:text>
        <xsl:number level="multiple" count="*"/>
        <xsl:text>_</xsl:text>
        <xsl:value-of select="string-length(local-name(.))"/>
        <xsl:text>_</xsl:text>
        <xsl:value-of select="translate(name(),':','.')"/>
    </xsl:template>
    <!--Strip characters--><xsl:template match="text()" priority="-1"/>

    <!--SCHEMA SETUP-->
    <xsl:template match="/">
        <svrl:schematron-output xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                title="Checking a Medication Scheme document"
                                schemaVersion="ISO19757-3">
            <xsl:comment>
                <xsl:value-of select="$archiveDirParameter"/>   
                <xsl:value-of select="$archiveNameParameter"/>  
                <xsl:value-of select="$fileNameParameter"/>  
                <xsl:value-of select="$fileDirParameter"/>
            </xsl:comment>
            <svrl:ns-prefix-in-attribute-values uri="http://www.ehealth.fgov.be/standards/kmehr/schema/v1" prefix="kmehr"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">header.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Header structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M2"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">folder.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Folder structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M3"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">transaction.structure.generic.checks</xsl:attribute>
                <xsl:attribute name="name">General transaction structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M4"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">medicationscheme.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Medication Scheme checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M5"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">transaction.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Healthcare elements checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M6"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">transaction.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Healthcare elements checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M7"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">transaction.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Treatment suspension checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M8"/>
        </svrl:schematron-output>
    </xsl:template>

    <!--SCHEMATRON PATTERNS-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Checking a Medication Scheme document</svrl:text>

    <!--PATTERN header.structure.checksHeader structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Header structure checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:header" priority="1002" mode="M2">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:header"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="kmehr:standard/kmehr:cd = '20120401'"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="kmehr:standard/kmehr:cd = '20120401'">
                    <xsl:attribute name="id">version</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The cd in &lt;standard&gt; must be equal to "20120401". (Current: <xsl:text/>
                        <xsl:value-of select="kmehr:standard/kmehr:cd"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:id[text()]) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:id[text()]) &gt; 0">
                    <xsl:attribute name="id">version</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The id in &lt;header&gt; must be non-empty. (Current: <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M2"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:header/kmehr:recipient/kmehr:hcparty"
                  priority="1001"
                  mode="M2">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:header/kmehr:recipient/kmehr:hcparty"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="kmehr:name/lower-case(text()) = 'vitalink'"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="kmehr:name/lower-case(text()) = 'vitalink'">
                    <xsl:attribute name="id">recipient-name</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The name of the recipient must be "VITALINK". (Current: <xsl:text/>
                        <xsl:value-of select="kmehr:name/lower-case(text())"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="kmehr:cd/lower-case(text()) = 'application'"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="kmehr:cd/lower-case(text()) = 'application'">
                    <xsl:attribute name="id">recipient-cd</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The cd in &lt;recipient&gt; must be "application". (Current: <xsl:text/>
                        <xsl:value-of select="kmehr:cd/lower-case(text())"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M2"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:header/kmehr:sender/kmehr:hcparty"
                  priority="1000"
                  mode="M2">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:header/kmehr:sender/kmehr:hcparty"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:cd[text()]) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:cd[text()]) &gt; 0">
                    <xsl:attribute name="id">recipient-cd</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The cd in &lt;hcparty&gt; (sender) must be non-empty. (Current: <xsl:text/>
                        <xsl:value-of select="count(kmehr:cd[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M2"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M2"/>
    <xsl:template match="@*|node()" priority="-2" mode="M2">
        <xsl:apply-templates select="*" mode="M2"/>
    </xsl:template>

    <!--PATTERN folder.structure.checksFolder structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Folder structure checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage" priority="1002" mode="M3">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="kmehr:kmehrmessage"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:folder) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:folder) = 1">
                    <xsl:attribute name="id">Folder</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A medication scheme element must contain exactly 1 folder. (Current count: <xsl:text/>
                        <xsl:value-of select="count(kmehr:folder)"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M3"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction/kmehr:author/kmehr:hcparty"
                  priority="1001"
                  mode="M3">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction/kmehr:author/kmehr:hcparty"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:cd[text()]) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:cd[text()]) &gt; 0">
                    <xsl:attribute name="id">recipient-cd</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The cd in &lt;hcparty&gt; (author) must be non-empty. (Current: <xsl:text/>
                        <xsl:value-of select="count(kmehr:cd[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M3"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:patient" priority="1000" mode="M3">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:patient"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:firstname[text()]) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:firstname[text()]) &gt; 0">
                    <xsl:attribute name="id">recipient-cd</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The patient's firstname must be non-empty. (Current: <xsl:text/>
                        <xsl:value-of select="count(kmehr:firstname[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:familyname[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:familyname[text()]) = 1">
                    <xsl:attribute name="id">recipient-cd</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The patient's familyname must be non-empty. (Current: <xsl:text/>
                        <xsl:value-of select="count(kmehr:familyname[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:id[text()]) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:id[text()]) &gt; 0">
                    <xsl:attribute name="id">recipient-cd</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The patient's identification number must be non-empty. (Current: <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M3"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M3"/>
    <xsl:template match="@*|node()" priority="-2" mode="M3">
        <xsl:apply-templates select="*" mode="M3"/>
    </xsl:template>

    <!--PATTERN transaction.structure.generic.checksGeneral transaction structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">General transaction structure checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:content/kmehr:medicinalproduct"
                  priority="1003"
                  mode="M4">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:content/kmehr:medicinalproduct"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:intendedcd[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:intendedcd[text()]) = 1">
                    <xsl:attribute name="id">intededcd</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The code of a medicinalproduct must be non-empty. (Current count: <xsl:text/>
                        <xsl:value-of select="count(kmehr:intendedcd[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:intendedname[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:intendedname[text()]) = 1">
                    <xsl:attribute name="id">intededname</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The name of a medicinalproduct must be non-empty. (Current count: <xsl:text/>
                        <xsl:value-of select="count(kmehr:intendedname[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M4"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:content/kmehr:substanceproduct"
                  priority="1002"
                  mode="M4">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:content/kmehr:substanceproduct"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:intendedcd[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:intendedcd[text()]) = 1">
                    <xsl:attribute name="id">intededcd</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The code of a substanceproduct must be non-empty. (Current count: <xsl:text/>
                        <xsl:value-of select="count(kmehr:intendedcd[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:intendedname[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:intendedname[text()]) = 1">
                    <xsl:attribute name="id">intededname</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The name of a substanceproduct must be non-empty. (Current count: <xsl:text/>
                        <xsl:value-of select="count(kmehr:intendedname[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M4"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:content/kmehr:cd[@S='CD-EAN']"
                  priority="1001"
                  mode="M4">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:content/kmehr:cd[@S='CD-EAN']"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(.[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(.[text()]) = 1">
                    <xsl:attribute name="id">intededcd</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The code of an EAN-product must be non-empty. (Current count: <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='CD-EAN'][text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(../../kmehr:text[text()]) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(../../kmehr:text[text()]) &gt; 0">
                    <xsl:attribute name="id">intededname</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The name of an EAN-product must be non-empty. (Current count: <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='CD-EAN']/../../text[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M4"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:content[kmehr:compoundprescription]"
                  priority="1000"
                  mode="M4">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:content[kmehr:compoundprescription]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:compoundprescription[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:compoundprescription[text()]) = 1">
                    <xsl:attribute name="id">intededcd</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The textual description of the compoundprescription must be non-empty. (Current count: <xsl:text/>
                        <xsl:value-of select="count(kmehr:compoundprescription[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(../kmehr:text[text()]) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(../kmehr:text[text()]) &gt; 0">
                    <xsl:attribute name="id">intededname</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The name of the compoundprescription must be non-empty. (Current count: <xsl:text/>
                        <xsl:value-of select="count(../kmehr:text[text()])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M4"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M4"/>
    <xsl:template match="@*|node()" priority="-2" mode="M4">
        <xsl:apply-templates select="*" mode="M4"/>
    </xsl:template>

    <!--PATTERN medicationscheme.structure.checksMedication Scheme checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Medication Scheme checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder" priority="1005" mode="M5">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:transaction/kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:transaction/kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']) = 1">
                    <xsl:attribute name="id">Folder</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A medication scheme element must contain exactly 1 "medicationschemeelement" transaction. (Current count: <xsl:text/>
                        <xsl:value-of select="count(kmehr:folder/kmehr:transaction/kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement'])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M5"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]"
                  priority="1004"
                  mode="M5">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']]/kmehr:content/kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='adaptationflag']) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']]/kmehr:content/kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='adaptationflag']) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        There must be exactly 1 "healthcareelement"-item containing an "adaptationflag" cd.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:item/kmehr:cd[lower-case(.)='medication']) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:item/kmehr:cd[lower-case(.)='medication']) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "medicationschemeelement"-transaction must contain exactly 1 "item" containing a "medication" cd.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M5"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='medication']]"
                  priority="1003"
                  mode="M5">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='medication']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:beginmoment) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:beginmoment) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "medication"-item must contain exactly 1 &lt;beginmoment&gt;.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test=" count(kmehr:content/kmehr:medicinalproduct) +         count(kmehr:content/kmehr:substanceproduct) +          count(kmehr:content/kmehr:compoundprescription) +          count(kmehr:content/kmehr:cd[@S='CD-EAN']) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:medicinalproduct) + count(kmehr:content/kmehr:substanceproduct) + count(kmehr:content/kmehr:compoundprescription) + count(kmehr:content/kmehr:cd[@S='CD-EAN']) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "medication"-item must contain 1 of the following: &lt;medicinalproduct&gt;, &lt;substanceproduct&gt;, &lt;compoundprescription&gt; or a cd from the "CD-EAN"-list.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test=" count(kmehr:posology) +         count(kmehr:regimen) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:posology) + count(kmehr:regimen) &lt; 2">
                    <xsl:attribute name="id">MS-XOR-Posology-Regimen</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        &lt;posology&gt; and &lt;regimen&gt; may not be used together in the same medicationschemeelement.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M5"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:frequency"
                  priority="1002"
                  mode="M5">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:frequency"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:periodicity) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:periodicity) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The tag &lt;frequency&gt; must be used with &lt;periodicity&gt;.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M5"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:posology"
                  priority="1001"
                  mode="M5">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:posology"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:text[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:text[text()]) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The tag &lt;posology&gt; must always be used with &lt;text&gt;.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M5"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:content[kmehr:cd[@S='CD-EAN'] or kmehr:compoundprescription]"
                  priority="1000"
                  mode="M5">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='medication']]/kmehr:content[kmehr:cd[@S='CD-EAN'] or kmehr:compoundprescription]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(../kmehr:text) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(../kmehr:text) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A &lt;compoundprescription&gt; or CD-EAN cd needs to be accompanied by a textual description in a &lt;text&gt;.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M5"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M5"/>
    <xsl:template match="@*|node()" priority="-2" mode="M5">
        <xsl:apply-templates select="*" mode="M5"/>
    </xsl:template>

    <!--PATTERN transaction.structure.checksHealthcare elements checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Healthcare elements checks</svrl:text>

    <!--RULE -->
    <xsl:template match="//kmehr:cd[@S='CD-MS-MEDICATIONTYPE']" priority="1003" mode="M6">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="//kmehr:cd[@S='CD-MS-MEDICATIONTYPE']"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test=" lower-case(.) = 'onprescription' or         lower-case(.) = 'otc' or         lower-case(.) = 'other' "/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="lower-case(.) = 'onprescription' or lower-case(.) = 'otc' or lower-case(.) = 'other'">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        CD-MS-MEDICATIONTYPE must be one of the following values "onprescription", "otc" or "other".
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M6"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="//kmehr:cd[@S='CD-MS-ORIGIN']" priority="1002" mode="M6">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="//kmehr:cd[@S='CD-MS-ORIGIN']"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test=" lower-case(.) = 'regularprocess' or         lower-case(.) = 'recorded' "/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="lower-case(.) = 'regularprocess' or lower-case(.) = 'recorded'">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        CD-MS-ORIGIN must be one of the following values "regularprocess" or "recorded".
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M6"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="//kmehr:cd[@S='CD-MS-ADAPTATION']" priority="1001" mode="M6">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="//kmehr:cd[@S='CD-MS-ADAPTATION']"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test=" lower-case(.) = 'nochanges' or         lower-case(.) = 'medication' or         lower-case(.) = 'posology' or         lower-case(.) = 'treatmentsuspension' "/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="lower-case(.) = 'nochanges' or lower-case(.) = 'medication' or lower-case(.) = 'posology' or lower-case(.) = 'treatmentsuspension'">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        CD-MS-ADAPTATION must be one of the following values "nochanges", "medication", "posology" or "teatmentsuspension".
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M6"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="//kmehr:cd[@S='CD-ITEM-MS']" priority="1000" mode="M6">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="//kmehr:cd[@S='CD-ITEM-MS']"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test=" lower-case(.) = 'origin' or         lower-case(.) = 'adaptationflag' or         lower-case(.) = 'medicationuse' or         lower-case(.) = 'medicationtype' or         lower-case(.) = 'begincondition' or         lower-case(.) = 'endcondition' "/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="lower-case(.) = 'origin' or lower-case(.) = 'adaptationflag' or lower-case(.) = 'medicationuse' or lower-case(.) = 'medicationtype' or lower-case(.) = 'begincondition' or lower-case(.) = 'endcondition'">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        CD-ITEM-MS must be one of the following values "origin", "adaptationflag", "medicationuse", "medicationtype", "begincondition" or "endcondition".
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M6"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M6"/>
    <xsl:template match="@*|node()" priority="-2" mode="M6">
        <xsl:apply-templates select="*" mode="M6"/>
    </xsl:template>

    <!--PATTERN transaction.structure.checksHealthcare elements checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Healthcare elements checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']]/kmehr:content[kmehr:cd[@S='CD-ITEM-MS']]"
                  priority="1006"
                  mode="M7">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']]/kmehr:content[kmehr:cd[@S='CD-ITEM-MS']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:cd[@S='CD-ITEM-MS']) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:cd[@S='CD-ITEM-MS']) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Each "healthcareelement-item must containt exactly 1 cd of list "CD-ITEM-MS" (possible values are "origin", "adaptationflag", "medicationuse", "medicationtype", "begincondition" and "endcondition").
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M7"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']]/kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='adaptationflag']]"
                  priority="1005"
                  mode="M7">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']]/kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='adaptationflag']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="exists(kmehr:cd[@S='CD-MS-ADAPTATION'])"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="exists(kmehr:cd[@S='CD-MS-ADAPTATION'])">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        An "adaptationflag" must contain at least 1 cd of list "CD-MS-ADAPTATION" (possible values are "nochanges", "medication", "posology" and "treatmentsuspension").
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M7"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']]/kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='origin']]"
                  priority="1004"
                  mode="M7">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']]/kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='origin']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:cd[@S='CD-MS-ORIGIN']) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:cd[@S='CD-MS-ORIGIN']) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        An "origin" must contain exactly 1 cd of list "CD-MS-ORIGIN" (possible values are "regularprocess" and "recorded").
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M7"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']]/kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='medicationtype']]"
                  priority="1003"
                  mode="M7">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']]/kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='medicationtype']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:cd[@S='CD-MS-MEDICATIONTYPE']) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:cd[@S='CD-MS-MEDICATIONTYPE']) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "medicationtype" must contain exactly 1 cd of list "CD-MS-MEDICATIONTYPE" (possible values are "onprescription", "otc" and "other").
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M7"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']][kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='medicationuse']]]"
                  priority="1002"
                  mode="M7">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']][kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='medicationuse']]]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content/kmehr:text[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:text[text()]) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "medicationuse" must contain exactly 1 non-empty &lt;text&gt;.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M7"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']][kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='begincondition']]]"
                  priority="1001"
                  mode="M7">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']][kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='begincondition']]]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content/kmehr:text[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:text[text()]) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "begincondition" must contain exactly 1 non-empty &lt;text&gt;.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M7"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']][kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='endcondition']]]"
                  priority="1000"
                  mode="M7">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='medicationschemeelement']]/kmehr:item[kmehr:cd[lower-case(.)='healthcareelement']][kmehr:content[kmehr:cd[@S='CD-ITEM-MS' and lower-case(.)='endcondition']]]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content/kmehr:text[text()]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:text[text()]) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "endcondition" must contain exactly 1 non-empty &lt;text&gt;.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M7"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M7"/>
    <xsl:template match="@*|node()" priority="-2" mode="M7">
        <xsl:apply-templates select="*" mode="M7"/>
    </xsl:template>

    <!--PATTERN transaction.structure.checksTreatment suspension checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Treatment suspension checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='treatmentsuspension']]"
                  priority="1001"
                  mode="M8">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='treatmentsuspension']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:item/kmehr:cd[lower-case(.)='medication']) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:item/kmehr:cd[lower-case(.)='medication']) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Each "treatmentsuspension"-transaction must contain exactly 1 "item" containing a "medication" cd.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M8"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='treatmentsuspension']]/kmehr:item[kmehr:cd[.='medication']]"
                  priority="1000"
                  mode="M8">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:kmehrmessage/kmehr:folder/kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and lower-case(.)='treatmentsuspension']]/kmehr:item[kmehr:cd[.='medication']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test=" count(kmehr:content/kmehr:medicinalproduct) +         count(kmehr:content/kmehr:substanceproduct) +          count(kmehr:content/kmehr:compoundprescription) +          count(kmehr:content/kmehr:cd[@S='CD-EAN']) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:medicinalproduct) + count(kmehr:content/kmehr:substanceproduct) + count(kmehr:content/kmehr:compoundprescription) + count(kmehr:content/kmehr:cd[@S='CD-EAN']) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "medication"-item must contain 1 of the following: &lt;medicinalproduct&gt;, &lt;substanceproduct&gt;, &lt;compoundprescription&gt; or a cd from the "CD-EAN"-list.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:beginmoment) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:beginmoment) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Each "treatmentsuspension"-transaction must contain exactly 1 &lt;beginmoment&gt;.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:lifecycle[kmehr:cd[@S='CD-LIFECYCLE' and (lower-case(.)='suspended' or lower-case(.)='stopped')]]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:lifecycle[kmehr:cd[@S='CD-LIFECYCLE' and (lower-case(.)='suspended' or lower-case(.)='stopped')]]) = 1">
                    <xsl:attribute name="id">MS-adaptationflag</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Each "treatmentsuspension"-transaction must contain exactly 1 &lt;lifecycle&gt; (possible values are "suspended" and "stopped").
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M8"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M8"/>
    <xsl:template match="@*|node()" priority="-2" mode="M8">
        <xsl:apply-templates select="*" mode="M8"/>
    </xsl:template>
</xsl:stylesheet>