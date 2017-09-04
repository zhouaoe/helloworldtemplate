package com.zhouao.io;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TryDigestInputStream {

    public static final int EOF = -1;

    public static void main(String args[]) throws IOException, NoSuchAlgorithmException {

//        URL url = new URL("http://www.baidu.com");
//        InputStream in = url.openStream();
        FileInputStream in = new FileInputStream("C:\\Users\\zhouao\\.viminfo");

        ByteArrayOutputStream aout = new ByteArrayOutputStream();

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        DigestInputStream digestInputStream = new DigestInputStream(in, messageDigest);
        byte[] buffer = new byte[1024];

        int readByte = digestInputStream.read(buffer);
        while (EOF != readByte) {
            aout.write(buffer, 0, readByte);
            readByte = digestInputStream.read(buffer);
        }

        byte[] digest = digestInputStream.getMessageDigest().digest();
        BigInteger bigInt = new BigInteger(1, digest);

        System.out.println("aout:" + aout.size() + aout.toString());
        System.out.println("digestInputStream MD5 digest:" + digestInputStream.getMessageDigest().digest());
        System.out.println("digestInputStream MD5 String :" + bigInt.toString(16));
    }
}
