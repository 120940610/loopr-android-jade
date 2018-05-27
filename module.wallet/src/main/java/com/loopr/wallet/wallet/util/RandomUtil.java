package com.loopr.wallet.wallet.util;

import com.loopr.wallet.common.utils.LogUtils;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by snow on 2018/5/27.
 */

public class RandomUtil {

    public static Object[] random24(){

        Random random = new Random();
        Object[] values = new Object[24];
        HashSet<Integer> hashSet = new HashSet<Integer>();

        // 生成随机数字并存入HashSet
        while(hashSet.size() < values.length){
            hashSet.add(random.nextInt(24) + 1);
        }

        values = hashSet.toArray();

        return values;
    }
}
