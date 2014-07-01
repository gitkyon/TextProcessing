import java.util.*;

public class BagOfWords{

    int[] feature;

    public BagOfWords(WordDictionary dictionary,Document document){
        System.out.println(dictionary.nextId);
        feature = new int[dictionary.nextId];
        ArrayList<String> words = document.words;
        for (Iterator iter = words.iterator(); iter.hasNext(); ){
            feature[dictionary.getId(iter.next().toString())]++;
        }
    }

    public String toString(){
        String bow = "";
        bow += ("(");
        for (int i=0; i<feature.length-1; i++){
            bow += feature[i] + ",";
        }
        bow += feature[feature.length-1] + ")";
        return bow;
    }

}