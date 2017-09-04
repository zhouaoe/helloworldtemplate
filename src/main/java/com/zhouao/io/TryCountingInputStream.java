package com.zhouao.io;


import org.apache.commons.io.input.CountingInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TryCountingInputStream {
    public static final int EOF = -1;

    public static void main(String args[]) throws IOException {

        URL url = new URL("http://www.baidu.com");
        InputStream in = url.openStream();
        ByteArrayOutputStream aout = new ByteArrayOutputStream();
        CountingInputStream countingInputStream = new CountingInputStream(in);
        byte[] buffer = new byte[1024];

        int readByte = countingInputStream.read(buffer);
        while (EOF != readByte) {
            aout.write(buffer, 0, readByte);
            readByte = countingInputStream.read(buffer);
        }

        System.out.println("aout:" + aout.size() + aout.toString());
        System.out.println("countingInputStream (int)getCount :" + countingInputStream.getCount());
        System.out.println("countingInputStream (long)getByteCount: " + countingInputStream.getCount());
    }
}
