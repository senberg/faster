package senberg.faster;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 * Optimized convenience methods for working with files.
 */
public class FileReader {
    public static void readIntArray(String filename, int[] output) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            IntBuffer intBuffer = mappedByteBuffer.asIntBuffer();
            intBuffer.get(output);
        }
    }

    public static void readLongArray(String filename, long[] output) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            LongBuffer intBuffer = mappedByteBuffer.asLongBuffer();
            intBuffer.get(output);
        }
    }

    public static void readFloatArray(String filename, float[] output) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            FloatBuffer floatBuffer = mappedByteBuffer.asFloatBuffer();
            floatBuffer.get(output);
        }
    }

    public static void readDoubleArray(String filename, double[] output) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            DoubleBuffer doubleBuffer = mappedByteBuffer.asDoubleBuffer();
            doubleBuffer.get(output);
        }
    }

    public static void readByteArray(String filename, byte[] output) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            mappedByteBuffer.get(output);
        }
    }

    public static String readString1(String filename) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            return mappedByteBuffer.asCharBuffer().toString();
        }
    }

    public static String readString3(String filename) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            return StandardCharsets.UTF_8.decode(mappedByteBuffer).toString();
        }
    }

    public static String readString2(String filename) throws IOException {
        Path path = Paths.get(filename);
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ))) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            return mappedByteBuffer.asCharBuffer().toString();
        }
    }

    public static String readString4(String filename) throws IOException {
        Path path = Paths.get(filename);
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(path, EnumSet.of(StandardOpenOption.READ))) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            return StandardCharsets.UTF_8.decode(mappedByteBuffer).toString();
        }
    }

    public static String readString5(String filename) throws IOException {
        return Files.readString(Paths.get(filename));
    }
}
