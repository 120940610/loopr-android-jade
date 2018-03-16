package com.loopr.wallet.utils.tools;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by snow on 2018/3/6.
 */

public class AES {

    public static String decrypt(String data, String appSecret) {
        String pk = MD5.getMD5String(appSecret).substring(0, 16);
        SecretKeySpec key = new SecretKeySpec(pk.getBytes(), "AES");
        String decryptContent = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(pk.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] decryptBytes = cipher.doFinal(Base64.decode(data, Base64.NO_WRAP));
            decryptContent = new String(decryptBytes, "UTF-8");
        } catch (Exception e) {
        }
        return decryptContent;
    }

    public static String encrypt(String data, String appSecret) {
        String pk = MD5.getMD5String(appSecret).substring(0, 16);
        String encryptContent = null;
        try {
            SecretKeySpec key = new SecretKeySpec(pk.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(pk.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] encryptBytes = cipher.doFinal(data.getBytes("UTF-8"));
            encryptContent = Base64.encodeToString(encryptBytes, Base64.NO_WRAP);
        } catch (Exception e) {
        }
        return encryptContent;
    }
}
