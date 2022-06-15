package top.hendrixshen.bilibilidanmaku.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Inflater;

public final class Zlib {
    public static byte[] decompress(byte[] data) {
        byte[] output;

        Inflater inflater = new Inflater();
        inflater.reset();
        inflater.setInput(data);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length)) {
            byte[] buf = new byte[1024];
            while (!inflater.finished()) {
                int i = inflater.inflate(buf);
                byteArrayOutputStream.write(buf, 0, i);
            }
            output = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            output = data;
        }

        inflater.end();
        return output;
    }
}