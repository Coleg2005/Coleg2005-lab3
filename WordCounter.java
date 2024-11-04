import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;
import java.util.*;


public class WordCounter {

    public static int processText(StringBuffer input, String stop) throws InvalidStopwordException, TooSmallText {

        // Sets the pattern to be pasred
        Pattern regex = Pattern.compile("[a-zA-Z_0-9]+");
        // matches the input string to the pattern above
        Matcher regexMatcher = regex.matcher(input);
        // has the stopword been found
        boolean found = false;
        // wordcount
        int wordcount = 0;

        // While there is another word
        while(regexMatcher.find()) {
            // increments wordcount
            wordcount++;
            // Sets cur to the current word in the input
            String cur = regexMatcher.group();
            // if cur is the stopword, break
            if(wordcount >= 5 && cur.equals(stop)){
                // sets found to true
                found = true;
                // breaks
                break;
            }
        }

        // if the wordcount is less than five, throws exception
        if(wordcount < 5) {
            throw new TooSmallText(wordcount);
        }

        // if stop is null, returns wordcount
        if(stop == null) {
            return wordcount;
        }
        
        // if stop was never found, throws exception
        if(found == false){
            throw new InvalidStopwordException(stop);
        }

        // returns wordcount
        return wordcount;
    }

    public static StringBuffer processFile(String path) throws EmptyFileException{

        // initializes result
        StringBuffer result = null;
        // tries this code and looks for exceptions
        try{
            // text is the text of the file 
            String text = Files.readString(Paths.get(path));
            // if there is no text in the file, empty file exception is thrown
            if(text.isEmpty()) {
                throw new EmptyFileException(path);
            }
            // if not empty, result is the text in the file
            result = new StringBuffer(text);

        } catch (IOException e) {
            //if the exception is an empty file, throws emptyfile exception
            if(e instanceof EmptyFileException) {
                throw (EmptyFileException) e;
            }
            // if the file wasnt able to be opened, asks for another path
            try (Scanner newinput = new Scanner(System.in)){
                String newpath = newinput.nextLine();
                return processFile(newpath);
            }
        }

        // returns result
        return result;
    }

    static int main(String args[]){

        // if there are no arguments, error
        if (args.length < 1) {
            System.out.println("Please provide a file path as an argument");
            return -1;
        }

        // initializes ret and if there is a stopword, sets it
        int ret = -1;
        String stop = null;
        if(args.length >= 2) {
            stop = args[1];
        }

        // tries the code and looks for errors
        try {
            // text is the text in the file of the first args path
            StringBuffer text = processFile(args[0]);
            //if text is not null 
            if(text != null) {
                // ret is the wordcount of text upto stop
                ret = processText(text, stop);
                // prints the wordcounts
                System.out.println("Found " + ret + " words.");
            }
        } catch (EmptyFileException e) {
           try {
            // for some reason here, the test cases want a toosmallexception even tho there is an empty file
                ret = processText(new StringBuffer(""), stop);
            } catch (InvalidStopwordException | TooSmallText e2) {
                // prints out the type of exception
                System.out.println(e2.toString());
            }
        } catch (TooSmallText | InvalidStopwordException e) {
            System.out.println(e.toString());
        }
        
        return ret;
    }
    
}
