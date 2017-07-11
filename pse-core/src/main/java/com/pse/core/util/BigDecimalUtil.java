package com.pse.core.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

    private BigDecimalUtil() {
        //
    }

    public static BigDecimal tryParse(String str) {
        if (null == str || str.length() == 0) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

}
