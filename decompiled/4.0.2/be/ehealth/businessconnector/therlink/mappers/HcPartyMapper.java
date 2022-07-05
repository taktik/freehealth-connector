package be.ehealth.businessconnector.therlink.mappers;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.fgov.ehealth.hubservices.core.v2.HCPartyIdType;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class HcPartyMapper {
   private static final String APPLICATION = "application_ID";
   private static final String IDVERSION = "1.0";

   public HcPartyMapper() {
   }

   public static HcParty mapHcParty(HcpartyType hcpartyType) {
      HcParty mappedParty = new HcParty();
      mappedParty.setName(hcpartyType.getName());
      mappedParty.setFamilyName(hcpartyType.getFamilyname());
      mappedParty.setFirstName(hcpartyType.getFirstname());
      mappedParty.setCds(hcpartyType.getCds());
      mappedParty.setIds(hcpartyType.getIds());
      mappedParty.setType(((CDHCPARTY)hcpartyType.getCds().get(0)).getValue());
      return mappedParty;
   }

   public static HCPartyIdType mapHcPartyIdType(HcParty hcp) throws TherLinkBusinessConnectorException {
      HCPartyIdType hcpIdType = new HCPartyIdType();
      hcpIdType.setFamilyname(hcp.getFamilyName());
      hcpIdType.setFirstname(hcp.getFirstName());
      hcpIdType.setName(hcp.getName());
      hcpIdType.getIds().addAll(mapIdsAndPropertiesToIdList(hcp));
      hcpIdType.setCd(createHcPartyType(hcp.getType()));
      return hcpIdType;
   }

   public static HcpartyType mapToHcpartyType(HcParty hcp) throws TherLinkBusinessConnectorException {
      HcpartyType hcpType = new HcpartyType();
      hcpType.setFamilyname(hcp.getFamilyName());
      hcpType.setFirstname(hcp.getFirstName());
      hcpType.setName(hcp.getName());
      hcpType.getIds().addAll(mapIdsAndPropertiesToIdList(hcp));
      CDHCPARTY cd = createHcPartyType(hcp.getType());
      hcpType.getCds().add(cd);
      return hcpType;
   }

   private static Collection<? extends IDHCPARTY> mapIdsAndPropertiesToIdList(HcParty hcp) {
      List<IDHCPARTY> result = new ArrayList();
      if (hcp.getIds() != null) {
         result.addAll(hcp.getIds());
      }

      IDHCPARTY ehp;
      if (hcp.getInss() != null) {
         ehp = HcPartyUtil.buildId("1.0", hcp.getInss(), IDHCPARTYschemes.INSS, (String)null);
         if (!verifyListIfIDExists(result, ehp)) {
            result.add(ehp);
         }
      }

      if (hcp.getNihii() != null) {
         ehp = HcPartyUtil.buildId("1.0", hcp.getNihii(), IDHCPARTYschemes.ID_HCPARTY, (String)null);
         if (!verifyListIfIDExists(result, ehp)) {
            result.add(ehp);
         }
      }

      if (hcp.getCbe() != null) {
         ehp = HcPartyUtil.buildId("1.0", hcp.getCbe(), IDHCPARTYschemes.ID_HCPARTY, (String)null);
         if (!verifyListIfIDExists(result, ehp)) {
            result.add(ehp);
         }
      }

      if (hcp.getApplicationID() != null) {
         ehp = HcPartyUtil.buildId("1.0", hcp.getApplicationID(), IDHCPARTYschemes.LOCAL, "application_ID");
         if (!verifyListIfIDExists(result, ehp)) {
            result.add(ehp);
         }
      }

      if (hcp.getEHP() != null) {
         ehp = HcPartyUtil.buildId("1.0", hcp.getEHP(), IDHCPARTYschemes.ID_HCPARTY, (String)null);
         if (!verifyListIfIDExists(result, ehp)) {
            result.add(ehp);
         }
      }

      return result;
   }

   private static boolean verifyListIfIDExists(List<IDHCPARTY> ids, IDHCPARTY idToVerify) {
      Iterator var2 = ids.iterator();

      IDHCPARTY existingId;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         existingId = (IDHCPARTY)var2.next();
      } while(!areIdsTheSame(idToVerify, existingId));

      return true;
   }

   private static boolean areIdsTheSame(IDHCPARTY idToVerify, IDHCPARTY existingId) {
      boolean stheSame = isTheSame(existingId.getS(), idToVerify.getS());
      boolean slTheSame = isTheSame(existingId.getSL(), idToVerify.getSL());
      boolean valueTheSame = isTheSame(existingId.getValue(), idToVerify.getValue());
      return stheSame && slTheSame && valueTheSame;
   }

   private static boolean isTheSame(Object existing, Object toVerify) {
      return existing == null && toVerify == null || existing != null && toVerify != null && existing.equals(toVerify);
   }

   private static CDHCPARTY createHcPartyType(String type) {
      String tempType = type;
      if (type != null) {
         tempType = type.trim();
      }

      return HcPartyUtil.buildCd("1.1", tempType, CDHCPARTYschemes.CD_HCPARTY, (String)null);
   }
}
