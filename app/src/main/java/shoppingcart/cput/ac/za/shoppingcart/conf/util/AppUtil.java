package shoppingcart.cput.ac.za.shoppingcart.conf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-04
 */
public class AppUtil {

    public static Date date(String date)
    {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        Date value = null;
        try
        {
            value = format.parse(date);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return value;
    }
}
