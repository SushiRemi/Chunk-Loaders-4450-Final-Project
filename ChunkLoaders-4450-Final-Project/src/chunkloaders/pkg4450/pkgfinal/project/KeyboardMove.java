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

    public void update(boolean isFlying) {
        //float dx = (float) Math.sin(Math.toRadians(this.camera.yaw)) * speed;
        //float dz = (float) Math.cos(Math.toRadians(this.camera.yaw)) * speed;
        float dy = speed;
        boolean fly = isFlying;
        
        
        Keyboard.enableRepeatEvents(true);
        ///////////////////
        //TO DO: Key Bounds
        ///////////////////
        if(fly){
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                camera.moveForward(speed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                camera.strafeLeft(speed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                camera.moveBack(speed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                camera.strafeRight(speed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                camera.moveUp(speed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                camera.moveDown(speed);
            }
        } else {
            camera.moveDown(2*speed); //simulate gravity
            
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                camera.moveForward(speed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                camera.strafeLeft(speed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                camera.moveBack(speed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                camera.strafeRight(speed);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && Keyboard.isRepeatEvent() == false) {
                camera.moveUp(2);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                //camera.moveUp(speed);
            }
        }

        
        // Esc to quit
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Display.destroy();
            System.exit(0);
        }
    }


}
