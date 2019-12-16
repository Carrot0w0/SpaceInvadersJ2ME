package SpaceInn;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Vector;

public class Invaders extends GameCanvas implements Runnable{
    
    //Se deriva de la clase GameCanvas, que es la que facilita la creacion 
    //de un juego. 
    
    //La libreria lcdui.game es la que nos ayuda a usar los sprites.
    //La libreria Vector nos ayuda a la creación de un vector, que es como un
    //arreglo dinamico.

    private Image Nave, Fondo; 
    private Thread SubProcess; //Hilo, para poder ejecutar el movimiento de los sprites.
    private Graphics g; //Para poder dibujar en nuestro GameCanvas.
    private Sprite sNave, sFondo; 
    private Invasor Invasores[]; //Arreglo tipo Invasor (Clase) para poder invocar                                 
    private int x;               //nuestros invasores de una manera rapida y sencilla.
    public Vector Balas; //Vector que nos ayuda a la invocacion de las balas.
    public int c;
    
    public Invaders(SpaceInn SP){
        super(true); //Menciona que se usaran las teclas, por eso esta en "true".
        g = getGraphics(); //Obtenemos los graficos.
        try { 
           Invasores = new Invasor[12]; //Vease "Clase Invsor" para explicar esto.
           Invasores[0] = new Invasor(Image.createImage("/In1.png"), 35, 26, 15, 12);
           Invasores[1] = new Invasor(Image.createImage("/In2.png"), 38, 26, 60, 12);
           Invasores[2] = new Invasor(Image.createImage("/In3.png"), 32, 26, 108, 12);
           Invasores[3] = new Invasor(Image.createImage("/In4.png"), 26, 26, 146, 50);
           Invasores[4] = new Invasor(Image.createImage("/In5.png"), 29, 26, 150, 12);
           Invasores[5] = new Invasor(Image.createImage("/In6.png"), 23, 26, 182, 50);
           Invasores[6] = new Invasor(Image.createImage("/In7.png"), 32, 26, 189, 12);
           Invasores[7] = new Invasor(Image.createImage("/In8.png"), 26, 26, 69, 50);
           Invasores[8] = new Invasor(Image.createImage("/In9.png"), 26, 26, 110, 50);
           Invasores[9] = new Invasor(Image.createImage("/In10.png"), 23, 20, 109, 90);
           Invasores[10] = new Invasor(Image.createImage("/In11.png"), 29, 23, 25, 52);
           Invasores[11] = new Invasor(Image.createImage("/In12.png"), 24, 15, 108, 120);
           Nave = Image.createImage("/Nave.png"); //Creamos la imagen de la nave, y la del fondo abajo.
           Fondo = Image.createImage("/Space.png");
        } catch (Exception e) { }
        sFondo = new Sprite(Fondo, 240, 320); //Creamos el sprite del fondo, abajo el de la nave.
        sNave = new Sprite(Nave, 22, 16);
        SubProcess = new Thread(this); //Tenemos que indicar donde se va a realizar nuestro hilo.
        SubProcess.start();
        
        Balas = new Vector();
        
        x=109;
        c=0;
    }
    
    public void run() { //Método generado automaticamente por la implementacion
                        //de la clase Runnable
        int i;        
        sNave.setPosition(x, 250);
        
        while(true) { //Mientras este activo (true) pasara todo lo que esta dentro.
            sFondo.paint(g);
            
            //Pintamos el arreglo de invasores.
            for(i = 0; i < 12;i++)
            {
                Invasores[i].Pintar(g);
            }
            
            
            for(i = 0; i < Balas.size(); i++) 
            {//El ciclo entra si "i" es menor al tamaño del vector Balas
                if(((Bala)(Balas.elementAt(i))).bChoco) //Valora si ha colisionado.
                {//Convertimos el vector Balas a tipo Bala (Clase)
                    Balas.removeElementAt(i); //Se remueve justamente el elemento con el que ha chocado
                    c++;
                    i--;
                    continue;
                    //Se eliminan los elementos (Invasor tocado y la bala)
                    //Se desincrementa la i para que no continue lo que es la bala
                    //despues de haber tocado el invasor.
                    //El continue nos ayuda para que el ciclo siga funcionando
                    //y podamos seguir jugando.
                }
                        
                ((Bala)(Balas.elementAt(i))).Update(); //Vease clase "Bala"
                ((Bala)(Balas.elementAt(i))).dibujar(g); //Vease clase "Bala"
            }
            sNave.paint(g);
            flushGraphics();
            try {
                SubProcess.sleep(200);
            } catch (InterruptedException e) { }
            sFondo.nextFrame(); //Para el siguiente sprite
        }
    }
    
    public void keyPressed(int keyCode)
    {
        Bala NuevaBala;
        
        switch(keyCode){
            case KEY_NUM6:
                if(sNave.getX()<getWidth()-22){
                    sNave.move(8, 0);                  
                }  
                break;
            case KEY_NUM4:
                if(sNave.getX()>0){
                    sNave.move(-8,0);
                }
                break;
            case KEY_NUM5:
                NuevaBala = new Bala(Invasores, sNave.getX() + 7, sNave.getY() - 30 ); 
                //Invocación de la bala, y sus coordenadas para que se vea.
                Balas.addElement(NuevaBala);                
                //Al vector Balas, se agrega la bala que se esta usando
                break;
        }
    }
    
    public void keyRepeated(int keyCode){
        
        switch(keyCode){
            case KEY_NUM6:
                if(sNave.getX()+8<getWidth()-22){
                    sNave.move(10, 0);                  
                }                    
                break;
            case KEY_NUM4:
                if(sNave.getX()+8>0){
                    sNave.move(-10,0);
                }
                break;
        }
    }
    
}