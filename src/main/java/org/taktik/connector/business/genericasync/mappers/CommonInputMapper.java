package org.taktik.connector.business.genericasync.mappers;

import be.cin.mycarenet.esb.common.v2.CareProviderType;
import be.cin.mycarenet.esb.common.v2.CommonInput;
import be.cin.mycarenet.esb.common.v2.IdType;
import be.cin.mycarenet.esb.common.v2.LicenseType;
import be.cin.mycarenet.esb.common.v2.NihiiType;
import be.cin.mycarenet.esb.common.v2.OrigineType;
import be.cin.mycarenet.esb.common.v2.PackageType;
import be.cin.mycarenet.esb.common.v2.PartyType;
import be.cin.mycarenet.esb.common.v2.RequestType;
import be.cin.mycarenet.esb.common.v2.ValueRefString;
import org.dozer.DozerBeanMapper;
import org.taktik.connector.business.mycarenetdomaincommons.domain.CareProvider;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Identification;
import org.taktik.connector.business.mycarenetdomaincommons.domain.McnPackageInfo;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Nihii;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Origin;
import org.taktik.connector.technical.config.util.domain.PackageInfo;

import java.util.ArrayList;
import java.util.List;

public final class CommonInputMapper {
	public static CommonInput mapCommonInputType(org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput input) {
		CommonInput destObject = new CommonInput();

		destObject.setRequest(new RequestType());
		destObject.getRequest().setIsTest(input.isTest());
		destObject.setInputReference(input.getInputReference());

		if (input.getOrigin() != null) {
			OrigineType origin = new OrigineType();
			if (input.getOrigin().getCareProvider() != null) {
				origin.setCareProvider(mapCareProvider(input.getOrigin().getCareProvider()));
			}
			if (input.getOrigin().getMcnPackageInfo() != null) {
				origin.setPackage(mapMcnPackage(input.getOrigin().getMcnPackageInfo()));
			}
			if (input.getOrigin().getSiteId() != null) {
				origin.setSiteID(createValueRefString(input.getOrigin().getSiteId()));
			}

			if (input.getOrigin().getSender() != null) {
				PartyType sender = new PartyType();
				if (input.getOrigin().getSender().getOrganization() != null) {
					sender.setOrganization(mapIdType(input.getOrigin().getSender().getOrganization()));
				}
				if (input.getOrigin().getSender().getPhysicalPerson() != null) {
					sender.setPhysicalPerson(mapIdType(input.getOrigin().getSender().getPhysicalPerson()));
				}
				origin.setSender(sender);
			}
			destObject.setOrigin(origin);
		}

		return destObject;
	}


	/**
	 * @deprecated
	 */
	@Deprecated
	static PackageType mapPackage(PackageInfo packageInfo) {
		PackageType result = new PackageType();
		LicenseType license = new LicenseType();
		String password = packageInfo.getPassword();
		if (password != null) {
			license.setPassword(password);
		}

		String userName = packageInfo.getUserName();
		if (userName != null) {
			license.setUsername(userName);
		}

		result.setLicense(license);
		String packageName = packageInfo.getPackageName();
		if (packageName != null) {
			result.setName(createValueRefString(packageName));
		}

		return result;
	}

	static PackageType mapMcnPackage(McnPackageInfo packageInfo) {
		return mapPackage(packageInfo);
	}

	static CareProviderType mapCareProvider(CareProvider careProvider) {
		CareProviderType result = new CareProviderType();
		Nihii nihii = careProvider.getNihii();
		if (nihii != null) {
			result.setNihii(mapNihii(nihii));
		}

		Identification organization = careProvider.getOrganization();
		if (organization != null) {
			result.setOrganization(mapIdType(organization));
		}

		Identification physicalPerson = careProvider.getPhysicalPerson();
		if (physicalPerson != null) {
			result.setPhysicalPerson(mapIdType(physicalPerson));
		}

		return result;
	}

	static IdType mapIdType(Identification organization) {
		IdType result = new IdType();
		String cbe = organization.getCbe();
		if (cbe != null) {
			result.setCbe(createValueRefString(cbe));
		}

		String name = organization.getName();
		if (name != null) {
			result.setName(createValueRefString(name));
		}

		Nihii nihii = organization.getNihii();
		if (nihii != null) {
			result.setNihii(mapNihii(nihii));
		}

		String ssin = organization.getSsin();
		if (ssin != null) {
			result.setSsin(createValueRefString(ssin));
		}

		return result;
	}

	static NihiiType mapNihii(Nihii nihii) {
		NihiiType result = new NihiiType();
		String quality = nihii.getQuality();
		if (quality != null) {
			result.setQuality(quality);
		}

		String value = nihii.getValue();
		if (value != null) {
			result.setValue(createValueRefString(value));
		}

		return result;
	}

	static ValueRefString createValueRefString(String value) {
		ValueRefString result = new ValueRefString();
		result.setValue(value);
		return result;
	}
}
