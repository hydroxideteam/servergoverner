package xyz.teamhydroxide.utils;

import java.util.ArrayList;
import java.util.List;

// Thanks Chinwe


/**
* @author Chinwe
*/
public class TimeParser
{
 
    /**
    * Parse a string input into milliseconds, using w(eeks), d(ays), h(ours), m(inutes) and s(econds)
    * For example: 4d8m2s -> 4 days, 8 minutes and 2 seconds
    *
    * @param string String to convert to milliseconds
    * @return Milliseconds
    */
    public static long parseString (String string)
    {
        List<String> list = new ArrayList<String>();
 
        String c;
        int goBack = 0;
        for (int i = 0; i < string.length(); i++)
        {
            c = String.valueOf(string.charAt(i));
            if (c.matches("[a-zA-Z]"))
            {
                list.add(string.substring(goBack, i + 1));
                goBack = i + 1;
 
            }
        }
        // Cleanse
        long amount;
        long total = 0;
        char ch;
        for (String st : list)
        {
            ch = st.charAt(st.length() - 1);
            if (st.length() != 1 && String.valueOf(ch).matches("[M,w,d,h,m,s]"))
            {
                // Total milliseconds
                amount = Math.abs(Integer.parseInt(st.substring(0, st.length() - 1)));
                switch (ch)
                {
                    case 's':
                        total += (amount * 1000);
                        break;
                    case 'm':
                        total += (amount * 1000 * 60);
                        break;
                    case 'h':
                        total += (amount * 1000 * 3600);
                        break;
                    case 'd':
                        total += (amount * 1000 * 3600 * 24);
                        break;
                    case 'w':
                        total += (amount * 1000 * 3600 * 24 * 7);
                        break;
                }
 
            }
        }
 
        if (total == 0) return -1;
 
        return total;
    }
 
    /**
    * @param milliseconds Milliseconds to convert to words
    * @param abbreviate  For example, if true, 293000 -> "10m-53s", otherwise "10 minutes and 53 seconds"
    * @return Time in words
    */
    public static String parseLong (long milliseconds, boolean abbreviate)
    {
        //        String[] units = new String[5];
        List<String> units = new ArrayList<String>();
        long amount;
 
        amount = milliseconds / (7 * 24 * 60 * 60 * 1000);
        units.add(amount + "w");
 
        amount = milliseconds / (24 * 60 * 60 * 1000) % 7;
        units.add(amount + "d");
 
        amount = milliseconds / (60 * 60 * 1000) % 24;
        units.add(amount + "h");
 
        amount = milliseconds / (60 * 1000) % 60;
        units.add(amount + "m");
 
        amount = milliseconds / 1000 % 60;
        units.add(amount + "s");
 
 
        // Sort into order
        String[] array = new String[5];
        char end;
        for (String s : units)
        {
            end = s.charAt(s.length() - 1);
            switch (end)
            {
                case 'w':
                    array[0] = s;
                case 'd':
                    array[1] = s;
                case 'h':
                    array[2] = s;
                case 'm':
                    array[3] = s;
                case 's':
                    array[4] = s;
            }
        }
 
        units.clear();
        for (String s : array)
            if (!s.startsWith("0")) units.add(s);
 
 
        // Append
        StringBuilder sb = new StringBuilder();
        String word, count, and;
        char c;
        for (String s : units)
        {
            if (!abbreviate)
            {
                c = s.charAt(s.length() - 1);
                count = s.substring(0, s.length() - 1);
                switch (c)
                {
                    case 'w':
                        word = "week" + (count.equals("1") ? "" : "s");
                        break;
                    case 'd':
                        word = "day" + (count.equals("1") ? "" : "s");
                        break;
                    case 'h':
                        word = "hour" + (count.equals("1") ? "" : "s");
                        break;
                    case 'm':
                        word = "minute" + (count.equals("1") ? "" : "s");
                        break;
                    default:
                        word = "second" + (count.equals("1") ? "" : "s");
                        break;
                }
 
                and = s.equals(units.get(units.size() - 1)) ? "" : s.equals(units.get(units.size() - 2)) ? " and " : ", ";
                sb.append(count + " " + word + and);
            } else
            {
                sb.append(s);
                if (!s.equals(units.get(units.size() - 1)))
                    sb.append("-");
            }
 
 
        }
        return sb.toString().trim().length() == 0 ? null : sb.toString().trim();
 
    }
 
 
}