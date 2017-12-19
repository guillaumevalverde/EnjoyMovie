package com.gve.testapplication.feature;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by gve on 19/12/2017.
 */

public class Utils {

    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private static DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    public static String getDate(String dateString) {
        try {
            return format2.format(format.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString;
        }
    }

    public static String getNumberInCurrency(int num) {
        NumberFormat formatter = NumberFormat.getInstance();
        return formatter.format(num);
    }
}
