package bam.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static String getDateStr() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
		
		return sdf1.format(new Date());
	}
}