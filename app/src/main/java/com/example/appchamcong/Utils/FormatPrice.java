package com.example.appchamcong.Utils;

import java.text.DecimalFormat;

public class FormatPrice {

    public static String formatNumber(double number) {
        // Định dạng số với dấu phân cách ngàn
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        // Trả về số đã định dạng
        return decimalFormat.format(number);
    }


}
