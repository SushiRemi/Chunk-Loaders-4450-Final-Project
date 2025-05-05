/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

/**
 *
 * @author Ada
 */

import org.lwjgl.input.Mouse;

public class MouseMove {
    private final Camera camera;
    private final float mouseSensitivity = 0.1f;

    public MouseMove(Camera cam) {
        this.camera = cam;
    }

    public void update() {
        float dx = Mouse.getDX();
        float dy = Mouse.getDY();
        camera.yaw(dx * mouseSensitivity);
        camera.pitch(dy * mouseSensitivity);
    }
}