package be.apb.gfddpp.domain;

import be.apb.standards.smoa.schema.id.v1.AbstractMedicinalProductIdType;
import be.apb.standards.smoa.schema.id.v1.AbstractPersonIdType;
import be.apb.standards.smoa.schema.id.v1.AtcDddSystemIdType;
import be.apb.standards.smoa.schema.id.v1.BeRegistrationIdType;
import be.apb.standards.smoa.schema.id.v1.CnkIdType;
import be.apb.standards.smoa.schema.id.v1.InssIdType;
import be.apb.standards.smoa.schema.id.v1.LocalIdType;
import be.apb.standards.smoa.schema.id.v1.ObjectFactory;

public enum ProductIdType {
	ATC(AtcDddSystemIdType.class) {
		@Override
		public AbstractMedicinalProductIdType createId(String id) {
			AtcDddSystemIdType  atcDddSystemId = new ObjectFactory().createAtcDddSystemIdType();
			atcDddSystemId.setAtc(id);
			return atcDddSystemId;
		}
		
		@Override
		public String getIdFrom(AbstractMedicinalProductIdType instance) {
			return ((AtcDddSystemIdType) instance).getAtc();
		}
	},
	CNK(CnkIdType.class) {
		@Override
		public AbstractMedicinalProductIdType createId(String id) {
			CnkIdType cnkId = new ObjectFactory().createCnkIdType();
			cnkId.setCnk(id);
			return cnkId;
		}
		
		@Override
		public String getIdFrom(AbstractMedicinalProductIdType instance) {
			return ((CnkIdType) instance).getCnk();
		}
	},

	BEREGISTRATIONID(BeRegistrationIdType.class) {
		@Override
		public AbstractMedicinalProductIdType createId(String id) {
			BeRegistrationIdType beRegistrationIdType = new ObjectFactory().createBeRegistrationIdType();
			beRegistrationIdType.setCti(Integer.parseInt(id));
			return beRegistrationIdType;
		}
		
		@Override
		public String getIdFrom(AbstractMedicinalProductIdType instance) {
			return String.valueOf(((BeRegistrationIdType) instance).getCti());
		}
	};
	
	public abstract AbstractMedicinalProductIdType createId(String id);
	
	public abstract String getIdFrom(AbstractMedicinalProductIdType instance);
	
	private Class<? extends AbstractMedicinalProductIdType> type;
	
	private ProductIdType(Class<? extends AbstractMedicinalProductIdType> type) {
		this.type = type;
	}
	
	public Class<? extends AbstractMedicinalProductIdType> getType() {
		return type;
	}
	
	public static ProductIdType valueOf(Class<? extends AbstractMedicinalProductIdType> type) {
		for (ProductIdType instance : ProductIdType.values()) {
			if (type.isAssignableFrom(instance.getType())) return instance;
		}
		throw new IllegalArgumentException("unsupported type");
	}
	
	public static <T extends AbstractMedicinalProductIdType> ProductIdType valueOf(T instance) {
		return valueOf(instance.getClass());
	}
}
