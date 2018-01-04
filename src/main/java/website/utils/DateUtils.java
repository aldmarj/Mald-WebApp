/**
 * 
 */
package website.utils;

import java.util.Calendar;

/**
 * @author Lawrence
 *
 */
public abstract class DateUtils {

		public static long getStartOfCurrentMonthInMillis() {
	        Calendar calendar = Calendar.getInstance();
	        
	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH);
	        
	        calendar.set(year, month, 0, 0, 0, 0);
	        return calendar.getTimeInMillis();
		}
		
		public static long getEndOfCurrentMonthInMillis() {
	        Calendar calendar = Calendar.getInstance();
	        
	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH + 1);
	        
	        calendar.set(year, month, 0, 0, 0, 0);
	        return calendar.getTimeInMillis();
		}
}
