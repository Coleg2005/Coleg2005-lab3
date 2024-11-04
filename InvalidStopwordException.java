public class InvalidStopwordException extends Exception{

    private final String stop;
    public InvalidStopwordException(String stop) {
        this.stop = stop;
    }

    public String toString() {

        return "InvalidStopwordException: Couldn't find stopword: " + stop;
    }
}
