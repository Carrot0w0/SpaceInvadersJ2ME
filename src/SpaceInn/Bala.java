package SpaceInn;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

public class Bala{
    
    public Sprite sBala; //Sprite de la bala
    private Invasor Invasores[]; 
    public boolean bChoco; //Si ha chocado o no (por eso boolean)
    
    public Bala(Invasor NuevosInvasores[], int PosX, int PosY)
    {//Invocamos la bala, indicamos su posicion (mandando los en los parametros la
     //posicion de la nave.
        try{
        sBala = new Sprite(Image.createImage("/Bala.png"), 8, 14);
        }catch(Exception ER){}
        
        sBala.setPosition(PosX, PosY);
        Invasores = NuevosInvasores;
        bChoco = false; 

    }
    
    public void Update()
    {//Se actualiza el estado de la bala con los invasores.
        int i;
        if(sBala.getY() < -14)
        {
            bChoco = true;
            return;
        }
        
        for(i = 0;i < 12; i++)
        {
            if(Invasores[i].bVivo && sBala.collidesWith(Invasores[i].sImagen, true))
            {//Si el invasor n (que esta vivo) colisiona con la bala es cierto
                bChoco = true; //Se activa la funcion de que a chocado
                Invasores[i].Morir(); //Se elimina el invasor
                return;
            }
        } 
        sBala.move(0, -20); //Movimiento continuo de la bala.
    }
    
    public void dibujar(Graphics g){
        sBala.paint(g);
    }
    
}
