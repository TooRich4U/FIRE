package com.pixelnos.fire.backend.expenseManager;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PeriodTest {

	@Test
	public void getPeriodInDaysTest() {
		assertEquals(1, Period.getPeriodInDays(Period.DAILY),0.01);
		assertEquals(7, Period.getPeriodInDays(Period.WEEKLY),0.01);
		assertEquals(14, Period.getPeriodInDays(Period.BIWEEKLY),0.01);
		assertEquals(30.44, Period.getPeriodInDays(Period.MONTHLY),0.01);
		assertEquals(91.31, Period.getPeriodInDays(Period.QUARTERLY),0.01);
		assertEquals(365.24, Period.getPeriodInDays(Period.ANNUALY),0.01);
	}
}
