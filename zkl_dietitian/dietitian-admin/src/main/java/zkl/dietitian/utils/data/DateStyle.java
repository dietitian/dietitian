package zkl.dietitian.utils.data;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期与时间
 * @author Lenovo
 *
 */
public class DateStyle {
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public String getSystemTime(){
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simple.format(new Date());
	}
	
	/**
	 * yyyy-MM-dd
	 * @return
	 */
	public String getSystemDate(){
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(new Date());
	}
	
}
