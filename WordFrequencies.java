package textprocessing;
import java.util.Map;
import java.util.HashMap;

public class WordFrequencies {
    private Map<String, Integer> totalFrequencies = new HashMap<>();
    
    public synchronized void addFrequencies(Map<String,Integer> f){
        f.forEach(
        (word, frequency) -> totalFrequencies.merge( word, frequency, (v1, v2) -> 
        v1 == null ? v1 : v1 + v2)
        );
    }
    public Map<String,Integer> getFrequencies(){
        return totalFrequencies;
    }
}