package RJutils;

import java.io.*;
import java.nio.charset.Charset;

public class ReaderRJ {
    private BufferedReader reader;
    private StringBuilder token;

    public ReaderRJ(InputStream in) throws IOException {
        reader = new BufferedReader(new InputStreamReader(in));
    }

    public ReaderRJ(InputStream in, Charset code) throws IOException {
        reader = new BufferedReader(new InputStreamReader(in, code));
    }

    public ReaderRJ(InputStream in, int buffSize, Charset code) throws IOException {
        reader = new BufferedReader(new InputStreamReader(in, code), buffSize);
    }

    public ReaderRJ(String path) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
    }

    public ReaderRJ(String path, Charset code) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), code));
    }

    public ReaderRJ(String path, int buffSize, Charset code) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), code), buffSize);
    }

    public String nextWord() throws IOException {
        int charka;

        token = new StringBuilder();

        while ((charka = reader.read()) != -1) {
            if (Character.isLetter(charka) || Character.getType(charka) == Character.DASH_PUNCTUATION || charka == '\'') {
                token.append((char) charka);
            } else {
                if (!token.isEmpty()) {
                    return token.toString();
                }
            }
        }
        if (!token.isEmpty()) {
            return token.toString();
        } else {
            return null;
        }
    }

    public boolean hasNextInt() throws IOException {
        int charka;
        reader.mark(1);
        while ((charka = reader.read()) != -1) {
            if (Character.isDigit(charka)) {
                reader.reset();
                return true;
            }
        }
        reader.reset();
        return false;
    }

    public int nextInt() throws IOException {
        int charka;

        token = new StringBuilder();

        while ((charka = reader.read()) != -1) {
            if (Character.isDigit(charka)) {
                token.append((char) charka);
            } else {
                if (!token.isEmpty()) {
                    return Integer.parseInt(token.toString());
                }
            }
        }
        return Integer.parseInt(token.toString());
    }

    public void close() throws IOException {
        reader.close();
    }
}
