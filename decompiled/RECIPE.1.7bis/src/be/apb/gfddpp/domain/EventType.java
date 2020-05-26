package be.apb.gfddpp.domain;

import be.apb.standards.smoa.schema.v1.AbstractEventType;
import be.apb.standards.smoa.schema.v1.BvacEventType;
import be.apb.standards.smoa.schema.v1.ContinuedPharmaceuticalCareDossierEvent;
import be.apb.standards.smoa.schema.v1.MedicationDeliveryEventType;
import be.apb.standards.smoa.schema.v1.MedicationHistoryEvent;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType;
import be.apb.standards.smoa.schema.v1.ArchivePrescriptionEventType;
import be.apb.standards.smoa.schema.v1.ArchivePrescriptionCommentEventType;
import be.apb.standards.smoa.schema.v1.RegisterExportEventType;

public enum EventType {
	MEDICATION_HISTORY(MedicationHistoryEvent.class){
		
	},
	CONTINUED_PHARMACEUTICAL_CARE_DOSSIER(ContinuedPharmaceuticalCareDossierEvent.class){
		
	},	
	PHARMACEUTICAL_CARE(PharmaceuticalCareEventType.class){
		
	},
	ARCHIVE_PRESCRIPTION(ArchivePrescriptionEventType.class){
		
	},
	ARCHIVE_PRESCRIPTION_COMMENT(ArchivePrescriptionCommentEventType.class){
		
	},
	MEDICATION_DELIVERY(MedicationDeliveryEventType.class){
		
	},
	BVAC(BvacEventType.class){
		
	},
	REGISTER_EXPORT(RegisterExportEventType.class){
				
	};
	private final Class<? extends AbstractEventType> type;
	
	EventType(Class<? extends AbstractEventType> type){
		this.type = type;
	}
	
	public static EventType lookupType(Class<? extends AbstractEventType> type){
		for(EventType supported: EventType.values()){
			if (type.isAssignableFrom(supported.getType())) return supported;
		}
		throw new IllegalArgumentException();
	}

	
	public Class<? extends AbstractEventType> getType(){
		return type;
	}
	
	public static <T extends AbstractEventType> EventType valueOf(T instance) {
		return lookupType(instance.getClass());
	}
	
//    ,
//    MedicationHistoryEvent.class,
//    PharmaceuticalCareEventType.class,
//    ArchivePrescriptionEventType.class,
//    ArchivePrescriptionCommentEventType.class,
//    MedicationDeliveryEventType.class,
//    RegisterExportEventType.class
}
