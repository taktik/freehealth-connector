package be.apb.gfddpp.domain;

import be.apb.standards.smoa.schema.id.v1.AbstractPharmacyIdType;
import be.apb.standards.smoa.schema.id.v1.NihiiIdType;
import be.apb.standards.smoa.schema.id.v1.ObjectFactory;

public enum PharmacyIdType {
	NIHII(NihiiIdType.class) {
		@Override
		public AbstractPharmacyIdType createId(String id) {
			NihiiIdType nihiiId = new ObjectFactory().createNihiiIdType();
			nihiiId.setNihiiPharmacyNumber(id);
			return nihiiId;
		}
		
		@Override
		public String getIdFrom(AbstractPharmacyIdType instance) {
			return ((NihiiIdType) instance).getNihiiPharmacyNumber();
		}
	};
	
	public abstract AbstractPharmacyIdType createId(String id);
	
	public abstract String getIdFrom(AbstractPharmacyIdType instance);
	
	private Class<? extends AbstractPharmacyIdType> type;
	
	private PharmacyIdType(Class<? extends AbstractPharmacyIdType> type) {
		this.type = type;
	}
	
	public Class<? extends AbstractPharmacyIdType> getType() {
		return type;
	}
	
	public static PharmacyIdType valueOf(Class<? extends AbstractPharmacyIdType> type) {
		for (PharmacyIdType instance : PharmacyIdType.values()) {
			if (type.isAssignableFrom(instance.getType())) return instance;
		}
		throw new IllegalArgumentException("unsupported type");
	}
}
