package be.apb.gfddpp.domain;

import be.apb.standards.smoa.schema.id.v1.AbstractCareServiceIdType;
import be.apb.standards.smoa.schema.id.v1.CbeIdType;
import be.apb.standards.smoa.schema.id.v1.ObjectFactory;

public enum CareServiceIdType {
	CBE(CbeIdType.class) {
		@Override
		public AbstractCareServiceIdType createId(String id) {
			CbeIdType cbeId = new ObjectFactory().createCbeIdType();
			cbeId.setCbe(id);
			return cbeId;
		}
		
		@Override
		public String getIdFrom(AbstractCareServiceIdType instance) {
			return ((CbeIdType) instance).getCbe();
		}
	};
	
	public abstract AbstractCareServiceIdType createId(String id);
	
	public abstract String getIdFrom(AbstractCareServiceIdType instance);
	
	private Class<? extends AbstractCareServiceIdType> type;
	
	private CareServiceIdType(Class<? extends AbstractCareServiceIdType> type) {
		this.type = type;
	}
	
	public Class<? extends AbstractCareServiceIdType> getType() {
		return type;
	}
	
	public static CareServiceIdType valueOf(Class<? extends AbstractCareServiceIdType> type) {
		for (CareServiceIdType instance : CareServiceIdType.values()) {
			if (type.isAssignableFrom(instance.getType())) return instance;
		}
		throw new IllegalArgumentException("unsupported type");
	}
}
