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

    // Create chunks (5x5 grid)
    private final int CHUNK_GRID_SIZE = 5;
    private final int CHUNK_SIZE = 30;
    private Chunk[][] chunks = new Chunk[CHUNK_GRID_SIZE][CHUNK_GRID_SIZE];
    
    //Create mouse and keyboard controller
    private MouseMove mouseMove;
    private KeyboardMove keyboardMove;
    
    //Terrain Generation code
    int[][] heightMap;
    int[][] topBlockMap;
    int[][][] undergroundBlockMap;
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
        camera = new Camera(0.0f, -120.0f, 0.0f);
        keyboardMove = new KeyboardMove(camera);
        // Init chunk creation
        for (int x = 0; x < CHUNK_GRID_SIZE; x++)
        {
            for (int z = 0; z < CHUNK_GRID_SIZE; z++)
            {
                // Extract sub-region from heightMap for this chunk
                int[][] subMap = new int[CHUNK_SIZE][CHUNK_SIZE];
                // Interate through matrix
                for (int i = 0; i < CHUNK_SIZE; i++)
                {
                    for (int j = 0; j < CHUNK_SIZE; j++)
                    {
                        // Hold worldx and z within vars
                        int worldX = x * CHUNK_SIZE + i;
                        int worldZ = z * CHUNK_SIZE + j;
                        
                        // Checking bounds to make sure it works!
                        if (worldX >= heightMap.length || worldZ >= heightMap[0].length) 
                        {
                            System.err.println("Out of bounds at: " + worldX + "," + worldZ);
                        }
                        // Create submap :D
                        subMap[i][j] = heightMap[worldX][worldZ];
                    }
                }
                
                float worldX = x * CHUNK_SIZE;
                float worldZ = z * CHUNK_SIZE;
                chunks[x][z] = new Chunk(worldX, 0.0f, worldZ, subMap);
            }
        }
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
                for (int x = 0; x < CHUNK_GRID_SIZE; x++)
                {
                    for (int z = 0; z < CHUNK_GRID_SIZE; z++)
                    {
                        chunks[x][z].render();
                    }
                }
                
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
        System.out.println("Randomly Generated Height Map Seed: " + seed);
        // randomNum will be between min and max (inclusive)
        
        int worldLength = chunkLength*chunkSize;
        int worldWidth = chunkWidth*chunkSize;
        
        //heightMap = new int[chunkWidth * chunkSize][chunkLength * chunkSize];
        heightMap = TerrainGenerator.getRandomHeightMap(seed, worldWidth, worldLength, baseHeight);
        
        
        //New seed for top block gen
        seed = rand.nextLong();
        System.out.println("Randomly Generated Top Block Map Seed: " + seed);
        
        topBlockMap = TerrainGenerator.getRandomTopBlockMap(seed, worldWidth, worldLength);
        
        //New seed for underground block gen
        seed = rand.nextLong();
        System.out.println("Randomly Generated Underground Block Map Seed: " + seed);
        
        undergroundBlockMap = TerrainGenerator.getRandomUndergroundBlockMap(seed, worldWidth, worldLength);
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
