package org.troublert.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DateUtil {
	private static final Logger log = LogManager.getLogManager().getLogger(DateUtil.class.getName());
	
	//TODO couldn't find the actual date format in our CSV so I improvized :P
	private static final String CSV_DATE_FORMAT = "d-MMM-yyyy,HH:mm:ss aaa";

	public static Date parseDateFromCsv(String dateString){
		try {
			DateFormat formatter = new SimpleDateFormat(CSV_DATE_FORMAT);
			return new Date(formatter.parse(dateString).getTime());
		} catch (ParseException e) {
			log.warning(e.getMessage());
			return null;
		}
	}
}
