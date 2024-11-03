public class InvalidStopwordException extends Exception{

    String stop;
    public InvalidStopwordException(String message, String stop) {
        super(message);
        this.stop = stop;
    }

    public String toString() {

        return "InvalidStopwordException: Couldn't find stopword: " + stop;
    }
}
