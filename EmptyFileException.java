import java.io.*;

public class EmptyFileException extends IOException {

    String path;

    public EmptyFileException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String toString(){
        return "EmptyFileException: " + this.path + " was empty";
    }
}
