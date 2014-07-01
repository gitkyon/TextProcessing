import java.util.*;

class Test{

    public static void main(String[] args){
        try{
            Document doc = new Document(0,"data.txt","file");
            WordDictionary dict = new WordDictionary(doc);
            BagOfWords bow = new BagOfWords(dict,doc);
            System.out.println(bow);
            dict.dictionaryFileOutput("dictionary.txt");
        }catch(DocumentDefTypeException e){
            System.err.println(e);
        }
    }

}