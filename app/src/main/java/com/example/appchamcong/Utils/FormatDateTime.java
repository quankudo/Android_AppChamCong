package com.example.appchamcong.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateTime {
    public static String formatDateToString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }
}
