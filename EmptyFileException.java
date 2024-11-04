import java.io.*;

public class EmptyFileException extends IOException {

    private final String path;

    public EmptyFileException(String path) {
        this.path = path;
    }

    public String toString(){
        return "EmptyFileException: " + this.path + " was empty";
    }
}
