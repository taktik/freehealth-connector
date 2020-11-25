package be.apb.gfddpp.domain;

import be.apb.gfddpp.productfilter.MedicinesRanges;
import be.apb.gfddpp.productfilter.PreparationsRanges;
import be.apb.gfddpp.productfilter.RangesType;

public enum RangeType {
	MEDICINE(MedicinesRanges.class){
		
	},
	PREPARATION(PreparationsRanges.class){
		
	};
	
	private Class<? extends RangesType> type;
	
	RangeType(Class<? extends RangesType> type){
		this.type = type;
	}
	
	public static RangeType lookupType(Class<? extends RangesType> type){
		for(RangeType supported: RangeType.values()){
			if (type.isAssignableFrom(supported.getType())) return supported;
		}
		throw new IllegalArgumentException();
	}
	
	public Class<? extends RangesType> getType(){
		return type;
	}
	
	public static <T extends RangesType> RangeType valueOf(T instance) {
		return lookupType(instance.getClass());
	}
	
}
