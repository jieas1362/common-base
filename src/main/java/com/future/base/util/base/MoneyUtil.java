package com.future.base.util.base;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyUtil {

    private static final BigDecimal MONEY_BASE = BigDecimal.valueOf(100L);

    /**
     * 将单位为元的金额转换为单位为分
     *
     * @param yuan Y元
     * @return Long F分
     */
    public static Long changeY2F(Double yuan) {
        if (ProChecker.isNull(yuan)) {
            return null;
        }
        return BigDecimal.valueOf(yuan).multiply(MONEY_BASE).longValue();
    }

    /**
     * 将单位为分的金额转换为单位为元
     *
     * @param fen F分
     * @return Double Y元
     */
    public static Double changeF2Y(Long fen) {
        if (ProChecker.isNull(fen)) {
            return null;
        }
        return BigDecimal.valueOf(fen).divide(MONEY_BASE).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 将单位为分的金额转换为单位为元
     *
     * @param fen F分
     * @return Double Y元
     */
    public static Double changeF2Y(Integer fen) {
        if (ProChecker.isNull(fen)) {
            return null;
        }
        return BigDecimal.valueOf(fen).divide(MONEY_BASE).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


    /**
     * 将单位为分的金额转换为单位为元
     *
     * @param fen F分
     * @return Double Y元
     */
    public static Double changeF2Y(Double fen) {
        if (ProChecker.isNull(fen)) {
            return null;
        }
        return BigDecimal.valueOf(fen).divide(MONEY_BASE).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

//    public static void main(String[] args) {
//        System.out.println(changeY2F(5.34633));
//    }
}
