package cn.edu.jju.outschoolreaders.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 日期时间格式化
 * @author yanjisheng
 *
 */
@Component
public class DateTimeFormatter implements Converter<String, Date>{
	
	private SimpleDateFormat onlyDate = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat onlyTime = new SimpleDateFormat("HH:mm:ss");
	private SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public synchronized String toDate(Date date){
		return onlyDate.format(date);
	}
	
	public synchronized String toTime(Date date){
		return onlyTime.format(date);
	}
	
	public synchronized String toDateTime(Date date){
		return dateTime.format(date);
	}
	
	public synchronized Date fromDateString(String dateString) throws ParseException{
		return onlyDate.parse(dateString);
	}
	
	public synchronized Date fromTimeString(String timeString) throws ParseException{
		return onlyTime.parse(timeString);
	}
	
	public synchronized Date fromDateTimeString(String dateTimeString) throws ParseException{
		return dateTime.parse(dateTimeString);
	}

	@Override
	public synchronized Date convert(String source) {
		Date date = null;
		try {
			date = dateTime.parse(source);
			return date;
		} catch (ParseException e) {
		}
		try {
			date = onlyDate.parse(source);
			return date;
		} catch (ParseException e) {
		}
		try {
			date = onlyTime.parse(source);
		} catch (ParseException e) {
		}
		return date;
	}
}
