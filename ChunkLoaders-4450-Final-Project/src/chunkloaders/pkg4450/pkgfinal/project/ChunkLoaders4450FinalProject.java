/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Julianne, Valen
 */
public class ChunkLoaders4450FinalProject {
    // FOR TESTING
    private float rotation = 0.0f;
    // FOR TESTING
    
    public void start() 
    { 
        try  
        { 
            createWindow(); 
            initGL(); 
            render();
        } 
        catch (Exception e)  
        { 
            e.printStackTrace(); 
        } 
    } 
    private void createWindow() throws Exception 
    { 
        Display.setFullscreen(false); 
        Display.setDisplayMode(new DisplayMode(640, 480)); 
        Display.setTitle("Semester Project - Mini Minecraft Demo"); 
        Display.create(); 
    } 
    private void initGL() 
    { 
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
         
        // Enables depth testing and fixes not being able to
        // see the red face :D
        glEnable(GL_DEPTH_TEST);
        
        glMatrixMode(GL_PROJECTION);  
        glLoadIdentity();  
         
        GLU.gluPerspective(45.0f, 640.0f/480.0f, 0.1f, 100.0f);
         
        glMatrixMode(GL_MODELVIEW);  
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 
    }
    private void render()
    {
        while (!Display.isCloseRequested())
        {
            try
            {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  
                glLoadIdentity(); 
                
                // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //
                // Might have to tweak this for your code @Juli // 
                // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //
                glTranslatef(0.0f, 0.0f, -5.0f); // Move cube into view
                
                // FOR TESTING
                glRotatef(rotation, 1.0f, 1.0f, 0.0f);
                // FOR TESTING
                
                drawCube();
                
                // FOR TESTING
                rotation += 0.5f;
                // FOR TESTING
                
                Display.update(); 
                Display.sync(60); 
            }
            catch (Exception e)
            {
                e.printStackTrace(); 
            }
        }
    }
    
    private void drawCube()
    {
        try
        {
            glBegin(GL_QUADS);
                // Front face (red)
                glColor3f(1.0f, 0.0f, 0.0f);
                glVertex3f(-1.0f, -1.0f,  1.0f);
                glVertex3f( 1.0f, -1.0f,  1.0f);
                glVertex3f( 1.0f,  1.0f,  1.0f);
                glVertex3f(-1.0f,  1.0f,  1.0f);

                // Back face (green)
                glColor3f(0.0f, 1.0f, 0.0f);
                glVertex3f(-1.0f, -1.0f, -1.0f);
                glVertex3f(-1.0f,  1.0f, -1.0f);
                glVertex3f( 1.0f,  1.0f, -1.0f);
                glVertex3f( 1.0f, -1.0f, -1.0f);

                // Left face (blue)
                glColor3f(0.0f, 0.0f, 1.0f);
                glVertex3f(-1.0f, -1.0f, -1.0f);
                glVertex3f(-1.0f, -1.0f,  1.0f);
                glVertex3f(-1.0f,  1.0f,  1.0f);
                glVertex3f(-1.0f,  1.0f, -1.0f);

                // Right face (yellow)
                glColor3f(1.0f, 1.0f, 0.0f);
                glVertex3f( 1.0f, -1.0f, -1.0f);
                glVertex3f( 1.0f,  1.0f, -1.0f);
                glVertex3f( 1.0f,  1.0f,  1.0f);
                glVertex3f( 1.0f, -1.0f,  1.0f);

                // Top face (cyan)
                glColor3f(0.0f, 1.0f, 1.0f);
                glVertex3f(-1.0f,  1.0f, -1.0f);
                glVertex3f(-1.0f,  1.0f,  1.0f);
                glVertex3f( 1.0f,  1.0f,  1.0f);
                glVertex3f( 1.0f,  1.0f, -1.0f);

                // Bottom face (magenta)
                glColor3f(1.0f, 0.0f, 1.0f);
                glVertex3f(-1.0f, -1.0f, -1.0f);
                glVertex3f( 1.0f, -1.0f, -1.0f);
                glVertex3f( 1.0f, -1.0f,  1.0f);
                glVertex3f(-1.0f, -1.0f,  1.0f);
            glEnd();
            
            glBegin(GL_LINE_LOOP); 
                // Sets frame color to white
                glColor3f(1.0f,1.0f,1.0f); 
                
                //Top 
                glVertex3f( 1.0f, 1.0f,-1.0f); 
                glVertex3f(-1.0f, 1.0f,-1.0f); 
                glVertex3f(-1.0f, 1.0f, 1.0f); 
                glVertex3f( 1.0f, 1.0f, 1.0f); 
            glEnd();
            glBegin(GL_LINE_LOOP); 
                //Bottom 
                glVertex3f( 1.0f,-1.0f, 1.0f); 
                glVertex3f(-1.0f,-1.0f, 1.0f); 
                glVertex3f(-1.0f,-1.0f,-1.0f); 
                glVertex3f( 1.0f,-1.0f,-1.0f); 
            glEnd();
            
            glBegin(GL_LINE_LOOP); 
                //Front 
                glVertex3f( 1.0f, 1.0f, 1.0f); 
                glVertex3f(-1.0f, 1.0f, 1.0f); 
                glVertex3f(-1.0f,-1.0f, 1.0f); 
                glVertex3f( 1.0f,-1.0f, 1.0f); 
            glEnd();
            glBegin(GL_LINE_LOOP); 
                //Back 
                glVertex3f( 1.0f,-1.0f,-1.0f); 
                glVertex3f(-1.0f,-1.0f,-1.0f); 
                glVertex3f(-1.0f, 1.0f,-1.0f); 
                glVertex3f( 1.0f, 1.0f,-1.0f); 
            glEnd();
            
            glBegin(GL_LINE_LOOP); 
                //Left 
                glVertex3f(-1.0f, 1.0f, 1.0f); 
                glVertex3f(-1.0f, 1.0f,-1.0f); 
                glVertex3f(-1.0f,-1.0f,-1.0f); 
                glVertex3f(-1.0f,-1.0f, 1.0f);
            glEnd();            
            glBegin(GL_LINE_LOOP); 
                //Right 
                glVertex3f( 1.0f, 1.0f,-1.0f); 
                glVertex3f( 1.0f, 1.0f, 1.0f); 
                glVertex3f( 1.0f,-1.0f, 1.0f); 
                glVertex3f( 1.0f,-1.0f,-1.0f);
            glEnd();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ChunkLoaders4450FinalProject program = new ChunkLoaders4450FinalProject(); 
        program.start(); 
    }
    
    
    
}
