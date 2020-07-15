package textprocessing;
import java.util.Map;
import java.util.HashMap;
public class FileProcessor extends Thread{
    private FileContents fc;
    private WordFrequencies wf;
    private Map<String, Integer> frequency = new HashMap<>();
    
    public FileProcessor(FileContents fc, WordFrequencies wf){
        this.fc = fc;
        this.wf = wf;
    }
    
    public synchronized void run(){
        try {
            System.out.println("Inicia un hilo de FileProcesor");
            String content = fc.getContents();
            while (content != null){
                // ([^a-zA-ZÀ-ÿ']+)'*\\1*
                String[] words = content.split("[^0-9A-Za-zñÑáÁéÉíÍóÓúÚ]+");
                for (String word : words) { 
                    this.frequency.compute(word, (k, v) -> v == null ? 1 : v + 1); 
                }
            
            content = this.fc.getContents();
            }
        } catch(Exception e) {}
        wf.addFrequencies(this.frequency);
    System.out.println("Finalizo un hilo de FileProcesor");
    }
}

/*
26658 de
20180 que
17104 la
17093 y
13289 a
13210 el
11587 en
7573 no
7271 se
6994 los
*/