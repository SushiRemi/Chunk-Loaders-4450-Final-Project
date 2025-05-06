/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

/**
 *
 * @author Ada, Julianne
 */
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class KeyboardMove {
    private final Camera camera;
    private final float speed = 0.1f;

    public KeyboardMove(Camera camera) {
        this.camera = camera;
    }

    public void update() {
        //float dx = (float) Math.sin(Math.toRadians(this.camera.yaw)) * speed;
        //float dz = (float) Math.cos(Math.toRadians(this.camera.yaw)) * speed;
        float dy = speed;
        
        ///////////////////
        //TO DO: Key Bounds
        ///////////////////
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            camera.moveForward(dy);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            camera.strafeLeft(dy);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            camera.moveBack(dy);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            camera.strafeRight(dy);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            camera.moveDown(dy);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            camera.moveUp(dy);
        }
        
        // Esc to quit
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Display.destroy();
            System.exit(0);
        }
    }


}