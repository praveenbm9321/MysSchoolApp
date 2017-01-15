package com.aikshika;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

public class Demo {
	
	public static int countDayOccurenceInMonth(DayOfWeek dow, YearMonth month) {
		  LocalDate start = month.atDay(1).with(TemporalAdjusters.next(dow));
		  return (int) ChronoUnit.WEEKS.between(start, month.atEndOfMonth()) + 1;
		}

	
	public static int countWeekendDays(int year, int month) {
	    Calendar calendar = Calendar.getInstance();
	    // Note that month is 0-based in calendar, bizarrely.
	    calendar.set(year, month, 1);
	    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	    int count = 0;
	    for (int day = 1; day <= daysInMonth; day++) {
	        calendar.set(year, month, day);
	        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	        if (dayOfWeek == Calendar.SUNDAY) {
	            count++;
	            // Or do whatever you need to with the result.
	        }
	    }
	    return count;
	}
	public static void main(String[] args) {
		
		
		
		
		
		Calendar c = Calendar.getInstance();
	 
	    
	    int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		System.out.println("no of sundays in nov"+countWeekendDays(year, month));
		System.out.println("no of sundays in oct"+countWeekendDays(2016, 10));
		System.out.println("no of sundays in may"+countWeekendDays(2016, 5));
		System.out.println("no of sundays in dec"+countWeekendDays(2016, 12));
		
	   
	}

}
