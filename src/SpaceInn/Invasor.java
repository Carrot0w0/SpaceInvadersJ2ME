package SpaceInn;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

public class Invasor {
    
    public Sprite sImagen; 
    public boolean bVivo; //Indica si esta vivo el invasor (o sea, activo en la pantalla).
    
    Invasor(Image Imagen, int SizeX, int SizeY, int PosX, int PosY)
    {//En este construcor, los parametros nos ahorran lineas en la clase Invaders
     //Aquí hacemos todo el proceso de llamado de imagen (Imagen), que se usa en el sprite
     //Tamaño de la imagen (SizeX, SizeY), que se usa en la creacion del Sprite
     //Posicion en la pantalla (PosX, PosY), con la funcion setPosition.
        sImagen = new Sprite(Imagen, SizeX, SizeY);
        sImagen.setPosition(PosX, PosY);
        bVivo = true; //Indica que esta vivo en la pantalla
    }
    
    public void Pintar(Graphics g)   
    {
        if(bVivo) //Valora si esta vivo antes de pintar, si es cierto, entra en esta condicion
        {
            sImagen.paint(g);
            sImagen.nextFrame();
        }
    }

    public void Morir() //Metodo para la eliminacion del invasor
    {
        bVivo = false;
    }
    
}
