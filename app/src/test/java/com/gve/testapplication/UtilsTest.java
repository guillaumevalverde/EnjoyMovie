package com.gve.testapplication;

import com.gve.testapplication.feature.Utils;
import com.gve.testapplication.test_common.BaseTest;

import org.junit.Test;

import java.text.ParseException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by gve on 19/12/2017.
 */

public class UtilsTest  extends BaseTest {

    @Test
    public void formatDateTest() throws ParseException {
        String date = "1999-10-12";
        String formattedDate = "12/10/1999";

        String formattedDateResult = Utils.getDate(date);
        assertEquals(formattedDate, formattedDateResult);
    }

    @Test
    public void formatNumberTest() {
        int number  = 1200000000;
        String numberFormatted = "1,200,000,000";
        String formattedNumberResult = Utils.getNumberInCurrency(number);
        assertEquals(numberFormatted, formattedNumberResult);
    }
}
