import java.text.BreakIterator;
import java.io.*;
import java.util.*;

public class Document{

    int id;
    int wordNum;
    ArrayList<String> words;
    
    public Document(int id, String document, String type) throws DocumentDefTypeException{
        this.id = id;
        if(type == "string"){
            this.words = tokenize(document);
        }else if(type == "file"){
            try{
                BufferedReader br = new BufferedReader(new FileReader(document));
                StringBuilder sb = new StringBuilder();
                sb.append(br.readLine() + " ");
                this.words = tokenize(new String(sb));
            }catch(IOException e){
                System.err.println(e);
            }     
        }else{
            new DocumentDefTypeException();
        }
        this.wordNum = words.size();
    }

    

    private static ArrayList<String> tokenize(String document){
        ArrayList<String> tokens = new ArrayList<String>();
        ArrayList<String> stopwords = WordDictionary.stopwords();
        BreakIterator bi = BreakIterator.getWordInstance();
        bi.setText(document);
        int start = bi.first();
        int end = 0;
        String token = "";
        while((end = bi.next()) != BreakIterator.DONE){
            Porter porter = new Porter();
            token = porter.stripAffixes(document.substring(start,end));
            if (!(stopwords.contains(token) || token=="")){
                tokens.add(token);
            }
            start = end;
        }
        System.out.println("Finished tokenizing.");
        return tokens;
    }
    
}

class DocumentDefTypeException extends Exception{

    public DocumentDefTypeException(){
        System.err.println("In definining document object, you have to choose appropriate type");
    }
    
}