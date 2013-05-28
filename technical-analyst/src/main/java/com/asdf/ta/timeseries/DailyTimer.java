package com.asdf.ta.timeseries;

import java.util.Calendar;
import com.asdf.ta.workbook.TimeScale;

public class DailyTimer implements Timer {
	final private Calendar cal;

	public DailyTimer() {
		this.cal = Calendar.getInstance();
	}
	@Override
	public long currIntervalStartTime(long currT) {
		cal.setTimeInMillis(currT);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
	@Override
	public long nextIntervalStartTime(long currT) {
		return Long.MAX_VALUE;
	}
	@Override
	public TimeScale getScale() {
		return TimeScale.MONTH_1;
	}
}