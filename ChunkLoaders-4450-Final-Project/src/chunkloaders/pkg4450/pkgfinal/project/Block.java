/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

/**
 *
 * @author Wheatley
 */
public class Block 
{ 
    private boolean isActive;
    private BlockType Type; 
    /* 
       NetBeans says these vars aren't being used
       I think it's fine? Let me know when you start 
       the terrain gen @Julianne :D 
    */
    private float x, y, z; 
    
    public enum BlockType 
    { 
        BlockType_Default(0),
        BlockType_Grass(1), 
        BlockType_Sand(2), 
        BlockType_Water(3), 
        BlockType_Dirt(4), 
        BlockType_Stone(5), 
        BlockType_Bedrock(6);
        
        private int BlockID;

        BlockType(int i) 
        { 
            BlockID=i;
        } 
        public int getID()
        { 
            return BlockID;
        } 
        public void setID(int i)
        { 
            BlockID = i;
        } 
    } 
    
    public Block(BlockType type)
    { 
        Type = type; 
    } 
    
    public void setCoords(float x, float y, float z)
    { 
        this.x = x; 
        this.y = y; 
        this.z = z; 
    } 
    
    public boolean isActive() 
    { 
        return isActive; 
    }
    public void setActive (boolean active)
    {
        isActive = active;
    }
    
    public int getID()
    {
        return Type.getID();
    }
}
