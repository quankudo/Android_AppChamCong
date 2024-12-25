package com.example.appchamcong.Utils;

import java.text.ParseException;
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

    public static Date convertStringToDate(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateString); // Trả về đối tượng Date
        } catch (ParseException e) {
            System.out.println("Lỗi định dạng ngày: " + e.getMessage());
            return null; // Trả về null nếu có lỗi
        }
    }
}
