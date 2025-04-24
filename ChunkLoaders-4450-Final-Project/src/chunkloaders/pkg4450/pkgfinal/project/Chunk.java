/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

import java.nio.FloatBuffer;
import java.util.Random;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*; 
import static org.lwjgl.opengl.GL15.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


/**
 *
 * @author Wheatley
 */
public class Chunk 
{
    static final int CHUNK_SIZE = 30;
    static final int WORLD_HEIGHT = 100;
    static final int CUBE_LENGTH = 2;
    private Block[][][] Blocks;
    private int VBOVertexHandle;
    private int VBOColorHandle;
    private int VBOTextureHandle;
    private Texture texture;
    private float startX, startY, startZ;
    private Random r;
    
    public void render()
    {
        
        //Enables PNG Transparency
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        glPushMatrix();
        //texture.bind();
            glBindBuffer(GL_ARRAY_BUFFER, VBOVertexHandle);
            glVertexPointer(3, GL_FLOAT, 0, 0L);
            
            glBindBuffer(GL_ARRAY_BUFFER, VBOColorHandle);
            glColorPointer(3, GL_FLOAT, 0, 0L);
            
            glBindBuffer(GL_ARRAY_BUFFER, VBOTextureHandle);
           glBindTexture(GL_TEXTURE_2D, 1);
            glTexCoordPointer(2,GL_FLOAT,0,0L);

            
            glDrawArrays(GL_QUADS, 0, CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE * 24);
        glPopMatrix();
    }
    
    public final void rebuildMesh(float startX, float startY, float startZ)
    {
        VBOColorHandle = glGenBuffers();
        VBOVertexHandle = glGenBuffers();
        VBOTextureHandle = glGenBuffers();
        
        int numBlocks = CHUNK_SIZE * WORLD_HEIGHT * CHUNK_SIZE;

    FloatBuffer VertexPositionData = BufferUtils.createFloatBuffer(numBlocks * 24 * 3); 
    FloatBuffer VertexColorData    = BufferUtils.createFloatBuffer(numBlocks * 24 * 3); 
    FloatBuffer VertexTextureData  = BufferUtils.createFloatBuffer(numBlocks * 24 * 2); 


        
        for (float x = 0; x < CHUNK_SIZE; x += 1)
        {
            for (float z = 0; z < CHUNK_SIZE; z += 1)
            {
                for (float y = 0; y < WORLD_HEIGHT; y++)
                {
                    Block block = Blocks[(int) x][(int) y][(int) z];// Skip render for default
                    if (block == null || block.getID() == Block.BlockType.BlockType_Default.getID()) continue;

                    VertexPositionData.put(createCube((float)(startX + x * CUBE_LENGTH), (float)((int)(CHUNK_SIZE * 0.8) + y * CUBE_LENGTH), (float)(startZ + z * CUBE_LENGTH)));
                    VertexColorData.put(createCubeVertexCol(getCubeColor(Blocks[(int) x]
                                                                               [(int) y] 
                                                                               [(int) z])));
                    VertexTextureData.put(createTexCube((float) 0, (float) 0,Blocks[(int)(x)][(int) (y)][(int) (z)]));
                }
            }
        }
        
        VertexColorData.flip(); 
        
        VertexPositionData.flip(); 
        VertexTextureData.flip();
        
        
        
        glBindBuffer(GL_ARRAY_BUFFER, VBOVertexHandle); 
        glBufferData(GL_ARRAY_BUFFER, VertexPositionData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0); 
        
        glBindBuffer(GL_ARRAY_BUFFER, VBOColorHandle); 
        glBufferData(GL_ARRAY_BUFFER, VertexColorData, GL_STATIC_DRAW); 
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        glBindBuffer(GL_ARRAY_BUFFER, VBOTextureHandle);
        glBufferData(GL_ARRAY_BUFFER, VertexTextureData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

    }
    
public static float[] createTexCube(float x, float y, Block block) {
    float tileSize = 1.0f / 16.0f;

    int tileX = 0;
    int tileY = 0;

    switch (block.getID()) {
        case 0: // Default
            tileX = 4; tileY = 11;
            break;
        case 1: // Grass
            tileX = 3; tileY = 0;
            break;
        case 2: // Sand
            tileX = 0; tileY = 11;
            break;
        case 3: // Water
            tileX = 14; tileY = 0;
            break;
        case 4: // Dirt
            tileX = 2; tileY = 0;
            break;
        case 5: // Stone
            tileX = 1; tileY = 0;
            break;
        case 6: // Bedrock
            tileX = 1; tileY = 1;
            break;
    }

    return createTexCubeFromTile(tileX, tileY, tileSize);
}
private static float[] createTexCubeFromTile(int tileX, int tileY, float tileSize) {
    float xMin = tileX * tileSize;
    float yMax = tileY * tileSize;
    float xMax = xMin + tileSize;
    float yMin = yMax + tileSize;

    return new float[] {
        // TOP
        xMax, yMax,  xMin, yMax,  xMin, yMin,  xMax, yMin,
        // BOTTOM
        xMax, yMax,  xMin, yMax,  xMin, yMin,  xMax, yMin,
        // FRONT
        xMax, yMax,  xMin, yMax,  xMin, yMin,  xMax, yMin,
        // BACK
        xMax, yMax,  xMin, yMax,  xMin, yMin,  xMax, yMin,
        // LEFT
        xMax, yMax,  xMin, yMax,  xMin, yMin,  xMax, yMin,
        // RIGHT
        xMax, yMax,  xMin, yMax,  xMin, yMin,  xMax, yMin
    };
}

    
    private float[] createCubeVertexCol(float[] CubeColorArray) 
    { 
        float[] cubeColors = new float[CubeColorArray.length * 4 * 6]; 
        
        for (int i = 0; i < cubeColors.length; i++) 
        { 
            cubeColors[i] = CubeColorArray[i % CubeColorArray.length]; 
        } 
        return cubeColors; 
    }
    
    public static float[] createCube(float x, float y, float z) 
    { 
        int offset = CUBE_LENGTH / 2;
        
        return new float[] 
        { 
            // TOP QUAD 
            x + offset, y + offset, z, 
            x - offset, y + offset, z, 
            x - offset, y + offset, z - CUBE_LENGTH, 
            x + offset, y + offset, z - CUBE_LENGTH, 
            
            // BOTTOM QUAD 
            x + offset, y - offset, z - CUBE_LENGTH, 
            x - offset, y - offset, z - CUBE_LENGTH, 
            x - offset, y - offset, z, 
            x + offset, y - offset, z, 

            // FRONT QUAD 
            x + offset, y + offset, z - CUBE_LENGTH, 
            x - offset, y + offset, z - CUBE_LENGTH, 
            x - offset, y - offset, z - CUBE_LENGTH, 
            x + offset, y - offset, z - CUBE_LENGTH, 

            // BACK QUAD 
            x + offset, y - offset, z, 
            x - offset, y - offset, z, 
            x - offset, y + offset, z, 
            x + offset, y + offset, z, 

            // LEFT QUAD 
            x - offset, y + offset, z - CUBE_LENGTH, 
            x - offset, y + offset, z, 
            x - offset, y - offset, z, 
            x - offset, y - offset, z - CUBE_LENGTH, 

            // RIGHT QUAD 
            x + offset, y + offset, z, 
            x + offset, y + offset, z - CUBE_LENGTH, 
            x + offset, y - offset, z - CUBE_LENGTH, 
            x + offset, y - offset, z 
        }; 
    }
    
    private float[] getCubeColor(Block block) 
    { 

        return new float[] { 1, 1, 1 }; 
    }
    
    public Chunk(float startX, float startY, float startZ, int[][] heightMap) 
    {   
        try{texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("/src/chunkloaders/pkg4450/pkgfinal/project/terrain.png"));
        }
        catch(Exception e)
        {
            System.out.print("ERROR!");
        }

        r = new Random(); 
        Blocks = new Block[CHUNK_SIZE][WORLD_HEIGHT][CHUNK_SIZE]; 
        
        for (int x = 0; x < CHUNK_SIZE; x++)
        {
            for (int z = 0; z < CHUNK_SIZE; z++)
            {
                int worldH = heightMap[z][x];
                
                for (int y = 0; y < WORLD_HEIGHT; y++)
                {
                    if (y < worldH)
                    {
                        if (y > worldH - 2)
                        {
                            Blocks[x][y][z] = new Block(Block.BlockType.BlockType_Grass); 
                        }
                        else if (y < worldH - (worldH - 1))
                        {
                            Blocks[x][y][z] = new Block(Block.BlockType.BlockType_Bedrock);
                        }
                        else
                        {
                            Blocks[x][y][z] = new Block(Block.BlockType.BlockType_Dirt); 
                        }
                    }
                    else
                    {
                        Blocks[x][y][z] = new Block(Block.BlockType.BlockType_Default);
                    }
                }
            }
        }
        
        VBOColorHandle = glGenBuffers(); 
        VBOVertexHandle = glGenBuffers(); 
        VBOTextureHandle = glGenBuffers();
        this.startX = startX; 
        this.startY = startY; 
        this.startZ = startZ; 
        rebuildMesh(startX, startY, startZ); 
    } 
}
