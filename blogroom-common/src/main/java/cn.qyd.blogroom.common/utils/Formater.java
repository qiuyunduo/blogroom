package cn.qyd.blogroom.common.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Stream;

public class Formater {
	protected static final DecimalFormat RATE_FORMAT = new DecimalFormat("#0.0");
	protected static final DecimalFormat RATE_PERCENT_FORMAT = new DecimalFormat("#0.0%");

	protected static final DecimalFormat PROESS_FORMAT = new DecimalFormat("#0%");

	protected static final DecimalFormat AMOUNT_SPLIT_FORMAT = new DecimalFormat("#,##,##0.00");
	protected static final DecimalFormat AMOUNT_SPLIT_FORMAT_3 = new DecimalFormat("#,##,##0.000");
	protected static final DecimalFormat AMOUNT_SPLIT_FORMAT_6 = new DecimalFormat("#,##,##0.000000");
	protected static final DecimalFormat INT_SPLIT_FORMAT = new DecimalFormat("#,##,##0");

	protected static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("#####0.00");

	protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	protected static final SimpleDateFormat DATE_FORMAT_TO_MONTH = new SimpleDateFormat("yyyy-MM");
	protected static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

	protected static final SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("HH");

	protected static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected static final SimpleDateFormat DATETIME_FORMAT_TWO=new SimpleDateFormat("yyyyMMddHHmmss");
	protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	protected static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
	protected static final DateTimeFormatter DATETIME_FORMAT_TO_MONTH =DateTimeFormatter.ofPattern("yyyy-MM");

	public static String formatDate(LocalDate date){
		if(date == null || "".equals( date )) { return ""; }
		return date.format(DATE_FORMATTER);
	}



	public static String formatDatetime(LocalDateTime dateTime){
		if(dateTime == null || "".equals( dateTime )) { return ""; }
		return dateTime.format(DATE_TIME_FORMATTER);
	}

	public static String formatTime(LocalTime time){
		if(time == null || "".equals( time )) { return ""; }
		return time.format(TIME_FORMATTER);
	}

	public static LocalDateTime parseLocalDateTime(String str){
		if(Strings.isNullOrEmpty(str)) { return null; }
		return LocalDateTime.parse(str, DATE_TIME_FORMATTER);
	}

	public static LocalDate parseLocalDate(String str){
		if(Strings.isNullOrEmpty(str)) { return null; }
		return LocalDate.parse(str, DATE_FORMATTER);
	}

	public static LocalTime parseLocalTime(String str){
		if(Strings.isNullOrEmpty(str)) { return null; }
		return LocalTime.parse(str, TIME_FORMATTER);
	}


	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		return DATE_FORMAT.format(date);
	}

	public static String formatDateToMonth(Date date) {
		if (date == null) {
			return "";
		}
		return DATE_FORMAT_TO_MONTH.format(date);
	}






	public static String formatTime(Date date) {
		if (date == null) {
			return "";
		}
		return TIME_FORMAT.format(date);
	}

	public static String formatDateTime(Date date) {
		if (date == null) {
			return "";
		}
		return DATETIME_FORMAT.format(date);
	}
	public static String formatDateTimeTwo(Date date) {
		if (date == null) {
			return "";
		}
		return DATETIME_FORMAT_TWO.format(date);
	}

	public static Date parseDate(String  date)  {
		return  Optional.ofNullable(date).map(e -> {
			try {
				return DATE_FORMAT.parse(e);
			} catch (ParseException e1) {
				throw new RuntimeException(e1);
			}
		}).orElse(new Date());
	}

	public static Date parseTime(String date) {
		return  Optional.ofNullable(date).map(e -> {
			try {
				return TIME_FORMAT.parse(e);
			} catch (ParseException e1) {
				throw new RuntimeException(e1);
			}
		}).orElse(new Date());
	}

	public static  Date parseDateTime(String date) {
		return  Optional.ofNullable(date).map(e -> {
			try {
				return DATETIME_FORMAT.parse(e);
			} catch (ParseException e1) {
				throw new RuntimeException(e1);
			}
		}).orElse(new Date());
	}


	public static Date parseUnixTime(Integer unixDate){
		Long timestamp = Long.valueOf(unixDate) * 1000;
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(timestamp));
		return parseDateTime(date);

	}

	public static String formatRate(float number) {
		return RATE_PERCENT_FORMAT.format(number);
	}

	public static String formatRate(double number) {
		return RATE_PERCENT_FORMAT.format(number);
	}

	public static String formatRate(BigDecimal number) {
		return RATE_PERCENT_FORMAT.format(number);
	}

	public static String formatRate(float number, boolean includePercent) {
		return includePercent ? RATE_PERCENT_FORMAT.format(number) : RATE_FORMAT.format(number * 100.0F);
	}

	public static String formatRate(double number, boolean includePercent) {
		return includePercent ? RATE_PERCENT_FORMAT.format(number) : RATE_FORMAT.format(number * 100.0D);
	}

	public static String formatRate(BigDecimal number, boolean includePercent) {
		return includePercent ? RATE_PERCENT_FORMAT.format(number)
				: RATE_FORMAT.format(number.multiply(new BigDecimal(100)));
	}

	public static int roundProgress(double number) {
		double v = number * 100.0D;
		if (v <= 0.0D) {
			return 0;
		}
		if (v <= 1.0D) {
			return 1;
		}
		return (int) (Math.floor(number * 100.0D) / 100.0D);
	}

	public static int roundProgress(float number) {
		double v = number * 100.0F;
		if (v <= 0.0D) {
			return 0;
		}
		if (v <= 1.0D) {
			return 1;
		}
		return (int) (Math.floor(number * 100.0F) / 100.0D);
	}

	public static String formatProgress(double number) {
		double v = number * 100.0D;
		if (v <= 0.0D) {
			return "0%";
		}
		if (v <= 1.0D) {
			return "1%";
		}
		return PROESS_FORMAT.format(Math.floor(number * 100.0D) / 100.0D);
	}

	public static String formatProgress(float number) {
		double v = number * 100.0F;
		if (v <= 0.0D) {
			return "0%";
		}
		if (v <= 1.0D) {
			return "1%";
		}
		return PROESS_FORMAT.format(Math.floor(number * 100.0F) / 100.0D);
	}

	public static String formatAmount(int number) {
		return AMOUNT_SPLIT_FORMAT.format(number);
	}

	public static String formatInt(int number) {
		return INT_SPLIT_FORMAT.format(number);
	}

	public static String formatAmount(long number) {
		return AMOUNT_SPLIT_FORMAT.format(number);
	}

	public static String formatAmount(float number) {
		return AMOUNT_SPLIT_FORMAT.format(number);
	}

	public static String formatAmount(double number) {
		return AMOUNT_SPLIT_FORMAT.format(number);
	}

	public static String formatAmount(BigDecimal number) {
		if (number == null) {
			return "";
		}
		return AMOUNT_SPLIT_FORMAT.format(number);
	}

	public static String formatAmount_3(BigDecimal number) {
		if (number == null) {
			return "";
		}
		return AMOUNT_SPLIT_FORMAT_3.format(number);
	}

	public static String formatAmount_6(BigDecimal number) {
		if (number == null) {
			return "";
		}
		return AMOUNT_SPLIT_FORMAT_6.format(number);
	}

	public static String formatAmount(BigInteger number) {
		if (number == null) {
			return "";
		}
		return AMOUNT_SPLIT_FORMAT.format(number);
	}

	public static String formatAmount(int number, boolean split) {
		return split ? AMOUNT_SPLIT_FORMAT.format(number) : AMOUNT_FORMAT.format(number);
	}

	public static String formatAmount(long number, boolean split) {
		return split ? AMOUNT_SPLIT_FORMAT.format(number) : AMOUNT_FORMAT.format(number);
	}

	public static String formatAmount(float number, boolean split) {
		return split ? AMOUNT_SPLIT_FORMAT.format(number) : AMOUNT_FORMAT.format(number);
	}

	public static String formatAmount(double number, boolean split) {
		return split ? AMOUNT_SPLIT_FORMAT.format(number) : AMOUNT_FORMAT.format(number);
	}

	public static String formatAmount(BigDecimal number, boolean split) {
		if (number == null) {
			return "";
		}
		return split ? AMOUNT_SPLIT_FORMAT.format(number) : AMOUNT_FORMAT.format(number);
	}

	public static String formatAmount(BigInteger number, boolean split) {
		if (number == null) {
			return "";
		}
		return split ? AMOUNT_SPLIT_FORMAT.format(number) : AMOUNT_FORMAT.format(number);
	}

	public static String formatAmount_(BigDecimal number, int count) {
		if (number == null) {
			return "";
		}
		if (count <= 0) {
			count = 1;
		}
		StringBuffer s = new StringBuffer("#,##,##0.");
		for (int i = 1; i <= count; i++) {
			s.append("0");
		}
		DecimalFormat AMOUNT_SPLIT_FORMAT_ = new DecimalFormat(s.toString());
		return AMOUNT_SPLIT_FORMAT_.format(number);
	}
	
	public static String formatAmount_Math(BigDecimal number1, BigDecimal number2) {
		if (number1 == null) {
			return "";
		}
		if (number2 == null) {
			return "";
		}
		BigDecimal number=number1.multiply(number2).setScale(2, BigDecimal.ROUND_HALF_UP);
		return AMOUNT_SPLIT_FORMAT.format(number);
	}


	public static String formatHour(Date date) {
		if (date == null) {
			return "";
		}
		return HOUR_FORMAT.format(date);
	}


	// 获得某天最大时间
	public static Date getEndOfDay(LocalDate localDate) {
		LocalDateTime endDay =LocalDateTime.of(localDate,LocalTime.MAX);
		return Date.from(endDay.atZone(ZoneId.systemDefault()).toInstant());
	}

	// 获得某天最大时间
	public static Date getEndOfDay(String day) {
		if(StringUtils.isEmpty(day)){
			return new Date();
		}
		LocalDate localDate=parseLocalDate(day);
		return getEndOfDay(localDate);
	}

	// 获得某天最小时间
	public static Date getStartOfDay(LocalDate localDate) {
		LocalDateTime startOfDay =LocalDateTime.of(localDate,LocalTime.MIN);
		return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
	}


	// 获得某天最小时间
	public static Date getStartOfDay(String day) {
		if(StringUtils.isEmpty(day)){
			return new Date();
		}
		LocalDate localDate=parseLocalDate(day);
		return getStartOfDay(localDate);
	}

	public static Date parseLocalDateTime(LocalDateTime localDateTime){
		if(localDateTime == null || "".equals( localDateTime )) { return null; }
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}


	/**
	 * 获取指定时间的指定天数后的时间
	 * @param localDateTime 时间
	 * @param day 天数，往前负数、往后正数
	 * @return
	 */
	public static LocalDateTime getDayTime(LocalDateTime localDateTime,int day){
		if(localDateTime == null || "".equals( localDateTime )) { return null; }
		localDateTime=localDateTime.plusDays(day);
		return localDateTime;
	}

	/**
	 * 获取指定时间的指定天数后的日期
	 * @param localDate 时间
	 * @param day 天数，往前负数、往后正数
	 * @return
	 */
	public static LocalDate getDayDate(LocalDate localDate,int day){
		if(localDate == null || "".equals( localDate )) { return null; }
		localDate=localDate.plusDays(day);
		return localDate;
	}

	/**
	 * 获取俩个时间之间的时间
	 * @param startTime
	 * @param endTime
	 * @return key:年月日 value:集合 如:{1,2,3}
	 */
	public static Map<String,List<String>> getTwoDateTime(LocalDateTime startTime,LocalDateTime endTime){
		if(startTime==null||endTime==null){
			return Maps.newHashMap();
		}
		List<LocalDateTime> list= Lists.newArrayList();
		long distance = ChronoUnit.HOURS.between(startTime, endTime);
		Stream.iterate(startTime, d -> {
			return d.plusHours(1);
		}).limit(distance + 1).forEach(f -> {
			list.add(f);
		});
		Map<String,List<String>> map= Maps.newHashMap();
		List<String> hourList=Lists.newArrayList();
		for(LocalDateTime date:list){
			String localDate=formatDate(date.toLocalDate());
			Integer hour=date.getHour();
			if(map.get(localDate)==null){
				hourList=Lists.newArrayList();
				hourList.add(hour.toString());
				map.put(localDate,hourList);
			}else{
				hourList.add(hour.toString());
			}
		}
		return map;
	}


	/**
	 * 获取俩个时间之间的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getTwoDateDay(LocalDate startDate,LocalDate endDate){
		if(startDate==null||endDate==null){
			return Lists.newArrayList();
		}
		List<LocalDate> list= Lists.newArrayList();
		long distance = ChronoUnit.DAYS.between(startDate, endDate);
		Stream.iterate(startDate, d -> {
			return d.plusDays(1);
		}).limit(distance + 1).forEach(f -> {
			list.add(f);
		});
		List<String> dayList=Lists.newArrayList();
		for(LocalDate date:list){
			String localDate=formatDate(date);
			dayList.add(localDate);
		}
		return dayList;
	}



	/**
	 * 求俩个时间之间的天数
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return
	 */
	public static Integer getTwoDateDayNum(String startDay,String endDay){
		if(StringUtils.isEmpty(startDay)){
			return 0;
		}
		if(StringUtils.isEmpty(endDay)){
			return 0;
		}
		LocalDate startDate=parseLocalDate(startDay);
		LocalDate endDate=parseLocalDate(endDay);
		Period period=Period.between(startDate,endDate);
		return period.getDays();
	}

	/**
	 * 获取某月的第一天
	 * @param toMonth
	 * @return
	 */
	public static LocalDate getFirstDayOfMonth(String toMonth){
		LocalDate inputDate = LocalDate.now();
		if(StringUtils.isNotEmpty(toMonth)){
			String year=toMonth.substring(0,4);
			String month=toMonth.substring(5,7);
			inputDate=LocalDate.of(Integer.valueOf(year),Integer.valueOf(month),1);
		}
		TemporalAdjuster FIRST_OF_DAY = TemporalAdjusters.firstDayOfMonth();
		return inputDate.with(FIRST_OF_DAY);
	}


	/**
	 * 获取某月的最后一天
	 * @param toMonth
	 * @return
	 */
	public static LocalDate getLastDayOfMonth(String toMonth){
		LocalDate inputDate = LocalDate.now();
		if(StringUtils.isNotEmpty(toMonth)){
			String year=toMonth.substring(0,4);
			String month=toMonth.substring(5,7);
			inputDate=LocalDate.of(Integer.valueOf(year),Integer.valueOf(month),1);
		}
		TemporalAdjuster LAST_OF_DAY = TemporalAdjusters.lastDayOfMonth();
		return inputDate.with(LAST_OF_DAY);
	}



	/**
	 * 获取某月的第一天
	 * @param toMonth
	 * @return
	 */
	public static String formatFirstDayOfMonth(String toMonth){
		LocalDate inputDate = LocalDate.now();
		if(StringUtils.isNotEmpty(toMonth)){
			String year=toMonth.substring(0,4);
			String month=toMonth.substring(5,7);
			inputDate=LocalDate.of(Integer.valueOf(year),Integer.valueOf(month),1);
		}
		TemporalAdjuster FIRST_OF_DAY = TemporalAdjusters.firstDayOfMonth();
		LocalDate localDate=inputDate.with(FIRST_OF_DAY);
		return localDate.format(DATE_FORMATTER);
	}


	/**
	 * 获取某月的最后一天
	 * @param toMonth
	 * @return
	 */
	public static String formatLastDayOfMonth(String toMonth){
		LocalDate inputDate = LocalDate.now();
		if(StringUtils.isNotEmpty(toMonth)){
			String year=toMonth.substring(0,4);
			String month=toMonth.substring(5,7);
			inputDate=LocalDate.of(Integer.valueOf(year),Integer.valueOf(month),1);
		}
		TemporalAdjuster LAST_OF_DAY = TemporalAdjusters.lastDayOfMonth();
		LocalDate localDate=inputDate.with(LAST_OF_DAY);
		return localDate.format(DATE_FORMATTER);
	}


	/**
	 * 获取一年的第一天
	 * @param toYear
	 * @return
	 */
	public static String formatFirstDayOfYear(Integer toYear){
		LocalDate inputDate = LocalDate.now();
		if(toYear!=null){
			inputDate=LocalDate.of(toYear,1,1);
		}
		TemporalAdjuster FIRST_OF_DAY = TemporalAdjusters.firstDayOfYear();
		LocalDate localDate=inputDate.with(FIRST_OF_DAY);
		return localDate.format(DATE_FORMATTER);
	}


	/**
	 * 获取一年的最后一天
	 * @param toYear
	 * @return
	 */
	public static String formatLastDayOfYear(Integer toYear){
		LocalDate inputDate = LocalDate.now();
		if(toYear!=null){
			inputDate=LocalDate.of(toYear,12,31);
		}
		TemporalAdjuster LAST_OF_DAY = TemporalAdjusters.lastDayOfYear();
		LocalDate localDate=inputDate.with(LAST_OF_DAY);
		return localDate.format(DATE_FORMATTER);
	}

	/**
	 * 计算传入时间和当前时间相差毫秒数
	 * @param startDate
	 * @return
	 */
	public static int timeDifferenceSecond(Date startDate) {
		long a = System.currentTimeMillis();
		long b = startDate.getTime();
		return (int)(a - b);
	}



	/**
	 * 计算当前时间和传入时间的相差毫秒数
	 * @param startDate
	 * @return
	 */
	public static long currentTimeDifferenceSecond(Date startDate) {
		long a = System.currentTimeMillis();
		long b = startDate.getTime();
		return (b - a);
	}




	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 *
	 * @param nowTime 当前时间
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 * @author lyc
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime.getTime() == startTime.getTime()
				|| nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取某月的第一天 的凌晨
	 * @param toMonth
	 * @return
	 */
	public static LocalDateTime getToMonthStartTime(String toMonth) {
		LocalDate localDate=getFirstDayOfMonth(toMonth);
		LocalDateTime localDateTime=LocalDateTime.of(localDate,LocalTime.of(00,00,00));
		return localDateTime;
	}


	/**
	 * 获取某月最后一天的最后时间
	 * @param toMonth
	 * @return
	 */
	public static LocalDateTime getToMonthEndTime(String toMonth) {
		LocalDate localDate=getLastDayOfMonth(toMonth);
		LocalDateTime localDateTime=LocalDateTime.of(localDate,LocalTime.of(23,59,59));
		return localDateTime;
	}

	/**
	 * 获取周一的开始时间
	 * @return
	 */
	public static LocalDateTime getToWeekStartTime(){
		//获取当前时间
		LocalDate local = LocalDate.now();
		//获取今天是周几
		DayOfWeek dayOfWeek = local.getDayOfWeek();
		//算出上周一
		LocalDate lastMonday = local.minusDays(dayOfWeek.getValue()-1);
		LocalDateTime localDateTime=LocalDateTime.of(lastMonday,LocalTime.of(00,00,00));
		return localDateTime;
	}




}