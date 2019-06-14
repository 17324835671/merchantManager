package com.sparksys.common.utils;

public class PriceUtil {

    public static String formatPrice(String price) {
        String result = price;
        if (price.endsWith(".00")) {
            result = price.split("\\.00")[0];
        } else if (price.endsWith(".0")) {
            result = price.split("\\.0")[0];
        }
        return result;
    }
}