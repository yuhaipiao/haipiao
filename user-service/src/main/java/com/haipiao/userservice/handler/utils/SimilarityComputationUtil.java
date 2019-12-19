package com.haipiao.userservice.handler.utils;

import com.haipiao.userservice.handler.constants.RecommendedConstant;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author wangjipeng
 */
public class SimilarityComputationUtil {

    public static boolean calculatorResult(int thisSize, int resultSize, Double threshold){
        BigDecimal thisNum = new BigDecimal(thisSize);
        BigDecimal resultNum = new BigDecimal(resultSize);
        BigDecimal divide = resultNum.divide(thisNum, 1, RoundingMode.HALF_UP);
        return divide.compareTo(new BigDecimal(threshold)) >= 0;
    }
}
