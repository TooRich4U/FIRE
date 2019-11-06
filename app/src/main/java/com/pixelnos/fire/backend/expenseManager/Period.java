package com.pixelnos.fire.backend.expenseManager;

public enum Period {
	DAILY, WEEKLY, BIWEEKLY, MONTHLY, QUARTERLY, ANNUALY;
	
	public static double getPeriodInDays(Period period) {
		switch(period) {
		case DAILY:
			return 1;
		case WEEKLY:
			return 7;
		case BIWEEKLY:
			return 14;
		case MONTHLY:
			return 30.44;
		case QUARTERLY:
			return 91.31;
		case ANNUALY:
			return 365.24;
		default: return 0;
		}
	}
}