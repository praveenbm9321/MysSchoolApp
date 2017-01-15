package com.aikshika.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

public class WorkingDaysInPreviousMonth {
	
	public static int countDayOccurenceInMonth(DayOfWeek dow, YearMonth month) {
		  LocalDate start = month.atDay(1).with(TemporalAdjusters.nextOrSame(dow));
		  return (int) ChronoUnit.WEEKS.between(start, month.atEndOfMonth()) + 1;
		}
	
	public static int workingDays()
	{
		Calendar cal1 = Calendar.getInstance();
        int prevMonth = cal1.get(Calendar.MONTH);
        System.out.println("previous month:"+prevMonth);
        int workingDays = 0;
       
        System.out.println("current year:"+(Calendar.getInstance().get(Calendar.YEAR)));
        int year = 0;
        if(prevMonth==0){
      	  year = (Calendar.getInstance().get(Calendar.YEAR))-1;
      	  prevMonth = 12;
      	  System.out.println("year:"+year+" "+"month:"+prevMonth);
            int sundays = countDayOccurenceInMonth(DayOfWeek.SUNDAY, YearMonth.of(year, prevMonth));
            System.out.println("if sundays:"+sundays);
            YearMonth yearMonthObject = YearMonth.of(year, prevMonth);
            int daysInMonth = yearMonthObject.lengthOfMonth();
            System.out.println("days in a month:"+daysInMonth);
            workingDays = daysInMonth - sundays;
            return workingDays;
           
        }
        else
        {
        year = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println("year:"+year+" "+"month:"+prevMonth);
        int sundays = countDayOccurenceInMonth(DayOfWeek.SUNDAY, YearMonth.of(year, prevMonth));
        System.out.println("else sundays:"+sundays);
        YearMonth yearMonthObject = YearMonth.of(year, prevMonth);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        System.out.println("days in a month:"+daysInMonth);
        workingDays = daysInMonth - sundays;
        return workingDays;
        }
	}

}
