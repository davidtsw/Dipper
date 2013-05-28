package com.asdf.ta.timeseries;

import java.util.Calendar;
import com.asdf.ta.workbook.TimeScale;

public class MinutelyTimer extends AbstractTimer {
	public MinutelyTimer(TimeScale scale, int interval) {
		super(scale, interval);
	}
	@Override
	protected int getLevel() {
		return Calendar.MINUTE;
	}
	@Override
	protected void trunc(Calendar cal) {
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}
}