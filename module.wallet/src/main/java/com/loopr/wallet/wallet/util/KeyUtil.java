package com.loopr.wallet.wallet.util;

import android.text.TextUtils;

/**
 * Created by snow on 2018/3/30.
 */

public class KeyUtil {

    public static String passwd=null;

    /**
     * passwd strenth
     * @param password
     * @return
     */
    public static int checkPassword(String password) {
        String regexZ = "\\d*";
        String regexS = "[a-zA-Z]+";
        String regexT = "\\W+$";
        String regexZT = "\\D*";
        String regexST = "[\\d\\W]*";
        String regexZS = "\\w*";
        String regexZST = "[\\w\\W]*";

        if(TextUtils.isEmpty(password)){
            return 0;
        }

        if (password.matches(regexZ) || password.matches(regexS) ||password.matches(regexT)) {
            return 1;
        }

        if (password.matches(regexZT) || password.matches(regexST) || password.matches(regexZS)) {
            return 2;
        }
        if (password.matches(regexZST)) {
            return 3;
        }
        return 0;

    }
}
