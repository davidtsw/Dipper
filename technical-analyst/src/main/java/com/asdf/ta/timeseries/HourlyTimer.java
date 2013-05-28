package com.asdf.ta.timeseries;

import java.util.Calendar;
import com.asdf.ta.workbook.TimeScale;

public class HourlyTimer extends AbstractTimer {
	public HourlyTimer(TimeScale scale, int interval) {
		super(scale, interval);
	}
	@Override
	protected int getLevel() {
		return Calendar.HOUR_OF_DAY;
	}
	@Override
	protected void trunc(Calendar cal) {
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}
}