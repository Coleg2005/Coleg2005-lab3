import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;
import java.util.*;


public class WordCounter {

    public static int processText(StringBuffer input, String stop) throws InvalidStopwordException, TooSmallText {

        Pattern regex = Pattern.compile("[a-zA-Z_0-9]+");
        Matcher regexMatcher = regex.matcher(input);
        boolean found = false;
        int wordcount = 0;

        while(regexMatcher.find()) {
            String cur = regexMatcher.group();
            wordcount++;
            if(wordcount > 5 && cur.equals(stop)){
                found = true;
                break;
            }
        }

        if(wordcount < 5) {
            throw new TooSmallText("TooSmallText: Only found " + wordcount + " words.", wordcount);
        }

        if(stop == null) {
            return wordcount;
        }

        if(found == false){
            throw new InvalidStopwordException("InvalidStopwordException: Couldn't find stopword: " + stop, stop);
        }

        return wordcount;
    }

    public static StringBuffer processFile(String path) throws EmptyFileException{

        try{
            String text = Files.readString(Paths.get(path));
            if(text.isEmpty()) {
                throw new EmptyFileException("This file is empty", path);
            }
            return new StringBuffer(text);

        } catch (IOException e) {
            if(e instanceof EmptyFileException) {
                throw (EmptyFileException) e;
            }
            System.out.println("This file cannot be opened, please try again");
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a new filepath:");
        String newpath = input.nextLine();

        input.close();
        return processFile(newpath);

    }

    static int main(String args[]){

        Scanner input = new Scanner(System.in);
        int ret = -1;
                
        try{

            System.out.println("Enter '1' if you are entering a file");
            System.out.println("Enter '2' if you are entering a text");

            int option = input.nextInt();
            input.nextLine();

            String stop = null;
            if(args.length >= 2) {
                stop = args[1];
            }

            if(option == 1) {
                System.out.println("Please input file path");
                String path = input.nextLine();
                StringBuffer text = processFile(path);
                if(text != null) {
                    ret = processText(text, stop);
                    System.out.println("Found" + ret + " words.");
                }
            } else if (option == 2) {
                System.out.println("Please input the text");
                String temp = input.nextLine();
                StringBuffer text = new StringBuffer(temp);
                ret = processText(text, stop);
                System.out.println("Found" + ret + " words.");
            } else {
                System.out.println("Invalid option. Enter 1 or 2.");
            }

        } catch (EmptyFileException| TooSmallText| InvalidStopwordException e){
            System.out.println(e.toString());
        } finally {
            input.close();
        }
        return ret;
    }
    
}
