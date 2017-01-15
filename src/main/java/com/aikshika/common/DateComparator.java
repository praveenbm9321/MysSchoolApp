package com.aikshika.common;

import java.util.Comparator;
import java.util.Date;

import com.aikshika.entity.Tblhomework;


public class DateComparator implements Comparator<Tblhomework> {

	@Override
	public int compare(Tblhomework o1, Tblhomework o2) {
		System.out.println("inside compare method.........");
		
		Date assignDate1 = o1.getDtAssignDate();
		Date assignDate2 = o2.getDtAssignDate();
		System.out.println(assignDate1);
		System.out.println(assignDate2);
		return assignDate2.compareTo(assignDate1);
	}

}
