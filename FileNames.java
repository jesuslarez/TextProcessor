package textprocessing;
import java.util.Queue;
import java.util.LinkedList; 

public class FileNames {
    private Queue<String> queue = new LinkedList<>();
    private boolean acceptNames = true;
    
    public synchronized void addName(String fileName) {
        if (this.acceptNames){
          this.queue.add(fileName); 
          notifyAll();
        }else{
            return;
        }
    }
    
    public synchronized String getName() throws InterruptedException{
        while (this.queue.peek() == null && this.acceptNames) {
            if (!this.acceptNames){
                break;
            }
            wait();
        }
        notifyAll();
        return this.queue.poll();
    }
    public synchronized void noMoreNames() {
        this.acceptNames = false;
        notifyAll();
    }
}
