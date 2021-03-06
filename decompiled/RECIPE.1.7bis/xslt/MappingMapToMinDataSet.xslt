<?xml version="1.0" encoding="UTF-8"?>
<!--
This file was generated by Altova MapForce 2013r2sp2

YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.

Refer to the Altova MapForce Documentation for further details.
http://www.altova.com/mapforce
-->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:agt="http://www.altova.com/Mapforce/agt" xmlns:model="http://www.apb.be/standards/smoa/schema/model/v1" xmlns:ns0="http://www.apb.be/standards/smoa/schema/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fn="http://www.w3.org/2005/xpath-functions" exclude-result-prefixes="agt xs fn">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template name="agt:var9_MapToMinDataSet_function">
		<xsl:param name="par0" as="node()"/>
		<model:entityId>
			<xsl:attribute name="xsi:type" xmlns:n0="http://www.apb.be/standards/smoa/schema/id/v1" select="xs:QName('n0:EntityIdType')"/>
			<xsl:sequence select="($par0/@node())[fn:not(fn:exists(fn:boolean(self::attribute(xsi:type))[.]))]"/>
			<xsl:sequence select="$par0/node()"/>
		</model:entityId>
	</xsl:template>
	<xsl:template name="agt:var11_MapToMinDataSet_function">
		<xsl:param name="par0" as="node()"/>
		<model:personId>
			<xsl:attribute name="xsi:type" xmlns:n0="http://www.apb.be/standards/smoa/schema/id/v1" select="xs:QName('n0:InssIdType')"/>
			<xsl:sequence select="($par0/@node())[fn:not(fn:exists(fn:boolean(self::attribute(xsi:type))[.]))]"/>
			<xsl:sequence select="$par0/node()"/>
		</model:personId>
	</xsl:template>
	<xsl:template name="agt:var12_MapToMinDataSet_function">
		<xsl:param name="par0" as="node()"/>
		<xsl:variable name="var10_resultof_map" as="xs:boolean*">
			<xsl:for-each xmlns:n0="http://www.apb.be/standards/smoa/schema/id/v1" select="$par0/@xsi:type">
				<xsl:sequence select="(fn:resolve-QName(fn:string(.), $par0) = xs:QName('n0:InssIdType'))"/>
			</xsl:for-each>
		</xsl:variable>
		<xsl:if test="fn:exists($var10_resultof_map[.])">
			<xsl:call-template name="agt:var11_MapToMinDataSet_function">
				<xsl:with-param name="par0" select="$par0" as="node()"/>
			</xsl:call-template>
		</xsl:if>
	</xsl:template>
	<xsl:template name="agt:var14_MapToMinDataSet_function">
		<xsl:param name="par0" as="node()"/>
		<model:personId>
			<xsl:attribute name="xsi:type" xmlns:n0="http://www.apb.be/standards/smoa/schema/id/v1" select="xs:QName('n0:LocalIdType')"/>
			<xsl:sequence select="($par0/@node())[fn:not(fn:exists(fn:boolean(self::attribute(xsi:type))[.]))]"/>
			<xsl:sequence select="$par0/node()"/>
		</model:personId>
	</xsl:template>
	<xsl:template name="agt:var15_MapToMinDataSet_function">
		<xsl:param name="par0" as="node()"/>
		<xsl:variable name="var13_resultof_map" as="xs:boolean*">
			<xsl:for-each xmlns:n0="http://www.apb.be/standards/smoa/schema/id/v1" select="$par0/@xsi:type">
				<xsl:sequence select="(fn:resolve-QName(fn:string(.), $par0) = xs:QName('n0:LocalIdType'))"/>
			</xsl:for-each>
		</xsl:variable>
		<xsl:if test="fn:exists($var13_resultof_map[.])">
			<xsl:call-template name="agt:var14_MapToMinDataSet_function">
				<xsl:with-param name="par0" select="$par0" as="node()"/>
			</xsl:call-template>
		</xsl:if>
	</xsl:template>
	<xsl:template name="agt:var19_MapToMinDataSet_function">
		<xsl:param name="par0" as="node()"/>
		<model:entityId>
			<xsl:attribute name="xsi:type" xmlns:n0="http://www.apb.be/standards/smoa/schema/id/v1" select="xs:QName('n0:EntityIdType')"/>
			<xsl:sequence select="($par0/@node())[fn:not(fn:exists(fn:boolean(self::attribute(xsi:type))[.]))]"/>
			<xsl:sequence select="$par0/node()"/>
		</model:entityId>
	</xsl:template>
	<xsl:template name="agt:var20_MapToMinDataSet_function">
		<xsl:param name="par0" as="node()"/>
		<event xmlns="http://www.apb.be/standards/smoa/schema/v1">
			<xsl:attribute name="xsi:type" select="xs:QName('ns0:MedicationHistoryEvent')"/>
			<xsl:for-each select="$par0/ns0:dispensedForSamePrescription/ns0:product">
				<model:medicationHistoryEntity>
					<model:entityId>
						<xsl:attribute name="xsi:type" xmlns:n0="http://www.apb.be/standards/smoa/schema/id/v1" select="xs:QName('n0:EntityIdType')"/>
						<id:id xmlns:id="http://www.apb.be/standards/smoa/schema/id/v1">
							<xsl:sequence select="fn:string(model:dispensationGUID)"/>
						</id:id>
					</model:entityId>
					<xsl:for-each select="$par0/ns0:id">
						<model:sessionID>
							<xsl:sequence select="fn:string(.)"/>
						</model:sessionID>
					</xsl:for-each>
					<model:deliveryDate>
						<xsl:sequence select="xs:string(xs:dateTime(fn:string($par0/ns0:sessionDateTime)))"/>
					</model:deliveryDate>
					<model:pharmacyId>
						<xsl:sequence select="($par0/ns0:pharmacyId/@node(), $par0/ns0:pharmacyId/node())"/>
					</model:pharmacyId>
					<model:min-Patient>
						<xsl:variable name="var8_resultof_filter" as="node()?">
							<xsl:for-each select="$par0/model:max-Patient/model:identification/model:entityId">
								<xsl:variable name="var7_" as="node()" select="."/>
								<xsl:variable name="var6_resultof_map" as="xs:boolean*">
									<xsl:for-each xmlns:n0="http://www.apb.be/standards/smoa/schema/id/v1" select="@xsi:type">
										<xsl:sequence select="(fn:resolve-QName(fn:string(.), $var7_) = xs:QName('n0:EntityIdType'))"/>
									</xsl:for-each>
								</xsl:variable>
								<xsl:if test="fn:exists($var6_resultof_map[.])">
									<xsl:sequence select="."/>
								</xsl:if>
							</xsl:for-each>
						</xsl:variable>
						<xsl:for-each select="$var8_resultof_filter">
							<xsl:call-template name="agt:var9_MapToMinDataSet_function">
								<xsl:with-param name="par0" select="." as="node()"/>
							</xsl:call-template>
						</xsl:for-each>
						<xsl:call-template name="agt:var12_MapToMinDataSet_function">
							<xsl:with-param name="par0" select="$par0/model:max-Patient/model:identification/model:personId" as="node()"/>
						</xsl:call-template>
						<xsl:call-template name="agt:var15_MapToMinDataSet_function">
							<xsl:with-param name="par0" select="$par0/model:max-Patient/model:identification/model:personId" as="node()"/>
						</xsl:call-template>
						<xsl:for-each select="$par0/model:max-Patient/model:identification/model:name">
							<model:name>
								<xsl:sequence select="fn:string(.)"/>
							</model:name>
						</xsl:for-each>
						<model:familyName>
							<xsl:sequence select="fn:string($par0/model:max-Patient/model:identification/model:familyName)"/>
						</model:familyName>
					</model:min-Patient>
					<model:product>
						<xsl:attribute name="onSubstanceName" namespace="" select="xs:string(xs:boolean(fn:string(@onSubstanceName)))"/>
						<model:description>
							<xsl:sequence select="(./model:description/@node(), ./model:description/node())"/>
						</model:description>
						<model:dispensation>
							<xsl:sequence select="(./model:dispensation/@node(), ./model:dispensation/node())"/>
						</model:dispensation>
					</model:product>
					<xsl:for-each select="$par0/ns0:metaDataList">
						<model:metaDataList>
							<xsl:sequence select="(./@node(), ./node())"/>
						</model:metaDataList>
					</xsl:for-each>
				</model:medicationHistoryEntity>
			</xsl:for-each>
			<xsl:for-each select="$par0/ns0:dispensedWithoutPrescription/ns0:product">
				<model:medicationHistoryEntity>
					<model:entityId>
						<xsl:attribute name="xsi:type" xmlns:n0="http://www.apb.be/standards/smoa/schema/id/v1" select="xs:QName('n0:EntityIdType')"/>
						<id:id xmlns:id="http://www.apb.be/standards/smoa/schema/id/v1">
							<xsl:sequence select="fn:string(model:dispensationGUID)"/>
						</id:id>
					</model:entityId>
					<xsl:for-each select="$par0/ns0:id">
						<model:sessionID>
							<xsl:sequence select="fn:string(.)"/>
						</model:sessionID>
					</xsl:for-each>
					<model:deliveryDate>
						<xsl:sequence select="xs:string(xs:dateTime(fn:string($par0/ns0:sessionDateTime)))"/>
					</model:deliveryDate>
					<model:pharmacyId>
						<xsl:sequence select="($par0/ns0:pharmacyId/@node(), $par0/ns0:pharmacyId/node())"/>
					</model:pharmacyId>
					<model:min-Patient>
						<xsl:variable name="var18_resultof_filter" as="node()?">
							<xsl:for-each select="$par0/model:max-Patient/model:identification/model:entityId">
								<xsl:variable name="var17_" as="node()" select="."/>
								<xsl:variable name="var16_resultof_map" as="xs:boolean*">
									<xsl:for-each xmlns:n0="http://www.apb.be/standards/smoa/schema/id/v1" select="@xsi:type">
										<xsl:sequence select="(fn:resolve-QName(fn:string(.), $var17_) = xs:QName('n0:EntityIdType'))"/>
									</xsl:for-each>
								</xsl:variable>
								<xsl:if test="fn:exists($var16_resultof_map[.])">
									<xsl:sequence select="."/>
								</xsl:if>
							</xsl:for-each>
						</xsl:variable>
						<xsl:for-each select="$var18_resultof_filter">
							<xsl:call-template name="agt:var19_MapToMinDataSet_function">
								<xsl:with-param name="par0" select="." as="node()"/>
							</xsl:call-template>
						</xsl:for-each>
						<xsl:for-each select="$par0/model:max-Patient/model:identification/model:name">
							<model:name>
								<xsl:sequence select="fn:string(.)"/>
							</model:name>
						</xsl:for-each>
						<model:familyName>
							<xsl:sequence select="fn:string($par0/model:max-Patient/model:identification/model:familyName)"/>
						</model:familyName>
					</model:min-Patient>
					<model:product>
						<model:description>
							<xsl:sequence select="(./model:description/@node(), ./model:description/node())"/>
						</model:description>
						<model:dispensation>
							<xsl:sequence select="(./model:dispensation/@node(), ./model:dispensation/node())"/>
						</model:dispensation>
					</model:product>
					<xsl:for-each select="$par0/ns0:metaDataList">
						<model:metaDataList>
							<xsl:sequence select="(./@node(), ./node())"/>
						</model:metaDataList>
					</xsl:for-each>
				</model:medicationHistoryEntity>
			</xsl:for-each>
		</event>
	</xsl:template>
	<xsl:template match="/">
		<single-message xmlns="http://www.apb.be/standards/smoa/schema/v1" xmlns:code="http://www.apb.be/standards/smoa/schema/code/v1" xmlns:id="http://www.apb.be/standards/smoa/schema/id/v1" xmlns:model="http://www.apb.be/standards/smoa/schema/model/v1" xmlns:CD="http://www.ehealth.fgov.be/standards/kmehr/cd/v1" xmlns:DT="http://www.ehealth.fgov.be/standards/kmehr/dt/v1" xmlns:ID="http://www.ehealth.fgov.be/standards/kmehr/id/v1" xmlns:kmehr="http://www.ehealth.fgov.be/standards/kmehr/schema/v1" xmlns:sig="http://www.w3.org/2000/09/xmldsig#" xmlns:enc="http://www.w3.org/2001/04/xmlenc#">
			<xsl:attribute name="xsi:schemaLocation" select="'http://www.apb.be/standards/smoa/schema/v1 C:/dev/workspace-oepe/gfddpp/trunk/gfddpp/be-apb-gfddpp-services-smc/src/main/resources/xsd/smc-v3/single-message/maindoc/single-message-oa-1.0.xsd'"/>
			<xsl:for-each select="ns0:single-message/ns0:unsigned">
				<xsl:variable name="var1_resultof_first" as="node()" select="ns0:header"/>
				<unsigned>
					<header>
						<xsl:sequence select="($var1_resultof_first/@node(), $var1_resultof_first/node())"/>
					</header>
					<xsl:for-each select="ns0:eventFolder">
						<eventFolder>
							<xsl:for-each select="ns0:events">
								<xsl:variable name="var5_resultof_filter" as="node()*">
									<xsl:for-each select="ns0:event">
										<xsl:variable name="var3_" as="node()" select="."/>
										<xsl:variable name="var2_resultof_map" as="xs:boolean*">
											<xsl:for-each select="@xsi:type">
												<xsl:sequence select="(fn:resolve-QName(fn:string(.), $var3_) = xs:QName('ns0:PharmaceuticalCareEventType'))"/>
											</xsl:for-each>
										</xsl:variable>
										<xsl:variable name="var4_resultof_any" as="xs:boolean" select="fn:exists($var2_resultof_map[.])"/>
										<xsl:if test="$var4_resultof_any">
											<xsl:sequence select="."/>
										</xsl:if>
									</xsl:for-each>
								</xsl:variable>
								<events>
									<xsl:for-each select="$var5_resultof_filter">
										<xsl:call-template name="agt:var20_MapToMinDataSet_function">
											<xsl:with-param name="par0" select="." as="node()"/>
										</xsl:call-template>
									</xsl:for-each>
								</events>
							</xsl:for-each>
							<xsl:for-each select="ns0:entitySpace">
								<entitySpace>
									<xsl:sequence select="(./@node(), ./node())"/>
								</entitySpace>
							</xsl:for-each>
						</eventFolder>
					</xsl:for-each>
				</unsigned>
			</xsl:for-each>
		</single-message>
	</xsl:template>
</xsl:stylesheet>
