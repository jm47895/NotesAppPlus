package com.jordanmadrigal.notesappplus.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static com.jordanmadrigal.notesappplus.util.DateUtil.GET_MONTH_ERROR;
import static com.jordanmadrigal.notesappplus.util.DateUtil.getMonthFromNumber;
import static com.jordanmadrigal.notesappplus.util.DateUtil.monthNumbers;
import static com.jordanmadrigal.notesappplus.util.DateUtil.months;
import static org.junit.jupiter.api.Assertions.*;

public class DateUtilTest {

    private static final String today = "04-2020";

    @Test
    public void testGetCurrentTimeStamp_returnedTimestamp(){
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                assertEquals(today, DateUtil.getCurrentTimeStamp());
                System.out.println("Timestamp is equal");

            }
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9,10,11})
    public void testGetMonthFromNumber_returnSuccess(int monthNumber){
        assertEquals(months[monthNumber], DateUtil.getMonthFromNumber(monthNumbers[monthNumber]));
        System.out.println(monthNumbers[monthNumber] + " : " + months[monthNumber]);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9,10,11})
    public void testGetMonthFromNumber_returnError(int monthNumber){

        int randomInt = new Random().nextInt(90) + 13;
        assertEquals(getMonthFromNumber(String.valueOf(monthNumber * randomInt)), GET_MONTH_ERROR);
        System.out.println(monthNumbers[monthNumber] + " : " + GET_MONTH_ERROR);
    }
}
