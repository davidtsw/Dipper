package com.asdf.ta.workbook;

import com.asdf.ta.timeseries.DailyTimer;
import com.asdf.ta.timeseries.HourlyTimer;
import com.asdf.ta.timeseries.MinutelyTimer;
import com.asdf.ta.timeseries.MonthlyTimer;
import com.asdf.ta.timeseries.Timer;
import com.asdf.ta.timeseries.WeeklyTimer;

public enum TimeScale {
	/** one minute */
	MINUTE_1(60) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 1);
		}
	},
	/** two minutes */
	MINUTE_2(120) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 2);
		}
	},
	/** three minutes */
	MINUTE_3(180) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 3);
		}
	},
	/** four minutes */
	MINUTE_4(240) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 4);
		}
	},
	/** five minutes */
	MINUTE_5(300) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 5);
		}
	},
	/** six minutes */
	MINUTE_6(360) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 6);
		}
	},
	/** ten minutes */
	MINUTE_10(600) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 10);
		}
	},
	/** twelve minutes */
	MINUTE_12(720) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 12);
		}
	},
	/** fifteen minutes */
	MINUTE_15(900) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 15);
		}
	},
	/** twenty minutes */
	MINUTE_20(1200) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 20);
		}
	},
	/** thirty minutes */
	MINUTE_30(1800) {
		@Override
		public Timer getTimer() {
			return new MinutelyTimer(this, 30);
		}
	},
	/** one hour */
	HOUR_1(3600) {
		@Override
		public Timer getTimer() {
			return new HourlyTimer(this, 1);
		}
	},
	/** two hours */
	HOUR_2(7200) {
		@Override
		public Timer getTimer() {
			return new HourlyTimer(this, 2);
		}
	},
	/** three hours */
	HOUR_3(10800) {
		@Override
		public Timer getTimer() {
			return new HourlyTimer(this, 3);
		}
	},
	/** four hours */
	HOUR_4(14400) {
		@Override
		public Timer getTimer() {
			return new HourlyTimer(this, 4);
		}
	},
	/** six hours */
	HOUR_6(21600) {
		@Override
		public Timer getTimer() {
			return new HourlyTimer(this, 6);
		}
	},
	/** eight hours */
	HOUR_8(28800) {
		@Override
		public Timer getTimer() {
			return new HourlyTimer(this, 8);
		}
	},
	/** twelve hours */
	HOUR_12(43200) {
		@Override
		public Timer getTimer() {
			return new HourlyTimer(this, 12);
		}
	},
	/** one day */
	DAY_1(86400) {
		@Override
		public Timer getTimer() {
			return new DailyTimer();
		}
	},
	/** one week */
	WEEK_1(604800) {
		@Override
		public Timer getTimer() {
			return new WeeklyTimer();
		}
	},
	/** one month */
	MONTH_1(2419200) {
		@Override
		public Timer getTimer() {
			return new MonthlyTimer();
		}
	};
	private final int interval;

	private TimeScale(int interval) {
		this.interval = interval;
	}
	/**
	 * Returns the time interval of the scale<br>
	 * <i>approximate to number of second</i>
	 * @return the time interval of the scale
	 */
	public int getInterval() {
		return interval;
	}
	abstract public Timer getTimer();
}
