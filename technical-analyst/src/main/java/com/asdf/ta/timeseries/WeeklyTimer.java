package com.asdf.ta.timeseries;

import java.util.Calendar;
import com.asdf.ta.workbook.TimeScale;

public class WeeklyTimer implements Timer {
	final private Calendar tmp, cal;

	public WeeklyTimer() {
		this.cal = Calendar.getInstance();
		this.tmp = Calendar.getInstance();
	}
	@Override
	public long currIntervalStartTime(long currT) {
		tmp.setTimeInMillis(currT);
		cal.setTimeInMillis(currT);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		if (cal.after(tmp)) {
			cal.add(Calendar.WEEK_OF_MONTH, -1);
		}
		return cal.getTimeInMillis();
	}
	@Override
	public long nextIntervalStartTime(long currT) {
		return Long.MAX_VALUE;
	}
	@Override
	public TimeScale getScale() {
		return TimeScale.WEEK_1;
	}
}