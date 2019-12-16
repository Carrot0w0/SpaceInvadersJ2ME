package SpaceInn;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class SpaceInn extends MIDlet implements CommandListener{

    private Display Pantalla;
    private Invaders Inn; //Clase del juego.
    private Command Start, Exit;
    private Principal Prin; //Clase de la pantalla principal.
    private Alert GameOver;
    
    public SpaceInn(){
        Pantalla = Display.getDisplay(this);
        Inn = new Invaders(this); 
        Prin = new Principal(this);
        Exit = new Command("Salir", Command.EXIT, 1);
        Start = new Command("Start", Command.OK, 1);
        
        Prin.addCommand(Exit);
        Prin.addCommand(Start);
        Inn.addCommand(Exit);
        Prin.setCommandListener(this);
        Inn.setCommandListener(this);
        
        GameOver = new Alert("GameOver", "El juego ha terminado\nHas ganado! :D", null, AlertType.CONFIRMATION);
    }
    
    public void startApp() {
        Pantalla.setCurrent(Prin);
        //Se llamo la clase Principal, para mostrar la primer pantalla del juego.
        //Esta clase también es GameCanvas.
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if(c==Exit){
            destroyApp(true);
            notifyDestroyed();
        }
        else{
            if(c==Start){
                Pantalla.setCurrent(Inn);
                //Al presionar "Start", la pantalla de Principal se quita, y se 
                //invoca la del juego
                if(Inn.c==12)
                    Pantalla.setCurrent(GameOver, Inn);
            }
        }
    }
    
    public void JuegoTerminado(){
        if(Inn.c==12)
            Pantalla.setCurrent(GameOver, Inn);
    }
}
