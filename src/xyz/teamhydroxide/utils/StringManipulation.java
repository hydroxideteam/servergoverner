package xyz.teamhydroxide.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringManipulation {
	public static String buildFromArray(String[] args, int startpos) {
		
		StringBuilder sb = new StringBuilder();
		for (int i = startpos; i < args.length; i++) {
			sb.append(args[i]).append(" ");
		}
		return sb.toString().trim();
	}
	
	public static String[] makeArray(String str) {
		
		String[] words = str.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^\\w]", "");
		}
		return words;
	}
	
	public static boolean isInt(String str) {
		try {
	        Integer.parseInt(str);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
