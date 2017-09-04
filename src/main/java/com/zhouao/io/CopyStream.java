package com.zhouao.io;

import org.apache.commons.io.input.AutoCloseInputStream;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.io.input.TeeInputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.*;

/**
 * 1.	一个inputStream中的数据要同时发到两个输出流中
 * 2.	要方便的获取实际读取的总字节数
 * 3.	要计算原始数据MD5
 * 4.	原始数据要加密输出
 */
public class CopyStream {
    public static final int EOF = -1;
    public static final String KEY = "zhouao123456";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        URL url = new URL("http://www.baidu.com");
        InputStream in = url.openStream();
        ByteArrayOutputStream aout = new ByteArrayOutputStream();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        //计数流
        CountingInputStream countingInputStream = new CountingInputStream(in);
        //摘要流
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        DigestInputStream digestInputStream = new DigestInputStream(countingInputStream, messageDigest);
        //加密流
        Key key = new SecretKeySpec(KEY.getBytes(), 0, KEY.length(), "RC4");
        SecureRandom sr = new SecureRandom();
        Cipher cip = Cipher.getInstance("RC4");//加密模式
        cip.init(Cipher.ENCRYPT_MODE, key, sr);
        CipherInputStream cipherInputStream = new CipherInputStream(digestInputStream, cip);
        //tee流
        TeeInputStream teeInputStream = new TeeInputStream(cipherInputStream, bout, true);
        //组合
        InputStream copyIn = new AutoCloseInputStream(teeInputStream);

        byte[] buffer = new byte[1024];

        int readByte = copyIn.read(buffer);
        while (EOF != readByte) {
            aout.write(buffer, 0, readByte);
            readByte = teeInputStream.read(buffer);
        }

        String md5 = new BigInteger(1, digestInputStream.getMessageDigest().digest()).toString(16);
        // 为显示方便，最后把所有unoicode字符的高位都设置为16.改方法已经过时，这里只是为了显示方便，项目中不要使用。
        System.out.println("count:" + countingInputStream.getByteCount() + " md5:" + md5);
        System.out.println("aout:" + aout.size() + aout.toString(16));
        System.out.println("bout:" + bout.size() + bout.toString(16));
    }
}
