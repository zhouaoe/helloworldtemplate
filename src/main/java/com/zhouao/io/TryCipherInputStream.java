package com.zhouao.io;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TryCipherInputStream {
    public static final int EOF = -1;
    public static final String KEY = "zhouao123456";

    static ByteArrayOutputStream bout = new ByteArrayOutputStream();
    static ByteArrayOutputStream aout = new ByteArrayOutputStream();


    public static void main(String args[]) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidKeyException {
        encrypt();
        decrypt();
    }


    public static void encrypt() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        URL url = new URL("http://www.baidu.com");
        InputStream in = url.openStream();

        Key key = new SecretKeySpec(KEY.getBytes(), 0, KEY.length(), "RC4");
        SecureRandom sr = new SecureRandom();

//加密模式
        Cipher cip = Cipher.getInstance("RC4");
        cip.init(Cipher.ENCRYPT_MODE, key, sr);

        CipherInputStream cipherInputStream = new CipherInputStream(in, cip);
        byte[] buffer = new byte[1024];

        int readByte = cipherInputStream.read(buffer);
        while (EOF != readByte) {
            aout.write(buffer, 0, readByte);
            readByte = cipherInputStream.read(buffer);
        }

        System.out.println("After encryption:" + aout.size() + aout.toString());
    }


    private static void decrypt() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
//刚才的加密数据
        ByteArrayInputStream in = new ByteArrayInputStream(aout.toByteArray());
        Key key = new SecretKeySpec(KEY.getBytes(), 0, KEY.length(), "RC4");
        SecureRandom sr = new SecureRandom();

//解密模式
        Cipher cip = Cipher.getInstance("RC4");
        cip.init(Cipher.DECRYPT_MODE, key, sr);

        CipherInputStream cipherInputStream = new CipherInputStream(in, cip);
        byte[] buffer = new byte[1024];

        int readByte = cipherInputStream.read(buffer);
        while (EOF != readByte) {
            bout.write(buffer, 0, readByte);
            readByte = cipherInputStream.read(buffer);
        }

        System.out.println("After decryption:" + bout.size() + bout.toString());
    }

}
