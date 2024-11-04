public class TooSmallText extends Exception{

    private final int wordcount;

    public TooSmallText(int wordcount) {
        this.wordcount = wordcount;
    }

    public String toString() {
        return "TooSmallText: Only found " + wordcount + " words.";
    }
}
