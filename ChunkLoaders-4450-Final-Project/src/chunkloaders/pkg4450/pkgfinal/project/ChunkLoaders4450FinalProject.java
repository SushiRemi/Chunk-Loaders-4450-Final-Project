/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

import java.util.Random;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.BufferUtils;

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
    int[][] topBlockMap;
    int[][][] undergroundBlockMap;
    int baseHeight;
    long seed;
    
    private FloatBuffer lightPosition;
    private FloatBuffer whiteLight;
    private FloatBuffer dimLight;
    private FloatBuffer light1Pos;
    private FloatBuffer black;
    private FloatBuffer difLight;
    
    
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
        chunk = new Chunk(0.0f, 0.0f, 0.0f, heightMap, topBlockMap, undergroundBlockMap);
        // Pin mouse to window
        Mouse.setGrabbed(true);
        mouseMove = new MouseMove(camera);
        
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glEnable(GL_NORMALIZE);
        glEnable(GL_COLOR_MATERIAL);
        glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);
        initLightArrays();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);
        glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);
        //glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);
        glLight(GL_LIGHT0, GL_AMBIENT, dimLight);
        
       
    }
    
    private void updateLightPosition(){
        Vector3f camPos = camera.getPosition();
        lightPosition.clear();
        lightPosition.put(1.0f).put(-1.0f).put(1.0f).put(0.0f);
        lightPosition.flip();

        glLight(GL_LIGHT0, GL_POSITION, lightPosition);

        
    }
    
    private void render()
    {
        boolean flying = true;
        long startTime = System.currentTimeMillis();

        while (!Display.isCloseRequested())
        {
            try
            {
                if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
                    flying = !flying;
                }
                
                
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  
                glLoadIdentity(); 
                
                updateSunPosition(startTime); // update sun position


                
                // Update mouse and keyboard movements
                mouseMove.update();
                keyboardMove.update(flying);
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
    
    private void initLightArrays(){
        lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(-1.0f).put(0.0f).put(0.0f).put(0.0f).flip();
        whiteLight = BufferUtils.createFloatBuffer(4);
        whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
        dimLight = BufferUtils.createFloatBuffer(4);
        dimLight.put(0.1f).put(0.1f).put(0.1f).put(1.0f).flip();
        
        light1Pos = BufferUtils.createFloatBuffer(4);

        light1Pos.put(1.0f).put(-1.0f).put(0.0f).put(0.0f).flip();  // directional



        difLight = BufferUtils.createFloatBuffer(4);
        difLight.put(0.3f).put(0.3f).put(0.4f).put(1.0f).flip();



        black = BufferUtils.createFloatBuffer(4);
        black.put(0.0f).put(0.0f).put(0.0f).put(1.0f).flip();  
    }
    
    private void updateSunPosition(long startTime) {
        long currentTime = System.currentTimeMillis();
        float elapsedSeconds = (currentTime - startTime) / 1000.0f;
        float cycleTime = 24.0f;  // 24 seconds full rotation
        float angle = (elapsedSeconds / cycleTime) * 360.0f % 360.0f;

        float radius = 100.0f;
        float radian = (float) Math.toRadians(angle);

        float sunX = (float) Math.cos(radian) * radius;
        float sunY = (float) Math.sin(radian) * radius;  // rise and fall
        float sunZ = (float) Math.sin(radian) * radius;

        // Update directional light
        lightPosition.clear();
        lightPosition.put(sunX).put(sunY).put(sunZ).put(0.0f);
        lightPosition.flip();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);

        // Adjust ambient brightness
        float brightness = Math.max(0.1f, sunY / radius);
        dimLight.clear();
        dimLight.put(brightness).put(brightness).put(brightness).put(1.0f);
        dimLight.flip();
        glLight(GL_LIGHT0, GL_AMBIENT, dimLight);
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
