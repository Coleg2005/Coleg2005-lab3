public class TooSmallText extends Exception{

    private final int wordcount;

    public TooSmallText(String message, int wordcount) {
        super(message);
        this.wordcount = wordcount;
    }

    public String toString() {

        return "TooSmallText: Only found " + wordcount + " words.";
    }
}
