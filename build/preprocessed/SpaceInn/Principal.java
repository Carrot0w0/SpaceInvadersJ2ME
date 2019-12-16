package SpaceInn;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

public class Principal extends GameCanvas implements Runnable{

    private Image Start;
    private Sprite sStart;
    private Thread SubProcess;
    private Graphics g;
    
    public Principal(SpaceInn SP){
        super(true);
        g = getGraphics(); 
        try {
           Start = Image.createImage("/Principal.png");
        } catch(Exception ER){}
        sStart = new Sprite(Start, 240, 320);
        SubProcess = new Thread(this);
        SubProcess.start();
    }
    
    public void run() {
        while(true) {
            sStart.paint(g);
            flushGraphics();
            try {
                SubProcess.sleep(1000);
            } catch (InterruptedException e) { }
            sStart.nextFrame();
        }
    }
    
}
