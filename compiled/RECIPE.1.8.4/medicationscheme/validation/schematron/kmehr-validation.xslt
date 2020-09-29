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
        <xsl:value-of
                select="concat('.processing-instruction-', 1+count(preceding-sibling::processing-instruction()), '-')"/>
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
    <!--Strip characters-->
    <xsl:template match="text()" priority="-1"/>

    <!--SCHEMA SETUP-->
    <xsl:template match="/">
        <svrl:schematron-output xmlns:svrl="http://purl.oclc.org/dsdl/svrl" title="Checking a Kmehr document"
                                schemaVersion="ISO19757-3">
            <xsl:comment>
                <xsl:value-of select="$archiveDirParameter"/>  
                <xsl:value-of select="$archiveNameParameter"/>  
                <xsl:value-of select="$fileNameParameter"/>  
                <xsl:value-of select="$fileDirParameter"/>
            </xsl:comment>
            <svrl:ns-prefix-in-attribute-values uri="http://www.ehealth.fgov.be/standards/kmehr/schema/v1"
                                                prefix="kmehr"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">schema-kmehr.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Kmehr schema check</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M2"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">id-kmehr.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Kmehr id-kmehr structure checks for key elements</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M3"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">local-DN.structure.checks</xsl:attribute>
                <xsl:attribute name="name">DN attribute for local cd structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M4"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">inss.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Kmehr inss structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M5"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">inss.values.checks</xsl:attribute>
                <xsl:attribute name="name">Kmehr inss values checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M6"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">id-hcparty.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Kmehr id-hcparty structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M7"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">id-hcparty.values.checks</xsl:attribute>
                <xsl:attribute name="name">Kmehr id-hcparty values checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M8"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">id-kmehr.structure.warnings.checks</xsl:attribute>
                <xsl:attribute name="name">Kmehr id-kmehr structure warnings checks for key elements</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M9"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">id-kmehr.values.warnings.checks</xsl:attribute>
                <xsl:attribute name="name">Kmehr id-kmehr values warnings for key elements</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M10"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">items-id-kmehr.values.warnings.checks</xsl:attribute>
                <xsl:attribute name="name">Kmehr id-kmehr values warnings for items</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M11"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">codifications.values.checks</xsl:attribute>
                <xsl:attribute name="name">Codifications values checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M12"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">contents.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Contents structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M13"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">contactperson.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Contact Person structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M14"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">contactperson.structure.warnings.checks</xsl:attribute>
                <xsl:attribute name="name">Contact Person warnings checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M15"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">contacthcparty-contents.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Contacts hcparty content structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M16"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">contacthcparty.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Contacts hcparty structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M17"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">contacthcparty.structure.warnings.checks</xsl:attribute>
                <xsl:attribute name="name">Contacts hcparty warnings checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M18"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">gmdmanager-contents.structure.checks</xsl:attribute>
                <xsl:attribute name="name">GMD Manager content structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M19"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">gmdmanager.structure.checks</xsl:attribute>
                <xsl:attribute name="name">GMD Manager structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M20"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">gmdmanager.structure.warnings.checks</xsl:attribute>
                <xsl:attribute name="name">GMD Manager warnings checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M21"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">healthcareelement.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Healthcare Element structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M22"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">healthcareelement.structure.warnings.checks</xsl:attribute>
                <xsl:attribute name="name">Healthcare Element checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M23"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">currentproblems.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Current Problems structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M24"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">relevantpassivecareelements.warning.checks</xsl:attribute>
                <xsl:attribute name="name">Relevant Passive Care Elements structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M25"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">medication.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Medication structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M26"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">medication.structure.warnings.checks</xsl:attribute>
                <xsl:attribute name="name">Medication warnings checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M27"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">vaccine.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Vaccine structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M28"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">vaccine.structure.warnings.checks</xsl:attribute>
                <xsl:attribute name="name">Vaccine warnings checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M29"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">patientwill.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Patientwill structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M30"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">insurancystatus.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Insurancystatus structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M31"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">careplansubscription.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Careplansubscription structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M32"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">notification.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Notification structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M33"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">notification.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Notification structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M34"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">notification.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Notification structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M35"/>
            <svrl:active-pattern>
                <xsl:attribute name="document">
                    <xsl:value-of select="document-uri(/)"/>
                </xsl:attribute>
                <xsl:attribute name="id">parameter.structure.checks</xsl:attribute>
                <xsl:attribute name="name">Parameter structure checks</xsl:attribute>
                <xsl:apply-templates/>
            </svrl:active-pattern>
            <xsl:apply-templates select="/" mode="M36"/>
        </svrl:schematron-output>
    </xsl:template>

    <!--SCHEMATRON PATTERNS-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Checking a Kmehr document</svrl:text>

    <!--PATTERN schema-kmehr.structure.checksKmehr schema check-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Kmehr schema check</svrl:text>

    <!--RULE -->
    <xsl:template match="/*" priority="1000" mode="M2">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/*"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="kmehr:header"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:header">
                    <xsl:attribute name="id">Namespace</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The correct kmehr namespace must be referenced:
                        xmlns="http://www.ehealth.fgov.be/standards/kmehr/schema/v1"._||_
                        Le "namespace" correct du Kmehr doit être référencé:
                        xmlns="http://www.ehealth.fgov.be/standards/kmehr/schema/v1"._||_
                        De correcte "namespace" van de Kmehr moet als volgt worden gereferenceerd:
                        xmlns="http://www.ehealth.fgov.be/standards/kmehr/schema/v1"._||_
                        Der korrekte Kmehr "namespace" soll wie volgt referenziert werden
                        xmlns="http://www.ehealth.fgov.be/standards/kmehr/schema/v1".
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

    <!--PATTERN id-kmehr.structure.checksKmehr id-kmehr structure checks for key elements-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Kmehr id-kmehr structure checks for key elements</svrl:text>

    <!--RULE -->
    <xsl:template
            match="*[local-name()='header' or local-name()='folder' or local-name()='transaction' or local-name()='heading' or local-name()='item']"
            priority="1000"
            mode="M3">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="*[local-name()='header' or local-name()='folder' or local-name()='transaction' or local-name()='heading' or local-name()='item']"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count( kmehr:id[@S = 'ID-KMEHR'] ) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count( kmehr:id[@S = 'ID-KMEHR'] ) &lt; 2">
                    <xsl:attribute name="id">Identification</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" element can be identified by maximum one 'official' id element (ID-KMEHR)._||_
                        L'élément "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" peut être identifié par maximum un élément "official id" (ID_KMEHR)._||_
                        Het element "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" kan door maximaal een "official id" (ID_KMEHR) element geïdentificeerd worden._||_
                        Das Element "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" kann durch maximal ein "official id" (ID_KMEHR) Element identifiziert werden.
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

    <!--PATTERN local-DN.structure.checksDN attribute for local cd structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">DN attribute for local cd structure checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:cd[@S='LOCAL' ]" priority="1000" mode="M4">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="kmehr:cd[@S='LOCAL' ]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="@DN"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@DN">
                    <xsl:attribute name="id">Codification</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A local cd must have a "DN" attribute to describe. (Code: "<xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>")_||_
                        Un cd local doit contenir un attribut "DN" pour le décrire. (Code: "<xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>")_||_
                        Een lokale cd moet een "DN"-attribuut bevatten om hem te beschrijven. (Code: "<xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>")_||_
                        Eine lokale CD muss ein "DN" Attribut umfassen um die zu beschreiben. (Code: "<xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>")
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

    <!--PATTERN inss.structure.checksKmehr inss structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Kmehr inss structure checks</svrl:text>

    <!--RULE -->
    <xsl:template match="//*[kmehr:id[@S = 'ID-PATIENT' or @S = 'INSS']]" priority="1000"
                  mode="M5">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="//*[kmehr:id[@S = 'ID-PATIENT' or @S = 'INSS']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:id[@S = 'ID-PATIENT' or @S = 'INSS']) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:id[@S = 'ID-PATIENT' or @S = 'INSS']) &lt; 2">
                    <xsl:attribute name="id">Identification</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" element must have maximum one INSS code. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S='ID-PATIENT' or @S = 'INSS'])"/>
                        <xsl:text/>)_||_
                        L'élément "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" doit contenir au maximum un code NISS. (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S='ID-PATIENT' or @S = 'INSS'])"/>
                        <xsl:text/>)_||_
                        Het element "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" mag maximaal een INSZ-code bevatten (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S='ID-PATIENT' or @S = 'INSS'])"/>
                        <xsl:text/>)_||_
                        Das Element "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" darf maximal einen INSS-Code umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S='ID-PATIENT' or @S = 'INSS'])"/>
                        <xsl:text/>)
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

    <!--PATTERN inss.values.checksKmehr inss values checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Kmehr inss values checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:id[ @S = 'ID-PATIENT' or @S = 'INSS']" priority="1000" mode="M6">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:id[ @S = 'ID-PATIENT' or @S = 'INSS']"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when
                    test="number(current()) = floor(number(current())) and number(current()) &lt; 100000000000 and ((97 - (floor(number(current()) div 100) mod 97) = (number(current()) mod 100)) or (97 - ((2000000000 + floor(number(current()) div 100)) mod 97) = (number(current()) mod 100)))"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="number(current()) = floor(number(current())) and number(current()) &lt; 100000000000 and ((97 - (floor(number(current()) div 100) mod 97) = (number(current()) mod 100)) or (97 - ((2000000000 + floor(number(current()) div 100)) mod 97) = (number(current()) mod 100)))">
                    <xsl:attribute name="id">Identification</xsl:attribute>
                    <xsl:attribute name="flag">value</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        INSS number must be correct - INSS:
                        <xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>_||_
                        Le Numéro NISS doit être correct. - NISS:
                        <xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>_||_
                        Het INSZ-nummer moet correct zijn - NISS:
                        <xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>_||_
                        Die INSS-Nummer muss richtig sein. - INSS:
                        <xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>
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

    <!--PATTERN id-hcparty.structure.checksKmehr id-hcparty structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Kmehr id-hcparty structure checks</svrl:text>

    <!--RULE -->
    <xsl:template match="//*[kmehr:id[@S = 'ID-HCPARTY']]" priority="1000" mode="M7">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="//*[kmehr:id[@S = 'ID-HCPARTY']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:id[@S = 'ID-HCPARTY']) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:id[@S = 'ID-HCPARTY']) &lt; 2">
                    <xsl:attribute name="id">Identification</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" element must have maximum one INAMI/RIZIV code. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S='ID-HCPARTY'])"/>
                        <xsl:text/>)_||_
                        L'élément "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" doit contenir au maximum un code INAMI. (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S='ID-HCPARTY'])"/>
                        <xsl:text/>)_||_
                        Het element "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" mag maximaal een code RIZIV bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S='ID-HCPARTY'])"/>
                        <xsl:text/>)_||_
                        Das Element "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" darf maximal einen Code LIKIV umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S='ID-HCPARTY'])"/>
                        <xsl:text/>)
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

    <!--PATTERN id-hcparty.values.checksKmehr id-hcparty values checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Kmehr id-hcparty values checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:id[@S = 'ID-HCPARTY' ]" priority="1000" mode="M8">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:id[@S = 'ID-HCPARTY' ]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when
                    test="number(current()) = floor(number(current())) and ((( string-length(current()) = 8 or string-length(current()) = 10) and ( number(current()) mod 100) = (97 - (floor(number(current()) div 100) mod 97) ) ) or ( string-length(current()) = 11 and ( floor((number(current()) div 1000) ) mod 100) = (97 - (floor(number(current()) div 100000) mod 97) ) ) )"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="number(current()) = floor(number(current())) and ((( string-length(current()) = 8 or string-length(current()) = 10) and ( number(current()) mod 100) = (97 - (floor(number(current()) div 100) mod 97) ) ) or ( string-length(current()) = 11 and ( floor((number(current()) div 1000) ) mod 100) = (97 - (floor(number(current()) div 100000) mod 97) ) ) )">
                    <xsl:attribute name="id">Identification</xsl:attribute>
                    <xsl:attribute name="flag">value</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        The ID number must be correct. (id:
                        <xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>)_||_
                        Le Numéro d'indentification doit être correct. - INAMI:
                        <xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>_||_
                        Het identificatie-nummer moet correct zijn. - RIZIV:
                        <xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>_||_
                        Die Identificationsnummer muss richtig sein. - LIKIV:
                        <xsl:text/>
                        <xsl:value-of select="."/>
                        <xsl:text/>
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

    <!--PATTERN id-kmehr.structure.warnings.checksKmehr id-kmehr structure warnings checks for key elements-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Kmehr id-kmehr structure warnings checks for key elements
    </svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:*[local-name()='header' or local-name()='folder' or local-name()='transaction' or local-name()='heading' or local-name()='item']"
            priority="1000"
            mode="M9">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:*[local-name()='header' or local-name()='folder' or local-name()='transaction' or local-name()='heading' or local-name()='item']"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:id[@S='ID-KMEHR']"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:id[@S='ID-KMEHR']">
                    <xsl:attribute name="id">Identification</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" element should be identified by one 'official' id element (ID-KMEHR)._||_
                        L'élément "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" devrait être identifié par un élément 'official-id' (ID-KMEHR)._||_
                        Het element "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" zou moeten worden geïdentificeerd aan de hand van een 'official-id' (ID-KMEHR)
                        element._||_
                        Das Element "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" sollte anhand eines 'official-id' (ID-KMEHR) Elementes identifiziert werden.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M9"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M9"/>
    <xsl:template match="@*|node()" priority="-2" mode="M9">
        <xsl:apply-templates select="*" mode="M9"/>
    </xsl:template>

    <!--PATTERN id-kmehr.values.warnings.checksKmehr id-kmehr values warnings for key elements-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Kmehr id-kmehr values warnings for key elements</svrl:text>

    <!--RULE -->
    <xsl:template
            match="//*[ (local-name()='folder' or local-name()='transaction' or local-name()='heading') and (count(kmehr:id[@S='ID-KMEHR'])=1) ]"
            priority="1000"
            mode="M10">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="//*[ (local-name()='folder' or local-name()='transaction' or local-name()='heading') and (count(kmehr:id[@S='ID-KMEHR'])=1) ]"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when
                    test="( (count(current()/preceding-sibling::*[not(local-name()='header') and (count(kmehr:id[@S='ID-KMEHR'])=1)]) = 0 ) and ( number( current()/kmehr:id[@S='ID-KMEHR'] ) = 1 ) ) or ( (number( current()/kmehr:id[@S='ID-KMEHR'] ) - 1) = number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR']) )"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="( (count(current()/preceding-sibling::*[not(local-name()='header') and (count(kmehr:id[@S='ID-KMEHR'])=1)]) = 0 ) and ( number( current()/kmehr:id[@S='ID-KMEHR'] ) = 1 ) ) or ( (number( current()/kmehr:id[@S='ID-KMEHR'] ) - 1) = number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR']) )">
                    <xsl:attribute name="id">Identification</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Official id (ID-KMEHR) for "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" element must be correct. ( ID's start with 1 and are sequential.) Actual id:
                        <xsl:text/>
                        <xsl:value-of select="number( current()/kmehr:id[@S='ID-KMEHR'] )"/>
                        <xsl:text/>. Preceding id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>. Following id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/following-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>._||_
                        L'id officiel (ID-KMEHR) pour "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" doit être correct. (les ID commencent à 1 et sont séquentiels.) id actuel:
                        <xsl:text/>
                        <xsl:value-of select="number( current()/kmehr:id[@S='ID-KMEHR'] )"/>
                        <xsl:text/>. id précédent:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>. id suivant:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/following-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>._||_
                        De officiële id (ID-KMEHR) voor "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" moet correct zijn. (de ID's beginnen met 1 en zijn sequentieel.) Actuele id:
                        <xsl:text/>
                        <xsl:value-of select="number( current()/kmehr:id[@S='ID-KMEHR'] )"/>
                        <xsl:text/>. Vorige id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>. Volgende id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/following-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>._||_
                        Die offizielle ID (ID-KMEHR) für "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" muss richtig sein. (Die ID fangen an mit 1 und sind sequenziell.) Heutige Id:
                        <xsl:text/>
                        <xsl:value-of select="number( current()/kmehr:id[@S='ID-KMEHR'] )"/>
                        <xsl:text/>. Vorherige Id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>. Folgende Id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/following-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M10"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M10"/>
    <xsl:template match="@*|node()" priority="-2" mode="M10">
        <xsl:apply-templates select="*" mode="M10"/>
    </xsl:template>

    <!--PATTERN items-id-kmehr.values.warnings.checksKmehr id-kmehr values warnings for items-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Kmehr id-kmehr values warnings for items</svrl:text>

    <!--RULE -->
    <xsl:template match="//*[ local-name()='item' and (count(kmehr:id[@S='ID-KMEHR'])=1) ]"
                  priority="1000"
                  mode="M11">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="//*[ local-name()='item' and (count(kmehr:id[@S='ID-KMEHR'])=1) ]"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when
                    test="( (count(current()/preceding-sibling::*[not(local-name()='header') and not(local-name()='heading') and (count(kmehr:id[@S='ID-KMEHR'])=1)]) = 0 ) and ( number( current()/kmehr:id[@S='ID-KMEHR'] ) = 1 ) ) or ( (number( current()/kmehr:id[@S='ID-KMEHR'] ) - 1) = number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR']) )"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="( (count(current()/preceding-sibling::*[not(local-name()='header') and not(local-name()='heading') and (count(kmehr:id[@S='ID-KMEHR'])=1)]) = 0 ) and ( number( current()/kmehr:id[@S='ID-KMEHR'] ) = 1 ) ) or ( (number( current()/kmehr:id[@S='ID-KMEHR'] ) - 1) = number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR']) )">
                    <xsl:attribute name="id">Identification</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Official id (ID-KMEHR) for "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" element must be correct. ( ID's start with 1 and are sequential.) Actual id:
                        <xsl:text/>
                        <xsl:value-of select="number( current()/kmehr:id[@S='ID-KMEHR'] )"/>
                        <xsl:text/>. Preceding id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>. Following id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/following-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>._||_
                        L'id officiel (ID-KMEHR) pour "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" doit être correct. (les ID commencent à 1 et sont séquentiels.) id actuel:
                        <xsl:text/>
                        <xsl:value-of select="number( current()/kmehr:id[@S='ID-KMEHR'] )"/>
                        <xsl:text/>. id précédent:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>. id suivant:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/following-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>._||_
                        De officiële id (ID-KMEHR) voor "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" moet correct zijn. (de ID's beginnen met 1 en zijn sequentieel.) Actuele id:
                        <xsl:text/>
                        <xsl:value-of select="number( current()/kmehr:id[@S='ID-KMEHR'] )"/>
                        <xsl:text/>. Vorige id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>. Volgende id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/following-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>._||_
                        Die offizielle ID (ID-KMEHR) für "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>" muss richtig sein. (Die ID fangen an mit 1 und sind sequenziell.) Heutige Id:
                        <xsl:text/>
                        <xsl:value-of select="number( current()/kmehr:id[@S='ID-KMEHR'] )"/>
                        <xsl:text/>. Vorherige Id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/preceding-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>. Folgende Id:
                        <xsl:text/>
                        <xsl:value-of
                                select="number(current()/following-sibling::*[(count(kmehr:id[@S='ID-KMEHR'])=1)][1]/kmehr:id[@S='ID-KMEHR'])"/>
                        <xsl:text/>.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M11"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M11"/>
    <xsl:template match="@*|node()" priority="-2" mode="M11">
        <xsl:apply-templates select="*" mode="M11"/>
    </xsl:template>

    <!--PATTERN codifications.values.checksCodifications values checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Codifications values checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='sumehr']]//kmehr:item"
                  priority="1001"
                  mode="M12">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='sumehr']]//kmehr:item"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content/kmehr:cd[@S='ICPC']) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:cd[@S='ICPC']) &lt; 2">
                    <xsl:attribute name="id">Codification</xsl:attribute>
                    <xsl:attribute name="flag">value</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A content must have maximum one ICPC code. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='ICPC'])"/>
                        <xsl:text/>)_||_
                        Un "content" doit contenir au maximum un code ICPC. (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='ICPC'])"/>
                        <xsl:text/>)_||_
                        Een "content" mag maximaal één ICPC-code omvatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='ICPC'])"/>
                        <xsl:text/>)_||_
                        Ein "Content" darf maximal einen ICPC-Code umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='ICPC'])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content/kmehr:cd[@S='ICD']) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:cd[@S='ICD']) &lt; 2">
                    <xsl:attribute name="id">Codification</xsl:attribute>
                    <xsl:attribute name="flag">value</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A content must have maximum one ICD code. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='ICD'])"/>
                        <xsl:text/>)_||_
                        Un "content" doit contenir au maximum un code ICD. (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='ICD'])"/>
                        <xsl:text/>)_||_
                        Een "content" mag maximaal één ICD-code omvatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='ICD'])"/>
                        <xsl:text/>)_||_
                        Ein "Content" darf maximal einen ICPC-Code umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='ICD'])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content/kmehr:cd[@S='CD-CLINICAL']) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:cd[@S='CD-CLINICAL']) &lt; 2">
                    <xsl:attribute name="id">Codification</xsl:attribute>
                    <xsl:attribute name="flag">value</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A content must have maximum one IBUI code (CD-CLINICAL). (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='CD-CLINICAL'])"/>
                        <xsl:text/>)_||_
                        Un "content" doit contenir au maximum un code IBUI (CD-CLINICAL). (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='CD-CLINICAL'])"/>
                        <xsl:text/>)_||_
                        Een "content" mag maximaal één IBUI-code (CD-CLINICAL) omvatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='CD-CLINICAL'])"/>
                        <xsl:text/>)_||_
                        Ein "Content" darf maximal einen IBUI-Code (CD-CLINICAL) umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:cd[@S='CD-CLINICAL'])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M12"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='sumehr']]//kmehr:item/kmehr:content/kmehr:cd[not(@S = 'LOCAL')]"
            priority="1000"
            mode="M12">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='sumehr']]//kmehr:item/kmehr:content/kmehr:cd[not(@S = 'LOCAL')]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when
                    test=" (current()/@S = 'ICPC' and current()/@SV = '2' ) or            (current()/@S = 'ICD' and current()/@SV = '10' ) or           (current()/@S = 'CD-CLINICAL' ) or            (current()/@S = 'CD-ATC' ) or            (current()/@S = 'CD-PATIENTWILL' ) or            (current()/@S = 'CD-VACCINEINDICATION' )"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="(current()/@S = 'ICPC' and current()/@SV = '2' ) or (current()/@S = 'ICD' and current()/@SV = '10' ) or (current()/@S = 'CD-CLINICAL' ) or (current()/@S = 'CD-ATC' ) or (current()/@S = 'CD-PATIENTWILL' ) or (current()/@S = 'CD-VACCINEINDICATION' )">
                    <xsl:attribute name="id">Codification</xsl:attribute>
                    <xsl:attribute name="flag">value</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        "Codification / Version" used in "content" elements must be one of these: "ICPC 2", "ICD 10".
                        (S="<xsl:text/>
                        <xsl:value-of select="current()/@S"/>
                        <xsl:text/>" and SV="<xsl:text/>
                        <xsl:value-of select="current()/@SV"/>
                        <xsl:text/>")_||_
                        La "Codification / Version" utilisée dans les éléments "content" doit être une de celles-ci:
                        "ICPC 2", "ICD 10". (S="<xsl:text/>
                        <xsl:value-of select="current()/@S"/>
                        <xsl:text/>" et SV="<xsl:text/>
                        <xsl:value-of select="current()/@SV"/>
                        <xsl:text/>")_||_
                        De in de "content"-elementen gebruikte codificatie/versie moet één van de volgende zijn: "ICPC
                        2", "ICD 10". (S="<xsl:text/>
                        <xsl:value-of select="current()/@S"/>
                        <xsl:text/>" et SV="<xsl:text/>
                        <xsl:value-of select="current()/@SV"/>
                        <xsl:text/>")_||_
                        Die in den "Content"-Elementen benutzte Kodifikation/Version muss eine der folgenden sein: ICPC
                        2, "ICD 10". (S="<xsl:text/>
                        <xsl:value-of select="current()/@S"/>
                        <xsl:text/>" et SV="<xsl:text/>
                        <xsl:value-of select="current()/@SV"/>
                        <xsl:text/>")
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M12"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M12"/>
    <xsl:template match="@*|node()" priority="-2" mode="M12">
        <xsl:apply-templates select="*" mode="M12"/>
    </xsl:template>

    <!--PATTERN contents.structure.checksContents structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Contents structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="//kmehr:transaction[kmehr:cd[@S ='CD-TRANSACTION' and .='sumehr' or .='clinicalsummary' or .='contactreport']]//kmehr:item[kmehr:content]"
            priority="1000"
            mode="M13">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="//kmehr:transaction[kmehr:cd[@S ='CD-TRANSACTION' and .='sumehr' or .='clinicalsummary' or .='contactreport']]//kmehr:item[kmehr:content]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[count(*) =0 ] ) = 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[count(*) =0 ] ) = 0">
                    <xsl:attribute name="id">Content</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A content cannot be empty._||_
                        Un élément "content" ne peut pas être vide._||_
                        Een "content" element mag niet leeg zijn._||_
                        Das "Content"-Element darf nicht leer sein.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M13"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M13"/>
    <xsl:template match="@*|node()" priority="-2" mode="M13">
        <xsl:apply-templates select="*" mode="M13"/>
    </xsl:template>

    <!--PATTERN contactperson.structure.checksContact Person structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Contact Person structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contactperson']]"
            priority="1001"
            mode="M14">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contactperson']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content/kmehr:person) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:person) = 1">
                    <xsl:attribute name="id">Contact Person</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Contact Person must have one content person and only one. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:person)"/>
                        <xsl:text/>)_||_
                        Un item "personne de contact" doit contenir un et un seul élément "content". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:person)"/>
                        <xsl:text/>)_||_
                        Een item "contactpersoon" mag maar één enkel "content"-element bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:person)"/>
                        <xsl:text/>)_||_
                        Ein thema "Kontaktperson" darf nur ein "Content"-Element umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:person)"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M14"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contactperson']]/kmehr:content/*"
            priority="1000"
            mode="M14">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contactperson']]/kmehr:content/*"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="(local-name()='person')"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="(local-name()='person')">
                    <xsl:attribute name="id">Contact Person</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "Contact Person" content can contain only element "person". (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        L'élement "content" d'un item personne de contact ne peut contenir qu'un élément "person".
                        (Elément: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        Het "content"-element van een item "contactpersoon" mag maar één "person"-element bevatten.
                        (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        Das "Content"-Element im Thema "Kontaktperson" darf nur ein "Content"-Element umfassen.
                        (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M14"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M14"/>
    <xsl:template match="@*|node()" priority="-2" mode="M14">
        <xsl:apply-templates select="*" mode="M14"/>
    </xsl:template>

    <!--PATTERN contactperson.structure.warnings.checksContact Person warnings checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Contact Person warnings checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contactperson']]"
            priority="1001"
            mode="M15">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contactperson']]"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:cd[@S='CD-CONTACT-PERSON']"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="kmehr:cd[@S='CD-CONTACT-PERSON']">
                    <xsl:attribute name="id">Contact Person</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Contact Person should have a code (CD-CONTACT-PERSON) to describe the family tie._||_
                        Un item "personne de contact" devrait contenir un code (CD-CONTACT-PERSON) pour décrire son lien
                        familial._||_
                        Een item "contactpersoon" zou een code (CD-CONTACT-PERSON) moeten bevatten om de gezinsrelatie
                        te beschrijven. _||_
                        Ein thema "Kontaktperson" darf nur "Person"-Elemente umfassen.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M15"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contactperson']]/kmehr:content/kmehr:person"
            priority="1000"
            mode="M15">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contactperson']]/kmehr:content/kmehr:person"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:address"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:address">
                    <xsl:attribute name="id">Contact Person</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Contact Person should have at least one address._||_
                        Un item "personne de contact" devrait contenir au moins une adresse._||_
                        Een item "contactpersoon" zou minstens een adres moeten bevatten._||_
                        Ein Thema "Kontaktperson" sollte mindestens eine Adresse umfassen.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:telecom"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:telecom">
                    <xsl:attribute name="id">Contact Person</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Contact Person should have at least one telecom._||_
                        Un item "personne de contact" devrait contenir au moins un moyen de télécommunication._||_
                        Een item "contactpersoon" zou minstens een communicatiemiddel moeten bevatten._||_
                        Ein Thema "Kontaktperson" sollte mindestens ein Kommunikationsmittel umfassen.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M15"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M15"/>
    <xsl:template match="@*|node()" priority="-2" mode="M15">
        <xsl:apply-templates select="*" mode="M15"/>
    </xsl:template>

    <!--PATTERN contacthcparty-contents.structure.checksContacts hcparty content structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Contacts hcparty content structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contacthcparty']]/kmehr:content/*"
            priority="1000"
            mode="M16">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contacthcparty']]/kmehr:content/*"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="(local-name()='hcparty')"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="(local-name()='hcparty')">
                    <xsl:attribute name="id">Contact Hcparty</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "Contact Hcparty" content can contain only element "hcparty". (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        L'élément "content" d'un item "contact-prestataire de soin" ne peut contenir que des éléments
                        "hcparty". (Elément: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        Het "content"-element van een item "contact-zorgverlener" mag enkel "hcparty"-elementen
                        bevatten. (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        Das "Content"-Element im Thema "Kontakt - Pflegeanbieter" darf nur "hcparty"-Elemente umfassen.
                        (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M16"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M16"/>
    <xsl:template match="@*|node()" priority="-2" mode="M16">
        <xsl:apply-templates select="*" mode="M16"/>
    </xsl:template>

    <!--PATTERN contacthcparty.structure.checksContacts hcparty structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Contacts hcparty structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contacthcparty']]"
            priority="1001"
            mode="M17">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contacthcparty']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content/kmehr:hcparty) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:hcparty) = 1">
                    <xsl:attribute name="id">Contact Hcparty</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Contact Hcparty must have one content with "hcparty" element and only one. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:hcparty)"/>
                        <xsl:text/>)_||_
                        Un item "contact prestataire de soins" doit contenir un et un seul "content" contenant un
                        élément "hcparty". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:hcparty)"/>
                        <xsl:text/>)_||_
                        Een item "contact-zorgverlener" mag een enkel "content" met een element "hcparty" omvatten.
                        (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:hcparty)"/>
                        <xsl:text/>)_||_
                        Ein Thema "Kontakt - Pflegeanbieter" darf nur einen "Content" mit einem "hcparty"-Element
                        umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:hcparty)"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M17"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template match="kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contacthcparty']]/kmehr:content/kmehr:hcparty"
                  priority="1000"
                  mode="M17">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contacthcparty']]/kmehr:content/kmehr:hcparty"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="kmehr:familyname"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:familyname">
                    <xsl:attribute name="id">Contact Hcparty</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Contact Hcparty must have a familyname (and firstnames)._||_
                        Un item "contact prestataire de soins" doit contenir un nom de famille (et prénoms)._||_
                        Een item "contact-zorgverlener" moet een familienaam (en voornamen) omvatten._||_
                        Ein Thema "Kontakt - Pflegeanbieter" muss einen Familiennamen (und Vornamen) umfassen.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M17"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M17"/>
    <xsl:template match="@*|node()" priority="-2" mode="M17">
        <xsl:apply-templates select="*" mode="M17"/>
    </xsl:template>

    <!--PATTERN contacthcparty.structure.warnings.checksContacts hcparty warnings checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Contacts hcparty warnings checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contacthcparty']]/kmehr:content/kmehr:hcparty"
            priority="1000"
            mode="M18">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='contacthcparty']]/kmehr:content/kmehr:hcparty"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:id[@S = 'ID-HCPARTY']"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:id[@S = 'ID-HCPARTY']">
                    <xsl:attribute name="id">Contact Hcparty</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Contact Hcparty should have one INAMI/RIZIV number._||_
                        Un item "contact prestataire de soins" devrait contenir un numéro INAMI/RIZIV._||_
                        Een item "contact-zorgverlener" zou minstens een RIZIV-nummer moeten hebben._||_
                        Ein thema "kontakt - pflegeanbieter" sollte eine LIKIV-Nummer haben.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:address"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:address">
                    <xsl:attribute name="id">Contact Hcparty</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Contact Hcparty should have at least one address._||_
                        Un item "contact prestataire de soins" devrait contenir au moins une adresse._||_
                        Een item "contact-zorgverlener" zou minstens een adres moeten hebben._||_
                        Ein thema "kontakt - pflegeanbieter" sollte mindestens eine Adresse haben.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:telecom"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:telecom">
                    <xsl:attribute name="id">Contact Hcparty</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Contact Hcparty should have at least one telecom._||_
                        Un item "contact prestataire de soins" devrait contenir au moins un moyen de
                        télécommunication._||_
                        Een item "contact-zorgverlener" zou minstens een telecommunicatiemiddel moeten hebben._||_
                        Ein thema "kontakt - pflegeanbieter" sollte mindestens ein Telekommunikationsmittel haben.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M18"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M18"/>
    <xsl:template match="@*|node()" priority="-2" mode="M18">
        <xsl:apply-templates select="*" mode="M18"/>
    </xsl:template>

    <!--PATTERN gmdmanager-contents.structure.checksGMD Manager content structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">GMD Manager content structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]/kmehr:content/*"
            priority="1000"
            mode="M19">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]/kmehr:content/*"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="(local-name()='hcparty')"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="(local-name()='hcparty')">
                    <xsl:attribute name="id">GMD Manager</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "GMD Manager" content can contain only element "hcparty". (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        L'élément content d'un item "détenteur de DMG" ne peut contenir que des éléments "hcparty".
                        (Elément: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        Het "content"-element van een item "GMD-houder" kan enkel "hcparty"-elementen omvatten.
                        (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        Das "Content"-Element eines Themas "Inhaber des GMD" kann nur "hcparty"-Elemente umfassen.
                        (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M19"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M19"/>
    <xsl:template match="@*|node()" priority="-2" mode="M19">
        <xsl:apply-templates select="*" mode="M19"/>
    </xsl:template>

    <!--PATTERN gmdmanager.structure.checksGMD Manager structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">GMD Manager structure checks</svrl:text>

    <!--RULE -->
    <xsl:template match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]"
                  priority="1003"
                  mode="M20">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]) &lt; 2">
                    <xsl:attribute name="id">GMD Manager</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        There can be maximum one GMD Manager. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']])"/>
                        <xsl:text/>)_||_
                        Il ne peut y avoir au maximum un item "détenteur de DMG". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']])"/>
                        <xsl:text/>)_||_
                        Er mag maximaal één item "GMD-houder" zijn. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']])"/>
                        <xsl:text/>)_||_
                        Es gibt maximal ein thema "Inhaber des GMD". (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M20"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]"
            priority="1002"
            mode="M20">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content/kmehr:hcparty) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:hcparty) = 1">
                    <xsl:attribute name="id">GMD Manager</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        GMD Manager must have one content hcparty and only one. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:hcparty)"/>
                        <xsl:text/>)_||_
                        Un item "détenteur de DMG" contient un et un seul élément 'content' de type 'hcparty'.(Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:hcparty)"/>
                        <xsl:text/>)_||_
                        Een item "GMD-houder" mag maar één enkel "content"-element van het type "hcparty" omvatten.
                        (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:hcparty)"/>
                        <xsl:text/>)_||_
                        Ein thema "Inhaber des GMD" umfaßt nur ein "Content"-Element vom Typ "hcparty". (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:hcparty)"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M20"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]/kmehr:content/kmehr:hcparty"
            priority="1001"
            mode="M20">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]/kmehr:content/kmehr:hcparty"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:id[@S = 'ID-HCPARTY']) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:id[@S = 'ID-HCPARTY']) &gt; 0">
                    <xsl:attribute name="id">GMD Manager</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        GMD Manager must have one INAMI/RIZIV number and only one. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S = 'ID-HCPARTY'])"/>
                        <xsl:text/>)_||_
                        Un item "detenteur de DMG" doit contenir un et un seul numéro INAMI/RIZIV. (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S = 'ID-HCPARTY'])"/>
                        <xsl:text/>)_||_
                        Een item "GMD-houder" moet één enkel RIZIV-nummer bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S = 'ID-HCPARTY'])"/>
                        <xsl:text/>)_||_
                        Ein thema "Inhaber des GMD" darf nur eine LIKIV-Nummer umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:id[@S = 'ID-HCPARTY'])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M20"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]/kmehr:content/kmehr:hcparty[kmehr:familyname]/kmehr:cd[@S='CD-HCPARTY']"
            priority="1000"
            mode="M20">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]/kmehr:content/kmehr:hcparty[kmehr:familyname]/kmehr:cd[@S='CD-HCPARTY']"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="current()='persphysician'"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="current()='persphysician'">
                    <xsl:attribute name="id">GMD Manager</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        If GMD Manager is a person (with familyname) then his "role" (official CD-HCPARTY) must be
                        "persphysician". (Code: "<xsl:text/>
                        <xsl:value-of select="current()"/>
                        <xsl:text/>")_||_
                        Si un item "détenteur de DMG" est une personne (avec un nom de famille) alors son "rôle"
                        (CD-HCPARTY officiel) doit être "persphysician". (Code: "<xsl:text/>
                        <xsl:value-of select="current()"/>
                        <xsl:text/>")_||_
                        Indien een item "GMD-houder" een persoon (met familienaam) is, dan moet zijn "rol" (officiële
                        CD-HCPARTY) "persphysician" zijn. (Code: "<xsl:text/>
                        <xsl:value-of select="current()"/>
                        <xsl:text/>")_||_
                        Wenn ein thema "Inhaber des GMD" eine Person mit einem Familiennamen ist, dann muss seine
                        offizielle Rolle "persphysician" sein. (Code: "<xsl:text/>
                        <xsl:value-of select="current()"/>
                        <xsl:text/>")
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M20"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M20"/>
    <xsl:template match="@*|node()" priority="-2" mode="M20">
        <xsl:apply-templates select="*" mode="M20"/>
    </xsl:template>

    <!--PATTERN gmdmanager.structure.warnings.checksGMD Manager warnings checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">GMD Manager warnings checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]/kmehr:content/kmehr:hcparty"
            priority="1000"
            mode="M21">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='gmdmanager']]/kmehr:content/kmehr:hcparty"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:address"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:address">
                    <xsl:attribute name="id">GMD Manager</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        GMD Manager should have at least one address._||_
                        Un item "détenteur de DMG" devrait contenir au moins une adresse._||_
                        Een item "GMD-houder" zou minstens een adres moeten bevatten._||_
                        Ein thema "Inhaber des GMD" sollte mindestens eine Adresse umfassen.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:telecom"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:telecom">
                    <xsl:attribute name="id">GMD Manager</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        GMD Manager should have at least one telecom._||_
                        Un item "détenteur de DMG" devrait contenir au moins un moyen de télécommunication._||_
                        Een item "GMD-houder" zou minstens een telecommunicatiemiddel moeten bevatten._||_
                        Ein thema "Inhaber des GMD" sollte mindestens ein Telekommunikationsmittel umfassen.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M21"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M21"/>
    <xsl:template match="@*|node()" priority="-2" mode="M21">
        <xsl:apply-templates select="*" mode="M21"/>
    </xsl:template>

    <!--PATTERN healthcareelement.structure.checksHealthcare Element structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Healthcare Element structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='healthcareelement']]"
            priority="1001"
            mode="M22">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='healthcareelement']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:text]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:text]) = 1">
                    <xsl:attribute name="id">Healthcare Element</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Healthcare Element must have one and only one content with "text" elements (label). (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Un item "Problème actuel" ou "Elément de soin passif" doit contenir un et un seul "content"
                        contenant des éléments "text"(label). (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Een item "huidig probleem" of "passief zorgelement" mag één enkel "content" met een
                        "text"-elementen (label) bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Ein thema "heutiges Problem" oder "passives Sorgeelement" darf nur einen "Content" mit einem
                        "Text"-Elementen (Label) umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:cd]) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:cd]) &lt; 2">
                    <xsl:attribute name="id">Healthcare Element</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Healthcare Element must have maximum one content with "cd" elements (codification). (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Un item "Problème actuel" ou "Elément de soin passif" doit contenir au plus un "content"
                        constitué d'éléments "cd"(codification). (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Een item "huidig probleem" of "passief zorgelement" mag maximaal een "content" met
                        "cd"-elementen (codificatie) omvatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Ein thema "heutiges Problem" oder "passives Sorgeelement" darf maximal einen "Content" mit
                        "CD"-Elementen (Kodifikation) umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when
                    test="kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE']='active' or kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE']='inactive'"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE']='active' or kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE']='inactive'">
                    <xsl:attribute name="id">Healthcare Element</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Healthcare Element must have one element "lifecycle", either "active" or "inactive". (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE'])"/>
                        <xsl:text/>, Value: "<xsl:text/>
                        <xsl:value-of select="kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE']"/>
                        <xsl:text/>")_||_
                        Un item "Problème actuel" ou "Elément de soin passif" contient un élément "lifecycle", soit
                        "active" ou "inactive". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE'])"/>
                        <xsl:text/>, Valeur: "<xsl:text/>
                        <xsl:value-of select="kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE']"/>
                        <xsl:text/>")_||_
                        Een item "huidig probleem" of "passief zorgelement" bevat een element "lifecycle", ofwel
                        "actief" of "non-actief". (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE'])"/>
                        <xsl:text/>, Waarde: "<xsl:text/>
                        <xsl:value-of select="kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE']"/>
                        <xsl:text/>")_||_
                        Ein Thema "heutiges Problem" oder "passives Sorgeelement" umfaßt ein Element "Lifecycle",
                        entweder "aktiv" oder "nicht aktiv" (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE'])"/>
                        <xsl:text/>, Werte: "<xsl:text/>
                        <xsl:value-of select="kmehr:lifecycle/kmehr:cd[@S='CD-LIFECYCLE']"/>
                        <xsl:text/>")
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:beginmoment) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:beginmoment) &lt; 2">
                    <xsl:attribute name="id">Healthcare Element</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Healthcare Element must have maximum one element "beginmoment"._||_
                        Un item "Problème actuel" ou "Elément de soin passif" doit avoir au maximum un élément
                        "beginmoment"._||_
                        Healthcare Element must have maximum one element "beginmoment"._||_
                        Healthcare Element must have maximum one element "beginmoment".
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:endmoment) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:endmoment) &lt; 2">
                    <xsl:attribute name="id">Healthcare Element</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Healthcare Element must have maximum one element "endmoment"._||_
                        Un item "Problème actuel" ou "Elément de soin passif" doit avoir au maximum un élément
                        "endmoment"._||_
                        Healthcare Element must have maximum one element "endmoment"._||_
                        Healthcare Element must have maximum one element "endmoment".
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M22"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='healthcareelement']]/kmehr:content/*"
            priority="1000"
            mode="M22">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='healthcareelement']]/kmehr:content/*"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="(local-name()='cd' or local-name()='text')"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="(local-name()='cd' or local-name()='text')">
                    <xsl:attribute name="id">Healthcare Element</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" content can contain only elements "text" or "cd". (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        Un content "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" ne peut contenir que des éléments "text" ou "cd". (Elément: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        Een content "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" kan enkel "text"- of "cd"-elementen bevatten. (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")_||_
                        Ein "Content" "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" kann nur "Text"- oder "CD"-Elemente umfassen. (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>")
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M22"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M22"/>
    <xsl:template match="@*|node()" priority="-2" mode="M22">
        <xsl:apply-templates select="*" mode="M22"/>
    </xsl:template>

    <!--PATTERN healthcareelement.structure.warnings.checksHealthcare Element checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Healthcare Element checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='healthcareelement']]"
            priority="1000"
            mode="M23">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='healthcareelement']]"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:content/kmehr:cd"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:content/kmehr:cd">
                    <xsl:attribute name="id">Healthcare Element</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Healthcare Element should have a content with codified informations (cd). (Label: "<xsl:text/>
                        <xsl:value-of select="kmehr:content/kmehr:text"/>
                        <xsl:text/>")_||_
                        Un item "Problème actuel" ou "Elément de soin passif" devrait contenir un élément "content"
                        contenant un "cd". (Label: "<xsl:text/>
                        <xsl:value-of select="kmehr:content/kmehr:text"/>
                        <xsl:text/>")_||_
                        Een item "huidig probleem" of "passief zorgelement" zou een element "content" met een
                        "cd"-element moeten bevatten. (Label: "<xsl:text/>
                        <xsl:value-of select="kmehr:content/kmehr:text"/>
                        <xsl:text/>")_||_
                        Ein Thema "heutiges Problem" oder "passives Sorgeelement" sollte ein Element "Content" mit einem
                        "CD"-Element umfassen. (Label: "<xsl:text/>
                        <xsl:value-of select="kmehr:content/kmehr:text"/>
                        <xsl:text/>")
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M23"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M23"/>
    <xsl:template match="@*|node()" priority="-2" mode="M23">
        <xsl:apply-templates select="*" mode="M23"/>
    </xsl:template>

    <!--PATTERN currentproblems.structure.checksCurrent Problems structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Current Problems structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='healthcareelement'] and kmehr:lifecycle[kmehr:cd[ @S ='CD-LIFECYCLE' and .='active' ]]]"
            priority="1000"
            mode="M24">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='healthcareelement'] and kmehr:lifecycle[kmehr:cd[ @S ='CD-LIFECYCLE' and .='active' ]]]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="kmehr:beginmoment"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:beginmoment">
                    <xsl:attribute name="id">Healthcare Element</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Current Problem must have a "beginmoment"._||_
                        Un item "Problème actuel" doit contenir un "beginmoment"._||_
                        Een item "huidig probleem" moet een beginmoment hebben._||_
                        Ein Thema "heutiges Problem" muss einen "Anfangmoment" haben.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M24"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M24"/>
    <xsl:template match="@*|node()" priority="-2" mode="M24">
        <xsl:apply-templates select="*" mode="M24"/>
    </xsl:template>

    <!--PATTERN relevantpassivecareelements.warning.checksRelevant Passive Care Elements structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Relevant Passive Care Elements structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='healthcareelement'] and kmehr:lifecycle[kmehr:cd[ @S ='CD-LIFECYCLE' and .='inactive' ]]]"
            priority="1000"
            mode="M25">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='healthcareelement'] and kmehr:lifecycle[kmehr:cd[ @S ='CD-LIFECYCLE' and .='inactive' ]]]"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:endmoment"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:endmoment">
                    <xsl:attribute name="id">Healthcare Element</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Relevant Passive Care Element should have an "endmoment"._||_
                        Un item "Elément de soin passif" devrait avoir une fin ("endmoment")._||_
                        Een item "passief zorgelement" zou een einde ("endmoment") moeten hebben._||_
                        Ein Thema "passives Sorgeelement" sollte ein Ende ("Endmoment") haben.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M25"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M25"/>
    <xsl:template match="@*|node()" priority="-2" mode="M25">
        <xsl:apply-templates select="*" mode="M25"/>
    </xsl:template>

    <!--PATTERN medication.structure.checksMedication structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Medication structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='medication']]"
            priority="1002"
            mode="M26">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='medication']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:text]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:text]) = 1">
                    <xsl:attribute name="id">Medication</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Medication must contain one (and only one) textual content corresponding to the medication label
                        (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Un item "médication" doit contenir un et un seul content textuel correspondant au label
                        "medication". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Een item "medicatie" moet één enkele textuele inhoud hebben die overeenstemt met de label
                        "medicatie". (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Ein thema "Medikation" muss nur einen textuellen Inhalt haben, welcher mit dem Label
                        "Medikation" übereinstimmt. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:cd[@S='CD-ATC']]) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:cd[@S='CD-ATC']]) &lt; 2">
                    <xsl:attribute name="id">Medication</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Medication must have maximum one content with codified ATC. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd[@S='CD-ATC']])"/>
                        <xsl:text/>)_||_
                        Un item "médication" contient au plus un élément "content" contenant un code ATC. (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd[@S='CD-ATC']])"/>
                        <xsl:text/>)_||_
                        Een item "medicatie" bevat maximaal een "content"-element met een ATC-code. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd[@S='CD-ATC']])"/>
                        <xsl:text/>)_||_
                        Ein Thema "Medikation" umfaßt maximal ein "Content"-Element mit einem ATC-Code. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd[@S='CD-ATC']])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when
                    test="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription]) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription]) &lt; 2">
                    <xsl:attribute name="id">Medication</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Medication must contain maximum one content "medicinalproduct" or "substanceproduct" or
                        "compoundprescription". (Number:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription])"/>
                        <xsl:text/>)_||_
                        Un item "médication" doit contenir au maximum un content "medicinalproduct", "substanceproduct"
                        ou "compoundprescription". (Nombre:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription])"/>
                        <xsl:text/>)_||_
                        Een item "medicatie" mag maar één inhoud "medicinalproduct", "substanceproduct" of
                        "compoundprescription" bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription])"/>
                        <xsl:text/>)_||_
                        Ein thema "Medikation" sollte nur ein Inhalt "medicinalproduct", "substanceproduct" oder
                        "compoundprescription" haben. (Zahl:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:beginmoment) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:beginmoment) &lt; 2">
                    <xsl:attribute name="id">Medication</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Medication must have maximum one "beginmoment". (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:beginmoment)"/>
                        <xsl:text/>)_||_
                        Un item "médication" doit contenir au maximum un "beginmoment". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:beginmoment)"/>
                        <xsl:text/>)_||_
                        Een item "medicatie" mag maximaal één "beginmoment" (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:beginmoment)"/>
                        <xsl:text/>)_||_
                        Ein thema "Medikation" darf maximal ein "beginmoment". (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:beginmoment)"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M26"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='medication']]/kmehr:content"
            priority="1001"
            mode="M26">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='medication']]/kmehr:content"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:cd[@S='CD-ATC']) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:cd[@S='CD-ATC']) &lt; 2">
                    <xsl:attribute name="id">Medication</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Medication content must have maximum one codified ATC. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:cd[@S='CD-ATC'])"/>
                        <xsl:text/>)_||_
                        Un item "médication" doit contenir au maximum un code ATC. (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:cd[@S='CD-ATC'])"/>
                        <xsl:text/>)_||_
                        Een item "medicatie" mag maximaal één ATC-code bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:cd[@S='CD-ATC'])"/>
                        <xsl:text/>)_||_
                        Ein thema "Medikation" darf nur einen textuellen Inhalt haben, welcher mit dem Label
                        "Medikation" übereinstimmt. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:cd[@S='CD-ATC'])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M26"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='medication']]/kmehr:content/*"
            priority="1000"
            mode="M26">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='medication']]/kmehr:content/*"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when
                    test="(local-name()='text' or local-name()='medicinalproduct' or local-name()='substanceproduct' or local-name()='compoundprescription' or ( local-name()='cd' and @S='CD-ATC' ) )"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="(local-name()='text' or local-name()='medicinalproduct' or local-name()='substanceproduct' or local-name()='compoundprescription' or ( local-name()='cd' and @S='CD-ATC' ) )">
                    <xsl:attribute name="id">Medication</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" content can contain only elements "text", "medicinalproduct", "substanceproduct",
                        "compoundprescription" or "cd ATC". (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>
                        <xsl:text/>
                        <xsl:value-of select="@S"/>
                        <xsl:text/>")_||_
                        Un content "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" ne peut contenir que des éléments "text", "medicinalproduct", "substanceproduct",
                        "compoundprescription" ou "cd ATC". (Elément: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>
                        <xsl:text/>
                        <xsl:value-of select="@S"/>
                        <xsl:text/>")_||_
                        Een content "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" mag enkel de elementen "text", "medicinalproduct", "substanceproduct",
                        "compoundprescription" of "cd ATC" bevatten. (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>
                        <xsl:text/>
                        <xsl:value-of select="@S"/>
                        <xsl:text/>")_||_
                        Ein Content "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" darf nur die Elemente "text", "medicinalproduct", "substanceproduct",
                        "compoundprescription" oder "cd ATC" umfassen. (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>
                        <xsl:text/>
                        <xsl:value-of select="@S"/>
                        <xsl:text/>")
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M26"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M26"/>
    <xsl:template match="@*|node()" priority="-2" mode="M26">
        <xsl:apply-templates select="*" mode="M26"/>
    </xsl:template>

    <!--PATTERN medication.structure.warnings.checksMedication warnings checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Medication warnings checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='medication']]"
            priority="1000"
            mode="M27">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='medication']]"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when
                    test="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription]) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription]) &gt; 0">
                    <xsl:attribute name="id">Medication</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Medication should contain at least one content "medicinalproduct" or "substanceproduct" or
                        "compoundprescription". (Number:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription])"/>
                        <xsl:text/>)_||_
                        Un item "médication" devrait contenir au moins un content "medicinalproduct", "substanceproduct"
                        ou "compoundprescription". (Nombre:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription])"/>
                        <xsl:text/>)_||_
                        Een item "medicatie" zou maar één inhoud "medicinalproduct", "substanceproduct" of
                        "compoundprescription" mogen bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription])"/>
                        <xsl:text/>)_||_
                        Ein thema "Medikation" sollte nur ein Inhalt "medicinalproduct", "substanceproduct" oder
                        "compoundprescription" haben. (Zahl:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct or kmehr:compoundprescription])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="kmehr:content[kmehr:cd[@S='CD-ATC']]"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="kmehr:content[kmehr:cd[@S='CD-ATC']]">
                    <xsl:attribute name="id">Medication</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Medication should have one content with codified ATC._||_
                        Un item "médication" devrait contenir un élément "content" contenant un code ATC._||_
                        Een item "medicatie" zou één enkel "content"-element met een ATC-code moeten hebben._||_
                        Ein thema "Medikation" darf nur einen "Content"-Element mit einem ATC-Code haben.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M27"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M27"/>
    <xsl:template match="@*|node()" priority="-2" mode="M27">
        <xsl:apply-templates select="*" mode="M27"/>
    </xsl:template>

    <!--PATTERN vaccine.structure.checksVaccine structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Vaccine structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='vaccine']]"
            priority="1002"
            mode="M28">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='vaccine']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="kmehr:content[kmehr:cd[@S='CD-VACCINEINDICATION']]"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="kmehr:content[kmehr:cd[@S='CD-VACCINEINDICATION']]">
                    <xsl:attribute name="id">Vaccine</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Vaccine must have at least one content code CD-VACCINEINDICATION"._||_
                        Un item "vaccin administré" doit contenir au moins un élément "content" contenant un code de
                        CD-VACCINEINDICATION_||_
                        Een item "toegediende vaccin" moet minstens een "content"-element met een
                        CD-VACCINEINDICATION-code hebben._||_
                        Ein Thema "durchgeführte Impfung" muss mindestens ein "Content"-Element mit einem
                        CD-VACCINEINDICATION-Code haben.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct]) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct]) &lt; 2">
                    <xsl:attribute name="id">Vaccine</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Vaccine must contain maximum one content "medicinalproduct" or "substanceproduct". (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct])"/>
                        <xsl:text/>)_||_
                        Un item "vaccin administré" doit contenir au maximum un content "medicinalproduct" ou
                        "substanceproduct". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct])"/>
                        <xsl:text/>)_||_
                        Een item "toegediende vaccin" mag maximaal één inhoud "medicinalproduct" of "substanceproduct"
                        bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct])"/>
                        <xsl:text/>)_||_
                        Ein thema "durchgeführte Impfung" soll nur ein Inhalt "medicinalproduct" oder "substanceproduct"
                        haben. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:cd[@S='CD-ATC']]) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:cd[@S='CD-ATC']]) &lt; 2">
                    <xsl:attribute name="id">Vaccine</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Vaccine must have maximum one content with codified ATC. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd[@S='CD-ATC']])"/>
                        <xsl:text/>)_||_
                        Un item "vaccin administré" doit contenir au maximum un élément "content" contenant un code ATC.
                        (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd[@S='CD-ATC']])"/>
                        <xsl:text/>)_||_
                        Een item "toegediende vaccin" mag maximaal een "content"-element met een ATC-code hebben.
                        (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd[@S='CD-ATC']])"/>
                        <xsl:text/>)_||_
                        Ein thema "durchgeführte Impfung" darf maximal ein "Content"-Element mit einem ATC-Code haben.
                        (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd[@S='CD-ATC']])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M28"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='vaccine']]/kmehr:content"
            priority="1001"
            mode="M28">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='vaccine']]/kmehr:content"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:cd[@S='CD-ATC']) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:cd[@S='CD-ATC']) &lt; 2">
                    <xsl:attribute name="id">Vaccine</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Vaccine content must have maximum one codified ATC. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:cd[@S='CD-ATC'])"/>
                        <xsl:text/>)_||_
                        Un item "vaccin administré" doit contenir au maximum un code ATC. (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:cd[@S='CD-ATC'])"/>
                        <xsl:text/>)_||_
                        Een item "toegediende vaccin" mag maximaal een ATC-code hebben. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:cd[@S='CD-ATC'])"/>
                        <xsl:text/>)_||_
                        Ein thema "durchgeführte Impfung" darf maximal ein "ATC-Code haben. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:cd[@S='CD-ATC'])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M28"/>
    </xsl:template>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='vaccine']]/kmehr:content/*"
            priority="1000"
            mode="M28">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='vaccine']]/kmehr:content/*"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when
                    test="(local-name()='medicinalproduct' or local-name()='substanceproduct' or ( local-name()='cd' and (@S='CD-ATC' or @S='CD-VACCINEINDICATION') ) )"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="(local-name()='medicinalproduct' or local-name()='substanceproduct' or ( local-name()='cd' and (@S='CD-ATC' or @S='CD-VACCINEINDICATION') ) )">
                    <xsl:attribute name="id">Vaccine</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        A "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" content can contain only elements "medicinalproduct", "substanceproduct", "cd
                        VACCINEINDICATION" or "cd ATC". (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>
                        <xsl:text/>
                        <xsl:value-of select="@S"/>
                        <xsl:text/>")_||_
                        Un content "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" ne peut contenir que des éléments "medicinalproduct", "substanceproduct", "cd
                        VACCINEINDICATION" ou "cd ATC". (Elément: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>
                        <xsl:text/>
                        <xsl:value-of select="@S"/>
                        <xsl:text/>")_||_
                        Een content "<xsl:text/>
                        <xsl:value-of select="../../kmehr:cd[@S='CD-ITEM']"/>
                        <xsl:text/>" mag enkel de elementen "medicinalproduct", "substanceproduct", "cd
                        VACCINEINDICATION" of "cd ATC" bevatten. (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>
                        <xsl:text/>
                        <xsl:value-of select="@S"/>
                        <xsl:text/>")_||_
                        Ein Content "" darf nur die Elemente "medicinalproduct", "substanceproduct", "cd
                        VACCINEINDICATION" oder "cd ATC" umfassen. (Element: "<xsl:text/>
                        <xsl:value-of select="local-name()"/>
                        <xsl:text/>
                        <xsl:text/>
                        <xsl:value-of select="@S"/>
                        <xsl:text/>")
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M28"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M28"/>
    <xsl:template match="@*|node()" priority="-2" mode="M28">
        <xsl:apply-templates select="*" mode="M28"/>
    </xsl:template>

    <!--PATTERN vaccine.structure.warnings.checksVaccine warnings checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Vaccine warnings checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='vaccine']]"
            priority="1000"
            mode="M29">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='vaccine']]"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct]) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct]) &gt; 0">
                    <xsl:attribute name="id">Vaccine</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Vaccine should contain at least one content "medicinalproduct" or "substanceproduct". (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct])"/>
                        <xsl:text/>)_||_
                        Un item "vaccin administré" devrait contenir au moins un content "medicinalproduct" ou
                        "substanceproduct". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct])"/>
                        <xsl:text/>)_||_
                        Een item "toegediende vaccin" zou één enkele inhoud "medicinalproduct" of "substanceproduct"
                        mogen bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct])"/>
                        <xsl:text/>)_||_
                        Ein thema "durchgeführte Impfung" sollte nur einen Inhalt "medicinalproduct" oder
                        "substanceproduct" haben. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:medicinalproduct or kmehr:substanceproduct])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M29"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M29"/>
    <xsl:template match="@*|node()" priority="-2" mode="M29">
        <xsl:apply-templates select="*" mode="M29"/>
    </xsl:template>

    <!--PATTERN patientwill.structure.checksPatientwill structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Patientwill structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S ='CD-ITEM' and .='patientwill']]"
            priority="1000"
            mode="M30">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary'  or .='sumehr']]//kmehr:item[kmehr:cd[@S ='CD-ITEM' and .='patientwill']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:cd]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:cd]) = 1">
                    <xsl:attribute name="id">Patient Will</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Patient Will must have one and only one content with "cd" elements (code). (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Un item "volonté du patient" doit contenir un et un seul élément "content" contenant des
                        éléments "cd" (code). (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Een item "wil van de patiënt" mag één enkel "content"-element met "cd"-elementen (code) bevatten
                        (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Ein Thema "Patientenwille" darf nur ein "Content"-Element mit "CD"-Elementen (Code) umfassen.
                        (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:text]) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:text]) &lt; 2">
                    <xsl:attribute name="id">Patient Will</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Patient Will must have maximum one content with "text" elements. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Un item "volonté du patient" doit contenir au maximum un élément "content" contenant des
                        éléments "text". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Een item "wil van de patiënt" mag maximaal één "content"-element met "text"-elementen (code)
                        bevatten (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Ein Thema "Patientenwille" darf maximal ein "Content"-Element mit "Text"-Elementen (Code)
                        umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M30"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M30"/>
    <xsl:template match="@*|node()" priority="-2" mode="M30">
        <xsl:apply-templates select="*" mode="M30"/>
    </xsl:template>

    <!--PATTERN insurancystatus.structure.checksInsurancystatus structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Insurancystatus structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary']]//kmehr:item[kmehr:cd[@S ='CD-ITEM' and .='insurancystatus']]"
            priority="1000"
            mode="M31">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary']]//kmehr:item[kmehr:cd[@S ='CD-ITEM' and .='insurancystatus']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:insurance]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:insurance]) = 1">
                    <xsl:attribute name="id">Insurancy Status</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Insurancy Status must have one and only one content with "insurance" element. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:insurance])"/>
                        <xsl:text/>)_||_
                        Un item "insurancystatus" doit contenir un et un seul élément "content" contenant un élément
                        "insurance". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:insurance])"/>
                        <xsl:text/>)_||_
                        Een item "insurancystatus" mag één enkel "content"-element met één "insurance"-element bevatten
                        (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:insurance])"/>
                        <xsl:text/>)_||_
                        Ein Thema "insurancystatus" darf nur ein "Content"-Element mit einem "insurance"-Element
                        umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:insurance])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content/kmehr:insurance) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content/kmehr:insurance) = 1">
                    <xsl:attribute name="id">Insurancy Status</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Insurancy Status content must have one and only insurance element. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:insurance)"/>
                        <xsl:text/>)_||_
                        Un item "insurancystatus" doit contenir un et un seul élément "content" contenant un élément
                        "insurance". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:insurance)"/>
                        <xsl:text/>)_||_
                        Een item "insurancystatus" mag één enkel "content"-element met één "insurance"-element bevatten
                        (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:insurance)"/>
                        <xsl:text/>)_||_
                        Ein Thema "insurancystatus" darf nur ein "Content"-Element mit einem "insurance"-Element
                        umfassen. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content/kmehr:insurance)"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M31"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M31"/>
    <xsl:template match="@*|node()" priority="-2" mode="M31">
        <xsl:apply-templates select="*" mode="M31"/>
    </xsl:template>

    <!--PATTERN careplansubscription.structure.checksCareplansubscription structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Careplansubscription structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary']]/kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='careplansubscription']]"
            priority="1000"
            mode="M32">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='clinicalsummary']]/kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='careplansubscription']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:cd]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:cd]) = 1">
                    <xsl:attribute name="id">Careplan Subscription</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Careplan Subscription must contain one (and only one) cd content. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Un item "careplansubscription" doit contenir un et un seul élément "content" contenant un
                        élément "cd". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Careplan Subscription mag slechts één enkele cd content bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Careplan Subscription must contain one (and only one) cd content. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:cd])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:text]) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:text]) &lt; 2">
                    <xsl:attribute name="id">Careplan Subscription</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Careplan Subscription must contain maximum one text content. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Un item "careplansubscription" doit contenir un et un seul content textuel. (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Careplan Subscription mag maximaal één "text" content bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Careplan Subscription must contain maximum one text content. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="kmehr:isrelevant"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:isrelevant">
                    <xsl:attribute name="id">Careplan Subscription</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Careplan Subscription must have a "isrelevant" element._||_
                        Un item "careplansubscription" doit contenir un élément "isrelevant"._||_
                        Careplan Subscription moet een "isrelevant" element bevatten._||_
                        Careplan Subscription must have a "isrelevant" element.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="kmehr:lifecycle"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:lifecycle">
                    <xsl:attribute name="id">Careplan Subscription</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Careplan Subscription must have a "lifecycle" element._||_
                        Un item "careplansubscription" doit contenir un élément "lifecycle"._||_
                        Careplan Subscription moet een "lifecycle" element bevatten._||_
                        Careplan Subscription must have a "lifecycle" element.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:beginmoment) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:beginmoment) &lt; 2">
                    <xsl:attribute name="id">Careplan Subscription</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Careplan Subscription must contain maximum one "beginmoment". (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:beginmoment)"/>
                        <xsl:text/>)_||_
                        Un item "careplansubscription" doit contenir au maximum un élément "beginmoment". (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:beginmoment)"/>
                        <xsl:text/>)_||_
                        Careplan Subscription mag maximaal één "beginmoment" bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:beginmoment)"/>
                        <xsl:text/>)_||_
                        Careplan Subscription must contain maximum one "beginmoment". (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:beginmoment)"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:endmoment) &lt; 2"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(kmehr:endmoment) &lt; 2">
                    <xsl:attribute name="id">Careplan Subscription</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Careplan Subscription must contain maximum one "endmoment". (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:endmoment)"/>
                        <xsl:text/>)_||_
                        Un item "careplansubscription" doit contenir au maximum un élément "endmoment". (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:endmoment)"/>
                        <xsl:text/>)_||_
                        Careplan Subscription mag maximaal één "endmoment" bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:endmoment)"/>
                        <xsl:text/>)_||_
                        Careplan Subscription must contain maximum one "endmoment". (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:endmoment)"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="kmehr:recorddatetime"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="kmehr:recorddatetime">
                    <xsl:attribute name="id">Careplan Subscription</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Careplan Subscription must have a recorddatetime._||_
                        Un item "careplansubscription" doit avoir une date d'enregistrement._||_
                        Een item "Careplansubscription" moet een registratiedatum hebben._||_
                        Careplan Subscription must have a recorddatetime.
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M32"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M32"/>
    <xsl:template match="@*|node()" priority="-2" mode="M32">
        <xsl:apply-templates select="*" mode="M32"/>
    </xsl:template>

    <!--PATTERN notification.structure.checksNotification structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Notification structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='contactreport']]/kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='encountertype']]"
            priority="1000"
            mode="M33">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='contactreport']]/kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='encountertype']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:text or kmehr:cd]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:text or kmehr:cd]) = 1">
                    <xsl:attribute name="id">Encountertype</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Encounter type content must contain one (and only one) textual or cd content. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text or kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Un item "encountertype" doit contenir un et un seul élément "content" contenant un élément
                        "text" ou "cd". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text or kmehr:cd])"/>
                        <xsl:text/>)_||_
                        De "encountertype" moet één enkele "text" of "cd" content bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text or kmehr:cd])"/>
                        <xsl:text/>)_||_
                        Encounter type content must contain one (and only one) textual or cd content. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text or kmehr:cd])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M33"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M33"/>
    <xsl:template match="@*|node()" priority="-2" mode="M33">
        <xsl:apply-templates select="*" mode="M33"/>
    </xsl:template>

    <!--PATTERN notification.structure.checksNotification structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Notification structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='contactreport']]/kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='encounterdatetime']]"
            priority="1000"
            mode="M34">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='contactreport']]/kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='encounterdatetime']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:date and kmehr:time]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:date and kmehr:time]) = 1">
                    <xsl:attribute name="id">Encounterdatetime</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Encounter datetime must contain one (and only one) date and time content. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:date and kmehr:time])"/>
                        <xsl:text/>)_||_
                        Un item "encounterdatetime" doit contenir un et un seul élément "content" contenant les éléments
                        "date" et "time". (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:date and kmehr:time])"/>
                        <xsl:text/>)_||_
                        Encounter datetime moet één enkele "date" en "time" content bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:date and kmehr:time])"/>
                        <xsl:text/>)_||_
                        Encounter datetime must contain one (and only one) date and time content. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:date and kmehr:time])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M34"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M34"/>
    <xsl:template match="@*|node()" priority="-2" mode="M34">
        <xsl:apply-templates select="*" mode="M34"/>
    </xsl:template>

    <!--PATTERN notification.structure.checksNotification structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Notification structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='contactreport']]/kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='transactionreason']]"
            priority="1000"
            mode="M35">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='contactreport']]/kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='transactionreason']]"/>

        <!--ASSERT WARNING-->
        <xsl:choose>
            <xsl:when test="count(kmehr:content[kmehr:text]) &gt; 0"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:text]) &gt; 0">
                    <xsl:attribute name="id">Transactionreason</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">WARNING</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Transaction reason should contain at least one textual content corresponding. (Number:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Un item "transactionreason" devrait contenir au moins un "content" textuel lui correspondant.
                        (Nombre:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Transaction reason zou minstens met minstens één content "text" moeten overeenkomen. (Aantal:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)_||_
                        Transaction reason should contain at least one textual content corresponding. (Zahl:
                        <xsl:text/>
                        <xsl:value-of select="count(kmehr:content[kmehr:text])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M35"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M35"/>
    <xsl:template match="@*|node()" priority="-2" mode="M35">
        <xsl:apply-templates select="*" mode="M35"/>
    </xsl:template>

    <!--PATTERN parameter.structure.checksParameter structure checks-->
    <svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Parameter structure checks</svrl:text>

    <!--RULE -->
    <xsl:template
            match="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='contactreport']]/kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='parameter']]"
            priority="1000"
            mode="M36">
        <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                         context="kmehr:transaction[kmehr:cd[@S='CD-TRANSACTION' and .='contactreport']]/kmehr:item[kmehr:cd[@S = 'CD-ITEM' and .='parameter']]"/>

        <!--ASSERT ERROR-->
        <xsl:choose>
            <xsl:when
                    test="count(kmehr:content[kmehr:decimal and kmehr:unit[kmehr:cd[@S='CD-UNIT' or @S='CD-TIMEUNIT']]]) = 1"/>
            <xsl:otherwise>
                <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                    test="count(kmehr:content[kmehr:decimal and kmehr:unit[kmehr:cd[@S='CD-UNIT' or @S='CD-TIMEUNIT']]]) = 1">
                    <xsl:attribute name="id">Parameter</xsl:attribute>
                    <xsl:attribute name="flag">structure</xsl:attribute>
                    <xsl:attribute name="role">ERROR</xsl:attribute>
                    <xsl:attribute name="location">
                        <xsl:apply-templates select="." mode="schematron-select-full-path"/>
                    </xsl:attribute>
                    <svrl:text>
                        Parameter must contain one (and only one) content with "decimal" and "unit" elements. (Number:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:decimal and kmehr:unit[kmehr:cd[@S='CD-UNIT' or @S='CD-TIMEUNIT']]])"/>
                        <xsl:text/>)_||_
                        Un item "parameter" doit contenir un et un seul élément "content" contenant les éléments
                        "decimal" and "unit". (Nombre:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:decimal and kmehr:unit[kmehr:cd[@S='CD-UNIT' or @S='CD-TIMEUNIT']]])"/>
                        <xsl:text/>)_||_
                        Het item "parameter" moet één enkele "decimal" and "unit" content bevatten. (Aantal:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:decimal and kmehr:unit[kmehr:cd[@S='CD-UNIT' or @S='CD-TIMEUNIT']]])"/>
                        <xsl:text/>)_||_
                        Parameter must contain one (and only one) with "decimal" and "unit" elementst. (Zahl:
                        <xsl:text/>
                        <xsl:value-of
                                select="count(kmehr:content[kmehr:decimal and kmehr:unit[kmehr:cd[@S='CD-UNIT' or @S='CD-TIMEUNIT']]])"/>
                        <xsl:text/>)
                    </svrl:text>
                </svrl:failed-assert>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="*" mode="M36"/>
    </xsl:template>
    <xsl:template match="text()" priority="-1" mode="M36"/>
    <xsl:template match="@*|node()" priority="-2" mode="M36">
        <xsl:apply-templates select="*" mode="M36"/>
    </xsl:template>
</xsl:stylesheet>