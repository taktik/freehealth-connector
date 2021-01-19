package be.ehealth.businessconnector.chapterIV.builders.impl;

import be.ehealth.businessconnector.chapterIV.builders.QualityBuilder;

/** @deprecated */
@Deprecated
public class QualityBuilderPersPhysician implements QualityBuilder {
   public String getQualityForCareProvider() {
      return "doctor";
   }
}
