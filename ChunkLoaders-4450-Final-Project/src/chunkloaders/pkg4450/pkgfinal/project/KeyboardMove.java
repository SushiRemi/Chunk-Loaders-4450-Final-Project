/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

/**
 *
 * @author Ada,
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
        float dx = (float) Math.sin(Math.toRadians(this.camera.yaw)) * speed;
        float dz = (float) Math.cos(Math.toRadians(this.camera.yaw)) * speed;
        float dy = speed;
        
        ///////////////////
        //TO DO: Key Bounds
        ///////////////////
        
        // Esc to quit
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Display.destroy();
            System.exit(0);
        }
    }


}