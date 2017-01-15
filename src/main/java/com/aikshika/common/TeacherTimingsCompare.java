package com.aikshika.common;

import java.util.Comparator;

import com.aikshika.entity.Mstteachertimetable;

public class TeacherTimingsCompare implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		Mstteachertimetable s1 = (Mstteachertimetable) o1;
		Mstteachertimetable s2 = (Mstteachertimetable) o2;

		if (s1.getMsttimings().getIntMsttimingsId() == s2.getMsttimings().getIntMsttimingsId()) {
			return 0;
		} else if (s1.getMsttimings().getIntMsttimingsId() > s2.getMsttimings().getIntMsttimingsId()) {
			return 1;
		} else {
			return -1;
		}
	}

}
