package DatesAndNumbers;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Locale.LanguageRange;

public class TestDate {

	public static void main(String[] args) {
		Date d = new Date();
		String s = d.toString();
		System.out.println(s);
		
		
		Locale loc = new Locale("US");
		Calendar c = Calendar.getInstance(loc);
		c.add(Calendar.DAY_OF_MONTH, -5);
		System.out.println(c);
		
		Calendar c1 = Calendar.getInstance();
		Locale loc1 = new Locale("UK");
		Date d1 = c1.getTime();
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,Locale.UK);
		String sf =  df.format(d);
		System.out.println(sf);
		
		Date d2 = new Date(1000000000000L);
		System.out.println("1st date "+ d2.toString());
		d2.setTime(d1.getTime()+3600000);
		System.out.println("new time "+d1.toString());
	}

}
