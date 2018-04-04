package be.ehealth.business.kmehrcommons.builders;

import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.SessionUtil;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESS;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESSschemes;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRY;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRYschemes;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.AddressType;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.CountryType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;

public class HcPartyBuilder implements ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private HcpartyType hcParty = new HcpartyType();

   public HcpartyType build() throws TechnicalConnectorException {
      if (this.hcParty.getCds().size() == 0) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.BUILDER_VALIDATION_EXCEPTION, new Object[]{"HcPartyBuilder", "invalid HcParty object : at least one Cd is needed "});
      } else {
         return this.hcParty;
      }
   }

   public HcPartyBuilder id(String sv, String value) {
      this.hcParty.getIds().add((new Id()).value(value).sv(sv).s(IDHCPARTYschemes.ID_HCPARTY).build());
      return this;
   }

   public HcPartyBuilder id(String sv, String value, IDHCPARTYschemes scheme, String sl) {
      this.hcParty.getIds().add((new Id()).value(value).sv(sv).s(scheme).sl(sl).build());
      return this;
   }

   public HcPartyBuilder id(String sv, String value, IDHCPARTYschemes scheme) {
      this.hcParty.getIds().add((new Id()).value(value).sv(sv).s(scheme).build());
      return this;
   }

   public HcPartyBuilder cd(String sv, String value, CDHCPARTYschemes scheme) {
      this.hcParty.getCds().add((new Cd()).sv(sv).value(value).s(scheme).build());
      return this;
   }

   public HcPartyBuilder cd(String sv, String value, CDHCPARTYschemes scheme, String sl) {
      this.hcParty.getCds().add((new Cd()).sv(sv).sl(sl).s(scheme).value(value).build());
      return this;
   }

   public HcPartyBuilder idHcPartyId(String value, String sv) {
      if (value != null && sv != null) {
         return this.id(sv, value, IDHCPARTYschemes.ID_HCPARTY);
      } else {
         throw new IllegalArgumentException("Local id should have parameters : value and sv filled out");
      }
   }

   public HcPartyBuilder localId(String value, String sv, String sl) {
      if (value != null && sv != null) {
         return this.id(sv, value, IDHCPARTYschemes.LOCAL, sl);
      } else {
         throw new IllegalArgumentException("Local id should have parameters : value and sv filled out");
      }
   }

   public HcPartyBuilder idFromSessionNiss(String sv) throws TechnicalConnectorException {
      if (sv == null) {
         throw new IllegalArgumentException("Inss id should have parameters : sv filled out");
      } else {
         return this.id(sv, SessionUtil.getNiss(), IDHCPARTYschemes.INSS);
      }
   }

   public HcPartyBuilder idFromSessionNihii(String sv) throws TechnicalConnectorException {
      if (sv == null) {
         throw new IllegalArgumentException("Inss id should have parameters : sv and value filled out");
      } else {
         return this.id(sv, SessionUtil.getNihii11(), IDHCPARTYschemes.ID_HCPARTY);
      }
   }

   public HcPartyBuilder inssId(String value, String sv) {
      if (value != null && sv != null) {
         return this.id(sv, value, IDHCPARTYschemes.INSS);
      } else {
         throw new IllegalArgumentException("Inss id should have parameters : sv and value filled out");
      }
   }

   public HcPartyBuilder localCd(String value, String sv, String sl) {
      if (value != null && sv != null && sl != null) {
         return this.cd(sv, value, CDHCPARTYschemes.LOCAL, sl);
      } else {
         throw new IllegalArgumentException("setLocalCd : empty input values not allowed inputValues : sv=" + sv + ", value=" + value);
      }
   }

   public HcPartyBuilder cdHcPartyCd(String value, String sv) {
      if (value != null && sv != null) {
         return this.cd(sv, value, CDHCPARTYschemes.CD_HCPARTY);
      } else {
         throw new IllegalArgumentException("Cd HcParty: parameters value and sv must be filled out");
      }
   }

   public HcPartyBuilder name(String name) {
      if (name == null) {
         throw new IllegalArgumentException("Parameter name must be filled out");
      } else {
         this.hcParty.setName(name);
         return this;
      }
   }

   public HcPartyBuilder firstname(String firstname) {
      if (firstname == null) {
         throw new IllegalArgumentException("parameter firstname must be filled out");
      } else {
         this.hcParty.setFirstname(firstname);
         return this;
      }
   }

   public HcPartyBuilder lastname(String lastName) {
      if (lastName == null) {
         throw new IllegalArgumentException("parameter lastname must be filled out");
      } else {
         this.hcParty.setFamilyname(lastName);
         return this;
      }
   }

   public HcPartyBuilder address(String addressCdValue, CDADDRESSschemes addressCdSchemes, String addressCdSv, String city, String district, String houseNumber, String nis, String postBox, String street, String zip, String countryValue, CDCOUNTRYschemes countrySchemes, String countrySv) {
      if (addressCdValue != null && addressCdSv != null && addressCdSchemes != null) {
         AddressType address = new AddressType();
         address.setCity(city);
         address.setDistrict(district);
         address.setHousenumber(houseNumber);
         address.setNis(nis);
         address.setPostboxnumber(postBox);
         address.setStreet(street);
         address.setZip(zip);
         CDADDRESS cdAddress = new CDADDRESS();
         cdAddress.setS(addressCdSchemes);
         cdAddress.setSV(addressCdSv);
         cdAddress.setValue(addressCdValue);
         address.getCds().add(cdAddress);
         if (countryValue != null && countrySchemes != null && countrySv != null) {
            CountryType countryType = new CountryType();
            CDCOUNTRY cdCountry = new CDCOUNTRY();
            cdCountry.setS(countrySchemes);
            cdCountry.setSV(countrySv);
            cdCountry.setValue(countryValue);
            countryType.setCd(cdCountry);
            address.setCountry(countryType);
         }

         this.hcParty.getAddresses().add(address);
         return this;
      } else {
         throw new IllegalArgumentException("parameter address must be filled out");
      }
   }

   /** @deprecated */
   @Deprecated
   public HcPartyBuilder setLocalCd(String type, String value) {
      return this.cd("1.1", value, CDHCPARTYschemes.LOCAL, type);
   }

   /** @deprecated */
   @Deprecated
   public HcPartyBuilder setIdhcpartyId(String value) {
      return this.idHcPartyId(value, "1.0");
   }

   /** @deprecated */
   @Deprecated
   public HcPartyBuilder setLocalId(String type, String value) {
      return this.localId(value, "1.0", type);
   }

   /** @deprecated */
   @Deprecated
   public HcPartyBuilder setInssId(String value) {
      return this.inssId(value, "1.0");
   }

   /** @deprecated */
   @Deprecated
   public HcPartyBuilder setCdhcpartyCd(String value) {
      return this.cdHcPartyCd(value, "1.1");
   }

   /** @deprecated */
   @Deprecated
   public HcPartyBuilder setName(String name) {
      return this.name(name);
   }

   /** @deprecated */
   @Deprecated
   public HcPartyBuilder setFirstName(String firstname) {
      return this.firstname(firstname);
   }

   /** @deprecated */
   @Deprecated
   public HcPartyBuilder setLastName(String lastName) {
      return this.lastname(lastName);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(HcpartyType.class);
      JaxbContextFactory.initJaxbContext(AuthorType.class, HcpartyType.class);
      JaxbContextFactory.initJaxbContext(CDCONSENT.class);
   }
}
