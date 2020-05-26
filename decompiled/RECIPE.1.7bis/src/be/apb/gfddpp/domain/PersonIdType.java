package be.apb.gfddpp.domain;

import be.apb.standards.smoa.schema.id.v1.AbstractPersonIdType;
import be.apb.standards.smoa.schema.id.v1.InssIdType;
import be.apb.standards.smoa.schema.id.v1.LocalIdType;
import be.apb.standards.smoa.schema.id.v1.ObjectFactory;

public enum PersonIdType {
	INSS(InssIdType.class) {
		@Override
		public AbstractPersonIdType createId(String id) {
			InssIdType inssId = new ObjectFactory().createInssIdType();
			inssId.setId(id);
			return inssId;
		}
		
		@Override
		public String getIdFrom(AbstractPersonIdType instance) {
			return ((InssIdType) instance).getId();
		}
	},
	LOCAL(LocalIdType.class) {
		@Override
		public AbstractPersonIdType createId(String id) {
			LocalIdType localId = new ObjectFactory().createLocalIdType();
			localId.setId(id);
			return localId;
		}
		
		@Override
		public String getIdFrom(AbstractPersonIdType instance) {
			return ((LocalIdType) instance).getId();
		}
	};
	
	public abstract AbstractPersonIdType createId(String id);
	
	public abstract String getIdFrom(AbstractPersonIdType instance);
	
	private Class<? extends AbstractPersonIdType> type;
	
	private PersonIdType(Class<? extends AbstractPersonIdType> type) {
		this.type = type;
	}
	
	public Class<? extends AbstractPersonIdType> getType() {
		return type;
	}
	
	public static PersonIdType valueOf(Class<? extends AbstractPersonIdType> type) {
		for (PersonIdType instance : PersonIdType.values()) {
			if (type.isAssignableFrom(instance.getType())) return instance;
		}
		throw new IllegalArgumentException("unsupported type");
	}
	
	public static <T extends AbstractPersonIdType> PersonIdType valueOf(T instance) {
		return valueOf(instance.getClass());
	}
}
