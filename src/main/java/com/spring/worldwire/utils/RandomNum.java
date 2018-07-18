package com.spring.worldwire.utils;

import java.util.Random;

/**
 * @Auther pg
 * @Date create in 21:59 2018/7/18
 */
public class RandomNum {


    public static String getPre3Num() {
        Random random = new Random();
        Integer result = random.nextInt(800) + 100;
        return result.toString();

    }

}
