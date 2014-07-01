import java.io.*;
import java.util.*;

public class WordDictionary{

    HashMap<String,Integer> dictionary;
    int nextId;

    public WordDictionary(){
        dictionary = new HashMap<String,Integer>();
        nextId = 0;
    }

    public WordDictionary(Document document){
        dictionary = new HashMap<String,Integer>();
        nextId = 0;
        for (Iterator iter = document.words.iterator(); iter.hasNext(); ){
            this.put(iter.next().toString());
        }
    }
    
    public void put(String word){
        if (word == "") return;
        Porter porter = new Porter();
        word = porter.stripAffixes(word);
        ArrayList<String> stopwords = stopwords();
        if (!(stopwords.contains(word))){
            if (!dictionary.containsKey(word)){
                dictionary.put(word,nextId);
                nextId++;
            }
        }
    }

    public int getId(String word){
        System.out.println(word);
        return dictionary.get(word);
    }

    public int size(){
        return dictionary.size();
    }

    public static ArrayList<String> stopwords(){
        ArrayList<String> stopwords = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("./stop-words/stop-words_english_6_en.txt"));
            String arrayToken;
            while((arrayToken = br.readLine())!=null){
                stopwords.add(arrayToken);
            }
            br.close();
        }catch(IOException e){
            System.err.println(e);
        }
        return stopwords;
    }

    public void dictionaryFileInput(String fileName){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            String[] arrayToken = new String[2];
            int wordId = 0;
            String entry;
            while((line = br.readLine()) != null){
                arrayToken = line.split(" ");
                entry = arrayToken[0];
                this.put(entry);
            }
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
    public void dictionaryFileOutput(String fileName){
        try{
            FileWriter fw = new FileWriter(new File(fileName));
            int i = 0;
            for (Iterator iter = dictionary.entrySet().iterator(); iter.hasNext(); ){
                fw.write(((Map.Entry)iter.next()).getKey().toString() + " " + i++ + "\r\n");
            }
            fw.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }

    
    
}