/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

/**
 *
 * @author Valen
 */
import static org.lwjgl.opengl.GL11.*;

// This class holds cube attributes
//Cube.draw() to draw 
public class Cube {
    
    public static void draw() {
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

        drawOutline();
    }

    private static void drawOutline() {
        glColor3f(1.0f,1.0f,1.0f); // white lines

        glBegin(GL_LINE_LOOP); // Top
            glVertex3f( 1.0f, 1.0f,-1.0f); 
            glVertex3f(-1.0f, 1.0f,-1.0f); 
            glVertex3f(-1.0f, 1.0f, 1.0f); 
            glVertex3f( 1.0f, 1.0f, 1.0f); 
        glEnd();

        glBegin(GL_LINE_LOOP); // Bottom
            glVertex3f( 1.0f,-1.0f, 1.0f); 
            glVertex3f(-1.0f,-1.0f, 1.0f); 
            glVertex3f(-1.0f,-1.0f,-1.0f); 
            glVertex3f( 1.0f,-1.0f,-1.0f); 
        glEnd();

        glBegin(GL_LINE_LOOP); // Front
            glVertex3f( 1.0f, 1.0f, 1.0f); 
            glVertex3f(-1.0f, 1.0f, 1.0f); 
            glVertex3f(-1.0f,-1.0f, 1.0f); 
            glVertex3f( 1.0f,-1.0f, 1.0f); 
        glEnd();

        glBegin(GL_LINE_LOOP); // Back
            glVertex3f( 1.0f,-1.0f,-1.0f); 
            glVertex3f(-1.0f,-1.0f,-1.0f); 
            glVertex3f(-1.0f, 1.0f,-1.0f); 
            glVertex3f( 1.0f, 1.0f,-1.0f); 
        glEnd();

        glBegin(GL_LINE_LOOP); // Left
            glVertex3f(-1.0f, 1.0f, 1.0f); 
            glVertex3f(-1.0f, 1.0f,-1.0f); 
            glVertex3f(-1.0f,-1.0f,-1.0f); 
            glVertex3f(-1.0f,-1.0f, 1.0f);
        glEnd();            

        glBegin(GL_LINE_LOOP); // Right
            glVertex3f( 1.0f, 1.0f,-1.0f); 
            glVertex3f( 1.0f, 1.0f, 1.0f); 
            glVertex3f( 1.0f,-1.0f, 1.0f); 
            glVertex3f( 1.0f,-1.0f,-1.0f);
        glEnd();
    }
}

