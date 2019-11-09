package com.pixelnos.fire.backend.manager;

public enum Period {
	DAILY, WEEKLY, BIWEEKLY, MONTHLY, QUARTERLY, ANNUALLY;
	
	public static double getPeriodInDays(Period period) {
		switch(period) {
		case WEEKLY:
		return 7;
		case BIWEEKLY:
			return 14;
		case MONTHLY:
			return 30.44;
		case QUARTERLY:
			return 91.31;
		case ANNUALLY:
			return 365.24;
		case DAILY:
		default: return 1;
		}
	}
}