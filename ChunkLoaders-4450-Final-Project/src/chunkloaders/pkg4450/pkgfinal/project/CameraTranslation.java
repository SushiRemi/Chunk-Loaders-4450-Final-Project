/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

//importing lwjgl tools
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Julianne
 */
public class CameraTranslation {
    
    
    //Input: x, y, z coords of camera, translation speed s - Output: x, y, z coords of camera after translation
    public float[] translateX (float[] coords, float speed){
        glTranslatef(speed, 0.0f, 0.0f);
    }
    
    public float[] translateY (float[] coords, float speed){
        glTranslatef(0.0f, speed, 0.0f);
    }
    
    public float[] translateZ (float[] coords, float speed){
        glTranslatef(0.0f, 0.0f, speed);
    }
    
}
