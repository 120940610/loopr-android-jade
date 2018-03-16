package com.loopr.wallet.utils.tools;

import android.content.pm.Signature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by snow on 2018/3/6.
 */

@SuppressWarnings("StringEqualsEmptyString")
public class MD5 {
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f' };


    public static String getMD5String(byte[] source) {
        String s = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();

            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {

                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];

                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Get the string MD5 encoding
     *
     * @param source
     *
     * @return
     */
    public static byte[] getMD5(String source) {
        if (source == null || source.isEmpty())
            return null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        try {
            if (null != md) {
                md.update(source.getBytes("UTF-8"));
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        if (md == null) {
            return null;
        }
        byte[] temp = md.digest();
        return temp;
    }

    public static String getMD5String(String source) {
        return bufferToHex(getMD5(source));
    }

    /**
     * Generate the md5 checksum for the file
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return getFileMD5String(fis);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static byte[] getFileMD5(File file) throws IOException {
        InputStream fis = null;
        MessageDigest messagedigest;
        try {
            fis = new FileInputStream(file);
            messagedigest = MessageDigest.getInstance("MD5");

            byte[] buffer = new byte[4096];
            int numRead;
            while ((numRead = fis.read(buffer)) > 0) {
                messagedigest.update(buffer, 0, numRead);
            }
            fis.close();

            return messagedigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static String getFileMD5String(InputStream fis) throws IOException {
        MessageDigest messagedigest;
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] buffer = new byte[4096];
        int numRead;
        while ((numRead = fis.read(buffer)) > 0) {
            messagedigest.update(buffer, 0, numRead);
        }
        fis.close();
        return bufferToHex(messagedigest.digest());
    }

    public static String getSHA1Hash(String fileName) {
        try {
            String hashType = "SHA-1";
            InputStream fis;
            fis = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            MessageDigest sha1 = MessageDigest.getInstance(hashType);
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                sha1.update(buffer, 0, numRead);
            }
            fis.close();
            return bufferToHex(sha1.digest());
        } catch (NoSuchAlgorithmException e) {

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return null;
    }

    public static String signatureMD5(Signature[] signatures) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            if (signatures != null) {
                for (Signature s : signatures)
                    digest.update(s.toByteArray());
            }
            return bufferToHex(digest.digest());
        } catch (Exception e) {
            return "";
        }
    }

    public static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }


    public static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}