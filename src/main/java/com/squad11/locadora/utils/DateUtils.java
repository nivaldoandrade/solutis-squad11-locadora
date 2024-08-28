package com.squad11.locadora.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate formatStringToDate(String date) {
        return LocalDate.parse(date, DTF);
    }

    public static String formatDateToString(LocalDate date) {
        return date.format(DTF);
    }
}
