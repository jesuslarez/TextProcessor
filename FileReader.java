package textprocessing;
public class FileReader extends Thread{
    private FileNames fn;
    private FileContents fc;
    
    public FileReader(FileNames fn, FileContents fc){
        this.fn = fn;
        this.fc = fc;
    }
    
    public synchronized void run(){
        try {
            System.out.println("Inicia un hilo de FileReader");
            this.fc.registerWriter();   
            String name = this.fn.getName();
            String content = null;
            
            while (name != null){
                content = Tools.getContents(name);  
                this.fc.addContents(content);
                name = this.fn.getName();
            }
            
            System.out.println("Finalizo un hilo de FileReader");
            this.fc.unregisterWriter();
        } catch(Exception e) {}
    }
}
   