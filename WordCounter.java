import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;


public class WordCounter {

    public int processText(StringBuffer input, String stop) throws InvalidStopwordException, TooSmallText {

        Pattern regex = Pattern.compile("[a-zA-Z_0-9]");
        Matcher regexMatcher = regex.matcher(input);
        boolean found = false;
        int wordcount = 0;

        while(regexMatcher.find()) {
            String cur = regexMatcher.group();
            if(stop.equals(cur)) {
                found = true;
                break;
            }
            wordcount++;
        }

        if(wordcount < 5) {
            throw new TooSmallText("The text is less than 5 words");
        }

        if(stop.equals(null)) {
            return wordcount;
        }

        if(found == false){
            throw new InvalidStopwordException("The Stop Word provided is not in the text");
        }

        return wordcount;
    }

    public StringBuffer processFile(String path) throws EmptyFileException{

        File file = new File(path);

        try{
            String text = Files.readString(Paths.get(path));

            StringBuffer ret = new StringBuffer(text);


        } catch (IOException e) {
            System.out.println("This file cannot be opened, please try again")
        }


        StringBuffer a = null;
    }

    static int main(String args[]){


        return 0;
    }
    
}
