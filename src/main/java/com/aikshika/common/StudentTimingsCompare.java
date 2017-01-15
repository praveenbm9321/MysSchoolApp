package com.aikshika.common;

import java.util.Comparator;

import com.aikshika.entity.Mststudenttimetable;

public class StudentTimingsCompare implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		Mststudenttimetable s1 = (Mststudenttimetable) o1;
		Mststudenttimetable s2 = (Mststudenttimetable) o2;

		if (s1.getMsttimings().getIntMsttimingsId() == s2.getMsttimings().getIntMsttimingsId()) {
			return 0;
		} else if (s1.getMsttimings().getIntMsttimingsId() > s2.getMsttimings().getIntMsttimingsId()) {
			return 1;
		} else {
			return -1;
		}
	}

}
