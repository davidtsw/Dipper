package com.asdf.ta.timeseries;

import com.asdf.ta.workbook.TimeScale;

public interface Timer {
	public long currIntervalStartTime(long currT);
	public long nextIntervalStartTime(long currT);
	public TimeScale getScale();
}
