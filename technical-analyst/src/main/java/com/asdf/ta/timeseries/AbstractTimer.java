package com.asdf.ta.timeseries;

import java.util.Calendar;
import com.asdf.ta.workbook.TimeScale;

public abstract class AbstractTimer implements Timer {
	final private TimeScale scale;
	final private int interval;
	final private Calendar cal;

	public AbstractTimer(TimeScale scale, int interval) {
		this.scale = scale;
		this.interval = interval;
		this.cal = Calendar.getInstance();
	}
	@Override
	public long currIntervalStartTime(long currT) {
		return calcIntervalStartTime(currT, 0);
	}
	@Override
	public long nextIntervalStartTime(long currT) {
		return calcIntervalStartTime(currT, interval);
	}
	@Override
	public TimeScale getScale() {
		return scale;
	}
	private long calcIntervalStartTime(long currT, int shift) {
		cal.setTimeInMillis(currT);
		trunc(cal);
		int lvT = cal.get(getLevel()) / interval * interval;
		cal.set(getLevel(), lvT + shift);
		return cal.getTime().getTime();
	}
	abstract protected int getLevel();
	abstract protected void trunc(Calendar cal);
}
