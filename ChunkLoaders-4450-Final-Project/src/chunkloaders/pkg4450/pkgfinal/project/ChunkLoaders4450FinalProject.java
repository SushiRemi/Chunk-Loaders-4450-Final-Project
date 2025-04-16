/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

import java.util.Random;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Julianne, Valen, Ada
 */
public class ChunkLoaders4450FinalProject {
    // FOR TESTING
    private float rotation = 0.0f;
    // FOR TESTING
    
    // Create cam controller
    private Camera camera;

    // Create chunk
    private Chunk chunk;
    
    //Create mouse and keyboard controller
    private MouseMove mouseMove;
    private KeyboardMove keyboardMove;
    
    //Terrain Generation code
    int[][] heightMap;
    int baseHeight;
    long seed;
    
    
    public void start() 
    { 
        
        try  
        { 
            initializeTerrain(50, 2, 2); //To generate Terrain map with parameters(base height, chunk width, chunk length)
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
        
        glEnableClientState(GL_VERTEX_ARRAY); 
        glEnableClientState(GL_COLOR_ARRAY); 
        // I predicted the future hehe
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glEnableClientState (GL_TEXTURE_COORD_ARRAY);
        
        glMatrixMode(GL_PROJECTION);  
        glLoadIdentity();  
         
        GLU.gluPerspective(45.0f, 640.0f/480.0f, 0.1f, 100.0f);
         
        glMatrixMode(GL_MODELVIEW);  
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 
        
        // Initialize camera, mouse, and keyboard
        camera = new Camera(0.0f, -100.0f, 0.0f);
        keyboardMove = new KeyboardMove(camera);
        // Init chunk creation
        chunk = new Chunk(0.0f, 0.0f, 0.0f, heightMap);
        // Pin mouse to window
        Mouse.setGrabbed(true);
        mouseMove = new MouseMove(camera);
    }
    private void render()
    {
        while (!Display.isCloseRequested())
        {
            try
            {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  
                glLoadIdentity(); 
                
                // Update mouse and keyboard movements
                mouseMove.update();
                keyboardMove.update();
                // Update camera config
                camera.lookThrough();
                // Generate chunks
                chunk.render();
                
                glTranslatef(0.0f, 0.0f, -5.0f); // Move cube into view
                
                // FOR TESTING
                glRotatef(rotation, 1.0f, 1.0f, 0.0f);
                // FOR TESTING
                
                //Cube.draw();
                
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
    
    private void initializeTerrain(int bHeight, int chunkWidth, int chunkLength){
        baseHeight = bHeight;
        int chunkSize = 30; //make sure this is same as chunkSize in Chunk.java
        
        //Random seed generation
        Random rand = new Random();
        seed = rand.nextLong();
        System.out.println("Randomly Generated Seed: " + seed);
        // randomNum will be between min and max (inclusive)
        
        int worldLength = chunkLength*chunkSize;
        int worldWidth = chunkWidth*chunkSize;
        
        //heightMap = new int[chunkWidth * chunkSize][chunkLength * chunkSize];
        heightMap = TerrainGenerator.getRandomHeightMap(seed, worldWidth, worldLength, baseHeight);
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
