package com.zhouao.io;

import org.apache.commons.io.input.TeeInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TryTeeInputStream {

    public static final int EOF = -1;

    public static void main(String[] args) throws IOException {

        URL url = new URL("http://www.baidu.com");
        InputStream in = url.openStream();

        ByteArrayOutputStream aout = new ByteArrayOutputStream();

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        TeeInputStream teeInputStream = new TeeInputStream(in, bout, true);

        byte[] buffer = new byte[1024];

        int readByte = teeInputStream.read(buffer);
        while (EOF != readByte) {
            aout.write(buffer, 0, readByte);
            readByte = teeInputStream.read(buffer);
        }

        System.out.println("aout:" + aout.size() + aout.toString());
        System.out.println("bout:" + bout.size() + bout.toString());
    }
}
