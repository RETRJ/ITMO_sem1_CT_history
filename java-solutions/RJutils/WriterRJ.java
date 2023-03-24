package RJutils;

import java.io.*;
import java.nio.charset.Charset;

public class WriterRJ implements AutoCloseable {
    private BufferedWriter writer;

    public WriterRJ(String path) throws IOException {
        writer = new BufferedWriter(new FileWriter(path));
    }

    public WriterRJ(String path, Charset code) throws IOException {
        writer = new BufferedWriter(new FileWriter(path, code));
    }

    public WriterRJ(String path, int buffSize, Charset code) throws IOException {
        writer = new BufferedWriter(new FileWriter(path, code), buffSize);
    }

    public void writeln(String s) throws IOException {
        if (s != null)
            writer.write(s);
        writer.newLine();
    }

    public void write(String s) throws IOException {
        writer.write(s);
    }

    public void write(char c) throws IOException {
        writer.write(c);
    }

    public void close() throws IOException {
        writer.close();
    }

}
