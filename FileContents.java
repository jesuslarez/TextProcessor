package textprocessing;
import java.util.Queue;
import java.util.LinkedList; 

public class FileContents {
    private Queue<String> queue;
    private int registerCount = 0;
    private boolean acceptContents = true;
    private int maxFiles;
    private int maxChars;
    private int currentChars;
    
    public FileContents(int maxFiles, int maxChars) {
        this.maxFiles = maxFiles;
        this.maxChars = maxChars;
        this.queue = new LinkedList<>();
    }
    
    public void registerWriter() {
        this.registerCount ++;
        
    }
    
    public void unregisterWriter() {
        this.registerCount --;
        if (this.registerCount == 0){
            this.acceptContents = false;
            notifyAll();
        } 
    }
    public synchronized void addContents(String contents) throws InterruptedException{
        while(checkMaxFiles() && checkMaxChars(contents)){
            wait();
        }
        
        this.queue.add(contents);
        this.currentChars += contents.length();
        notifyAll();
    }
    
    public synchronized String getContents() throws InterruptedException{
        
        while (this.queue.peek() == null) {
            if (!this.acceptContents){
                break;
            }
            wait();
        }
        notify();
        return this.queue.poll();
    }
    //Funcion para comprobar que se ha alcanzado el limite de archivos
    private boolean checkMaxFiles(){
        if (this.queue.size() == this.maxFiles) return false;
        return true;
    }
    /*Funcion para calcular que si se añade el contenido actual se sobrepasaria 
    el limite de caracteres
    */
    private boolean checkMaxChars(String contents){
        if (this.currentChars + contents.length() > this.maxChars) return false;
        return true;
    }
    
}