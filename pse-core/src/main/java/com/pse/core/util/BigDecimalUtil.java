package com.pse.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class BigDecimalUtil {

    private static final int PRECISION = 4;

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

    public static BigDecimal divide(Number dividend, Number divisor) {
        if (null == dividend) {
            return BigDecimal.ZERO;
        }
        BigDecimal bDividend = new BigDecimal(dividend.toString());
        BigDecimal bDivisor = new BigDecimal(divisor.toString());
        return bDividend.divide(bDivisor, PRECISION, RoundingMode.HALF_UP);
    }

    public static BigDecimal multiply(Number... multiplicands) {
        return multiply(Arrays.asList(multiplicands).stream()
                .filter(m -> null != m)
                .map(m -> m.toString())
                .map(BigDecimal::new)
                .collect(Collectors.toList())
                .toArray(new BigDecimal[]{}));
    }

    public static BigDecimal multiply(BigDecimal... multiplicands) {
        return Arrays.asList(multiplicands).stream()
            .filter(b -> null != b)
            .reduce(BigDecimal.ONE, BigDecimalUtil::directMultiply)
            .setScale(PRECISION, RoundingMode.HALF_UP);
    }

    private static BigDecimal directMultiply(BigDecimal multiplicand1, BigDecimal multiplicand2) {
        return multiplicand1.multiply(multiplicand2);
    }

}
